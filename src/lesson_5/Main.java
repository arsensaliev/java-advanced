package lesson_5;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        addArr(arr);
        formula1(arr);


        formula2(arr);
    }

    public static void addArr(float[] arr) {
        Arrays.fill(arr, 1);
    }

    public static void formula1(float[] arr) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Formula1 Total execution time: " + (endTime - startTime) + "ms");
    }

    public static void formula2(float[] arr) {
        long startTime = System.currentTimeMillis();
        float[] a1 = new float[HALF];
        float[] a2 = new float[HALF];

        System.arraycopy(arr, 0, a1, 0, HALF);
        System.arraycopy(arr, HALF, a2, 0, HALF);


        Thread thread1 = read(a1);
        Thread thread2 = read(a2);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.arraycopy(a1, 0, arr, 0, HALF);
            System.arraycopy(a2, 0, arr, HALF, HALF);

            long endTime = System.currentTimeMillis();
            System.out.println("Formula2 Total execution time: " + (endTime - startTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static Thread read(float[] arr) {
        return new Thread(() -> {
            for (int i = 0; i < arr.length; i++) {
                arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });
    }
}
