package ru.mitzury.hw6;

import java.util.ArrayList;

public class MyApp {
    public static void main(String[] args) {

        Bowl plate = new Bowl(100);
        System.out.println("Создана тарелка с едой:");
        plate.info();
        System.out.println();
        // Создаем стадо котов
        ArrayList<Cat> cats = new ArrayList<>();
        cats.add(new Cat("Барсик", 30));
        cats.add(new Cat("Мурзик", 25));
        cats.add(new Cat("Васька", 40));
        cats.add(new Cat("Рыжик", 35));

        System.out.println("Созданы коты:");
        for (Cat cat : cats) {
            cat.info();
        }
        System.out.println();


        System.out.println("Пытаемся накормить всех котов:");
        for (Cat cat : cats) {
            cat.eat(plate);
            plate.info();
            System.out.println();
        }

        System.out.println("Итоговая информация о сытости котов:");

        for (Cat cat : cats) {
            cat.info();
        }

        System.out.println();
        System.out.println("Остаток еды в тарелке:");
        plate.info();

        // Демонстрация добавления еды в тарелку
        System.out.println();
        System.out.println("Добавляем еще 50 ед. еды в тарелку:");
        plate.addFood(50);
        plate.info();
    }
}
