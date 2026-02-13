package ru.mitzury.hw19;

import java.util.ArrayList;

public class Box<T extends Fruit> {

    private ArrayList<T> fruits = new ArrayList<>();

    // добавление фрукта
    public void add(T fruit) {
        fruits.add(fruit);
    }

    // вес коробки
    public float weight() {
        float sum = 0;
        for (T fruit : fruits) {
            sum += fruit.getWeight();
        }
        return sum;
    }

    // сравнение коробок
    public boolean compare(Box<?> other) {
        return Math.abs(this.weight() - other.weight()) < 0.0001;
    }

    // пересыпание фруктов
    public void transferTo(Box<? super T> other) {
        if (other == this) return;

        for (T fruit : fruits) {
            other.add(fruit);
        }
        fruits.clear();
    }

    public int getCount() {
        return fruits.size();
    }
}
