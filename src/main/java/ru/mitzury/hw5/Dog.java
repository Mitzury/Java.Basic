package ru.mitzury.hw5;

public class Dog extends Animal {
    public Dog(String name, double runSpeed, double swimSpeed, int endurance) {
        super(name, runSpeed, swimSpeed, endurance);
    }

    @Override
    public double swim(int distance) {
        int requiredEndurance = distance * 2; // собака тратит 2 ед на 1 метр
        if (getEndurance() >= requiredEndurance) {
            setEndurance(getEndurance() - requiredEndurance);
            double time = distance / getSwimSpeed();
            System.out.println(getName() + " проплыл " + distance + " метров за " + time + " секунд.");
            return time;
        } else {
            System.out.println(getName() + " слишком устал, чтобы плыть " + distance + " метров!");
            return -1;
        }
    }
}