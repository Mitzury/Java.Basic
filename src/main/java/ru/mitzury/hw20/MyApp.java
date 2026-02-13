package ru.mitzury.hw20;

import java.io.IOException;
import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите имя файла: ");
        String fileName = scanner.nextLine();

        System.out.print("Введите искомую последовательность: ");
        String pattern = scanner.nextLine();

        try {
            int result = FileSubstringCounter.countOccurrences(fileName, pattern);
            System.out.println("Найдено совпадений: " + result);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }
}