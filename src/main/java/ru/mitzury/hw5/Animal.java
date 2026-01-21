package ru.mitzury.hw5;

public abstract class Animal {
    private String name;
    private double runSpeed;
    private double swimSpeed;
    private int endurance;

    public Animal(String name, double runSpeed, double swimSpeed, int endurance) {
        this.name = name;
        this.runSpeed = runSpeed;
        this.swimSpeed = swimSpeed;
        this.endurance = endurance;
    }

    public String getName() { return name; }
    public double getRunSpeed() { return runSpeed; }
    public double getSwimSpeed() { return swimSpeed; }
    public int getEndurance() { return endurance; }
    public void setEndurance(int endurance) { this.endurance = endurance; }

    public void setName(String name) {
        this.name = name;
    }

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public void setSwimSpeed(double swimSpeed) {
        this.swimSpeed = swimSpeed;
    }


    public double run(int distance) {
        int requiredEndurance = distance; // 1 ед на 1 метр бега
        if (endurance >= requiredEndurance) {
            endurance -= requiredEndurance;
            double time = distance / runSpeed;
            System.out.println(name + " пробежал " + distance + " метров за " + time + " секунд.");
            return time;
        } else {
            System.out.println(name + " слишком устал, чтобы пробежать " + distance + " метров!");
            return -1;
        }
    }

    public double swim(int distance) {
        System.out.println(name + " не умеет плавать!");
        return -1;
    }

    public void info() {
        System.out.println("Имя: " + name);
        System.out.println("Скорость бега: " + runSpeed + " м/с");
        System.out.println("Скорость плавания: " + swimSpeed + " м/с");
        System.out.println("Выносливость: " + endurance);
    }
}