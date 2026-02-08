package ru.mitzury.hw12;

import java.io.*;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

public class MyApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        File rootDir = new File(".");

        System.out.println("Текстовые файлы в корневом каталоге:");

        File[] txtFiles = rootDir.listFiles((dir, name) -> name.endsWith(".txt"));

        if (txtFiles == null || txtFiles.length == 0) {
            System.out.println("Текстовые файлы не найдены.");
            return;
        }

        for (File file : txtFiles) {
            System.out.println("- " + file.getName());
        }

        System.out.print("\nВведите имя файла: ");
        String fileName = scanner.nextLine();

        File selectedFile = new File(fileName);

        if (!selectedFile.exists()) {
            System.out.println("Файл не найден.");
            return;
        }

        System.out.println("\nСодержимое файла:");
        try {
            List<String> lines = Files.readAllLines(selectedFile.toPath());
            for (String line : lines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }

        System.out.println("\nВведите строки для записи в файл (exit — выход):");

        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(selectedFile, true))) {

            while (true) {
                String input = scanner.nextLine();

                if ("exit".equalsIgnoreCase(input)) {
                    break;
                }

                writer.write(input);
                writer.newLine();
                writer.flush();
            }

        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }

        System.out.println("Работа завершена.");
    }
}

