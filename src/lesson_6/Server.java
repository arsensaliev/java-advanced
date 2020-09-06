package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    static String message;
    static Scanner scanner = new Scanner(System.in);
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new ServerMessage(out);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        try {
                            String str = in.readUTF();
                            if (str.equals("/end")) {
                                break;
                            }

                            out.writeUTF(str);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
