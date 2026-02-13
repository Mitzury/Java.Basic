package ru.mitzury.hw20;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileSubstringCounter {

    public static int countOccurrences(String fileName, String pattern) throws IOException {

        if (pattern == null || pattern.isEmpty()) {
            return 0;
        }

        int count = 0;
        int bufferSize = 4096;
        char[] buffer = new char[bufferSize];

        String tail = ""; // хвост с прошлого чтения

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(fileName), StandardCharsets.UTF_8))) {

            int readChars;

            while ((readChars = reader.read(buffer)) != -1) {

                String chunk = tail + new String(buffer, 0, readChars);

                count += countInString(chunk, pattern);

                // оставляем последние символы (pattern.length - 1)
                if (chunk.length() >= pattern.length() - 1) {
                    tail = chunk.substring(chunk.length() - (pattern.length() - 1));
                } else {
                    tail = chunk;
                }
            }
        }

        return count;
    }

    // подсчет в строке
    private static int countInString(String text, String pattern) {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(pattern, index)) != -1) {
            count++;
            index++; // учитываем перекрывающиеся совпадения
        }

        return count;
    }
}