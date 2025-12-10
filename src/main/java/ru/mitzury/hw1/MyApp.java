package ru.mitzury.hw1;

import java.util.Scanner;

public class MyApp {

    // Задание 1
    public static void greetings() {
        System.out.println("Hello\nWorld!\nFrom\nJava");
    }

    // Задание 2
    public static void checkSign(int a, int b, int c) {
        int sum = a + b + c;
        if (sum >= 0) {
            System.out.println("Сумма положительна: " + sum);
        } else {
            System.out.println("Сумма отрицательная: " + sum);
        }
    }

    // Задание 3
    public static void selectColor(int data) {
        if (data <= 10) {
            System.out.println("Красный");
        } else if (data <= 20) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    // Задание 4
    public static void compareNumbers(int a, int b) {
        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    // Задание 5
    public static void addOrSubtractAndPrint(int initValue, int delta, boolean increment) {
        int result = increment ? initValue + delta : initValue - delta;
        System.out.println("Result: " + result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите номер задания (1–5) или 0 для выхода:");

        while (true) {
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    greetings();
                    break;

                case 2:
                    System.out.println("Введите три числа:");
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    int z = sc.nextInt();
                    checkSign(x, y, z);
                    break;

                case 3:
                    System.out.println("Введите число:");
                    int value = sc.nextInt();
                    selectColor(value);
                    break;

                case 4:
                    System.out.println("Введите два числа:");
                    int n1 = sc.nextInt();
                    int n2 = sc.nextInt();
                    compareNumbers(n1, n2);
                    break;

                case 5:
                    System.out.println("Введите initValue и delta:");
                    int init = sc.nextInt();
                    int delta = sc.nextInt();
                    System.out.println("Введите true для сложения или false для вычитания:");
                    boolean inc = sc.nextBoolean();
                    addOrSubtractAndPrint(init, delta, inc);
                    break;

                case 0:
                    System.out.println("Выход из программы...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Некорректный ввод. Введите число от 0 до 5.");
                    break;
            }

            System.out.println("\nВведите номер задания (1–5) или 0 для выхода:");
        }
    }
}