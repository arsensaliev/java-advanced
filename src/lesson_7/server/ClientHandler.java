package lesson_7.server;

import lesson_7.server.MyServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private String name;

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = null;
            new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }


    public void authentication() throws IOException {
        while (true) {
            String strFromServer = in.readUTF();
            if (strFromServer.startsWith("/auth")) {
                String[] token = strFromServer.split("\\s");
                String nick = myServer.getAuthService().getNicknameByLoginAndPassword(token[1], token[2]);
                if (nick != null) {
                    if (!myServer.isNickBusy(nick)) {
                        name = nick;
                        sendMsg("/authok " + nick);
                        myServer.broadcastMsg(name + " зашел в чат");
                        myServer.subscribe(this);
                    } else {
                        sendMsg("Учетная запись уже используется");
                    }
                } else {
                    sendMsg("Неверный логин или пароль");
                }
                break;
            }
        }
    }

    public void readMessages() throws IOException {
        while (true) {
            String strFromClient = in.readUTF();
            if (strFromClient.equals("/end")) {
                return;
            }
            myServer.broadcastMsg(String.format("%s: %s", name, strFromClient));
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }
}