package ru.mitzury.hw6;

class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public boolean eat(Bowl Bowl) {
        if (Bowl.decreaseFood(appetite)) {
            satiety = true;
            System.out.println(name + " поел и теперь сытый!");
            return true;
        } else {
            System.out.println(name + " не смог поесть, в тарелке недостаточно еды!");
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public boolean isSatiety() {
        return satiety;
    }

    public void info() {
        String satietyStatus = satiety ? "сытый" : "голодный";
        System.out.println("Кот " + name + ", аппетит: " + appetite + ", состояние: " + satietyStatus);
    }
}