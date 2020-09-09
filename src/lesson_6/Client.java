package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static String SERVER_ADDR = "localhost";
    private static int SERVER_PORT = 8189;

    private static Socket socket = null;
    private static ServerSocket server = null;
    private static DataInputStream in;
    private static DataOutputStream out;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new ClientMessage(out);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String str = in.readUTF();

                            if (str.equals("/end")) {
                                System.out.println("Я вышел");
                                break;
                            }

                            System.out.println(str);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        System.out.println("Мы отключились от сервера");
                        try {
                            socket.close();
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
