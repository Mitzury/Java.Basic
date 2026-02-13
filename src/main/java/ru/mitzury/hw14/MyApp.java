package ru.mitzury.hw14;

import java.util.Scanner;

public class MyApp {

    private static final int SIZE = 100_000_000;
    private static final int THREADS_COUNT = 4;

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим выполнения:");
        System.out.println("1 — Однопоточный");
        System.out.println("2 — Многопоточный (4 потока)");
        System.out.print("Ваш выбор: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                singleThreadCalc();
                break;
            case 2:
                multiThreadCalc();
                break;
            default:
                System.out.println("Неверный выбор");
        }

        scanner.close();
    }

    private static void singleThreadCalc() {
        double[] array = new double[SIZE];

        long start = System.currentTimeMillis();

        for (int i = 0; i < SIZE; i++) {
            array[i] = 1.14
                    * Math.cos(i)
                    * Math.sin(i * 0.2)
                    * Math.cos(i / 1.2);
        }

        long end = System.currentTimeMillis();
        System.out.println("Однопоточное выполнение: " + (end - start) + " ms");
    }

    private static void multiThreadCalc() throws InterruptedException {
        double[] array = new double[SIZE];
        int chunkSize = SIZE / THREADS_COUNT;

        Thread[] threads = new Thread[THREADS_COUNT];

        long start = System.currentTimeMillis();

        for (int t = 0; t < THREADS_COUNT; t++) {
            final int startIndex = t * chunkSize;
            final int endIndex = (t == THREADS_COUNT - 1)
                    ? SIZE
                    : startIndex + chunkSize;

            threads[t] = new Thread(() -> {
                for (int i = startIndex; i < endIndex; i++) {
                    array[i] = 1.14
                            * Math.cos(i)
                            * Math.sin(i * 0.2)
                            * Math.cos(i / 1.2);
                }
            });

            threads[t].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        long end = System.currentTimeMillis();
        System.out.println("Многопоточное выполнение (4 потока): " + (end - start) + " ms");
    }
}