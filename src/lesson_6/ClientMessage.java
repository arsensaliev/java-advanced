package lesson_6;

import java.io.DataOutputStream;
import java.util.Scanner;

public class ClientMessage {
    private final Scanner scanner = new Scanner(System.in);

    public ClientMessage(DataOutputStream out) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.println("Введите сообщение: ");
                        String answer = scanner.nextLine();

                        if (answer.equals("/end")) {
                            out.writeUTF("Отсоединение Клиента");
                            break;
                        }
                        out.writeUTF("Клиент: " + answer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
