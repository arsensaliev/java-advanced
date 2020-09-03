package lesson_5;

import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;


    public static void main(String[] args) {
        float[] arr = new float[SIZE];
        float[] arr2 = new float[SIZE];
        addArr(arr);
        addArr(arr2);


        formula1(arr);
        formula2(arr2);

        System.out.println(Arrays.equals(arr, arr2));
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
        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + (i + HALF) / 5) * Math.cos(0.2f + (i + HALF) / 5) * Math.cos(0.4f + (i + HALF) / 2));
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
            System.arraycopy(arr1, 0, arr, 0, HALF);
            System.arraycopy(arr2, 0, arr, HALF, HALF);


            long endTime = System.currentTimeMillis();
            System.out.println("Formula2 Total execution time: " + (endTime - startTime) + "ms");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
