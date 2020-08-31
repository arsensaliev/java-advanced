package lesson_3.task_1;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    int capacity = 10;
    ArrayList<String> stringList = addRandomString(capacity);

    uniqueStrings(stringList);

  }
  public static ArrayList<String> addRandomString(int length) {
    ArrayList<String> stringList = new ArrayList<>(length);
    int leftLimit = 97; // letter 'a'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 1;
    Random random = new Random();
    for (int i = 0; i < length; i++) {
      String generatedString = random.ints(leftLimit, rightLimit + 1).limit(targetStringLength)
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString().toUpperCase();

      stringList.add(generatedString);
    }
    System.out.println(stringList);
    return stringList;
  }

  public static void uniqueStrings(ArrayList<String> stringList) {

    Set<String> uniqueAlpha = new TreeSet<>(stringList);

    System.out.println("Количество уникальных элементов: " + uniqueAlpha.size());
    System.out.println("Уникальные элементы: " + uniqueAlpha);

    System.out.println("Частота встречаемости слов");
    for (String key : uniqueAlpha) {
      System.out.println(key + ": " + Collections.frequency(stringList, key));
    }
  }
}
