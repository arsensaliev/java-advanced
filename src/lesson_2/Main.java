package lesson_2;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.Random;

public class Main {
    static Random random = new Random();
    static String[][] strings = new String[4][4];

    public static void main(String[] args) {
        addElements(strings);
        try {
            each(strings);
        } catch (MyArraySizeException | MyArrayDataException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    static void addElements(String[][] strings) {
        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings.length; j++) {
                strings[i][j] = random.nextInt(2) == 0 ? "" + j : "s";
            }
        }
    }

    static void each(String[][] arr) throws MyArraySizeException, MyArrayDataException {
        if (arr.length > 4) {
            throw new MyArraySizeException("Массив больше 4 элементов");
        }

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            System.out.println(Arrays.toString(arr[i]));

            for (int j = 0; j < arr.length; j++) {
                if (arr[i].length > 4) {
                    throw new MyArraySizeException("Массив больше 4 элементов");
                }
                try {
                    sum += Integer.parseInt(arr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i,j);
                }
            }
        }

        System.out.println("Сумма: " + sum);
    }
}
