package lesson_6;

import java.io.DataOutputStream;
import java.util.Scanner;

public class ServerMessage {
    private final Scanner scanner = new Scanner(System.in);

    public ServerMessage(DataOutputStream out) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Введите сообщение: ");
                        String answer = scanner.nextLine();
                        if (answer.equals("/end")) {
                            out.writeUTF("Отсоединение Сервера");
                            break;
                        }
                        out.writeUTF("Сервер: " + answer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

