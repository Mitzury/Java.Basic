package ru.mitzury.hw5;

public class Cat extends Animal {
    public Cat(String name, double runSpeed, int endurance) {
        super(name, runSpeed, 0, endurance); // кот плавать не умеет
    }

    @Override
    public double swim(int distance) {
        System.out.println(getName() + " не умеет плавать!");
        return -1;
    }
}