package lesson_3;

import java.util.*;

public class Main {
  public static void main(String[] args) {
    int capacity = 20;
    List<String> stringList = addRandomString(capacity);

    uniqueStrings(stringList);
  }

  public static ArrayList addRandomString(int length) {
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
    return stringList;
  }

  public static void uniqueStrings(List<String> stringList) {

    Set<String> uniqueAlpha = new TreeSet<>(stringList);

    System.out.println("Количество уникальных элементов: " + uniqueAlpha.size());
    System.out.println("Уникальные элементы: " + uniqueAlpha);
  }
}
