package ru.mitzury.hw5;

public class MyApp {

    public static void main(String[] args) {

        Cat cat = new Cat("Мурка", 15.0, 50);
        Dog dog = new Dog("Шарик", 20.0, 5.0, 80);
        Horse horse = new Horse("Буренка", 25.0, 3.0, 100);

        cat.info();
        cat.run(30);
        cat.swim(5);
        cat.info();

        dog.info();
        dog.run(50);
        dog.swim(20);
        dog.info();

        horse.info();
        horse.run(60);
        horse.swim(10);
        horse.info();
    }
}