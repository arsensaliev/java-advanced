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
                    System.out.println("Введите сообщение: ");
                    String answer = scanner.nextLine();
                    out.writeUTF(answer);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
