package lesson_3.task_2;

public class Main {
    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();

        book.add("Салиев", "87080778008");
        book.add("Салиев", "87777777777");
        book.add("Петрович", "123545");
        book.get("Салиев");
        book.get("Иванов");
    }
}
