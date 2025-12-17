package ru.mitzury.hw6;

// Миска
class Bowl {
    private int maxFood;
    private int currentFood;

    public Bowl(int maxFood) {
        this.maxFood = maxFood;
        this.currentFood = maxFood;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            currentFood += amount;
            if (currentFood > maxFood) {
                currentFood = maxFood;
            }
        }
    }
    public boolean decreaseFood(int amount) {
        if (amount <= 0) {
            return false;
        }

        if (currentFood >= amount) {
            currentFood -= amount;
            return true;
        } else {
            return false;
        }
    }
    public int getCurrentFood() {
        return currentFood;
    }
    public int getMaxFood() {
        return maxFood;
    }
    public void info() {
        System.out.println("Миска: " + currentFood + "/" + maxFood + " ед.");
    }
}