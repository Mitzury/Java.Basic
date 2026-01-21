package ru.mitzury.hw10;

import java.util.List;

public class MyApp {
    static void main() {

        PhoneBook phoneBook = new PhoneBook();
        // Добавление записей с разным именем и одним и тем же (добавляем только телефон)
        phoneBook.add("Иванов Иван Иванович", "+7-123-456-78-90");
        phoneBook.add("Иванов Иван Иванович", "+312-98-654-3210");
        phoneBook.add("Петров Петр Петрович", "+1-555-555-5555");
        // Получаем все записи
        List<String> entries = phoneBook.getAllEntries();
        entries.forEach(System.out::println);


        System.out.println("Поиск по имени 'Иванов':");
        phoneBook.find("Иванов").forEach(System.out::println);
        System.out.println("\nПоиск по номеру '+1-555-555-5555':");
        phoneBook.find("+1-555-555-5555").forEach(System.out::println);
        System.out.println("Есть ли номер содержащий '555'? " +
                phoneBook.containsPhoneNumber("555"));
    }
}
