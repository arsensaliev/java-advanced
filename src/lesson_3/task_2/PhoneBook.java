package lesson_3.task_2;

import java.util.*;

public class PhoneBook {
    private final HashMap<String, List<String>> book;

    public PhoneBook() {
        this.book = new HashMap<>();
    }

    public void add(String lastName, String phone) {
        if (!book.containsKey(lastName)) {
            book.put(lastName, new ArrayList<>(Collections.singletonList(phone)));
            System.out.println(String.format("Номер %s добавлен для фамилии %s", phone, lastName));
        } else {
            List<String> phones = book.get(lastName);

            if (!phones.contains(phone)) {
                phones.add(phone);
                book.put(lastName, phones);
                System.out.println(String.format("Номер %s добавлен для фамилии %s", phone, lastName));
            } else {
                System.out.println(String.format("Номер %s уже существует для фамилии %s", phone, lastName));
            }
        }
    }

    public void get(String lastName) {
        if (book.containsKey(lastName)) {
            System.out.println(lastName + ": " + book.get(lastName));
        } else {
            System.out.println(String.format("В справочнике нет записи для фамилии %s", lastName));
        }
    }
}
