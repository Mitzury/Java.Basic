package ru.mitzury.hw10;

import java.util.*;
import java.util.stream.Collectors;

public class PhoneBook {
    Map<String, Set<String>> phoneBook = new HashMap<>();

    public void add(String name, String phone) {
        phoneBook.computeIfAbsent(name, k -> new HashSet<>()).add(phone);
    }

    public List<String> getAllEntries() {
        return phoneBook.entrySet().stream()
                .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                .collect(Collectors.toList());
    }

    public List<String> find(String query) {
        // Проверяем, является ли запрос номером телефона
        boolean isPhoneNumber = query.matches("[+\\d][\\d\\-]+");

        if (isPhoneNumber) {
            // Поиск по номеру телефона
            return phoneBook.entrySet().stream()
                    .filter(entry -> entry.getValue().contains(query))
                    .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                    .collect(Collectors.toList());
        } else {
            return phoneBook.entrySet().stream()
                    .filter(entry -> entry.getKey().toLowerCase().contains(query.toLowerCase()))
                    .map(entry -> entry.getKey() + ": " + String.join(", ", entry.getValue()))
                    .collect(Collectors.toList());

        }
    }
    public boolean containsPhoneNumber(String phonePart) {
        return phoneBook.entrySet().stream()
                .anyMatch(entry -> entry.getValue().stream()
                        .anyMatch(phone -> phone.contains(phonePart)));
    }
}