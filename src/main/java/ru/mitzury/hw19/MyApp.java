package ru.mitzury.hw19;

public class MyApp {
    public static void main(String[] args) {

        Box<Apple> appleBox = new Box<>();
        Box<Apple> appleBox2 = new Box<>();
        Box<Orange> orangeBox = new Box<>();
        Box<Fruit> fruitBox = new Box<>();

        appleBox.add(new Apple());
        appleBox.add(new Apple());
        appleBox.add(new Apple());

        orangeBox.add(new Orange());
        orangeBox.add(new Orange());

        System.out.println("Вес appleBox = " + appleBox.weight());
        System.out.println("Вес orangeBox = " + orangeBox.weight());

        System.out.println("Сравнение: " + appleBox.compare(orangeBox));

        // пересыпаем яблоки
        appleBox.transferTo(appleBox2);

        System.out.println("appleBox после пересыпания: " + appleBox.getCount());
        System.out.println("appleBox2 после пересыпания: " + appleBox2.getCount());

        // можно пересыпать в общую коробку
        appleBox2.transferTo(fruitBox);
        orangeBox.transferTo(fruitBox);

        System.out.println("fruitBox: " + fruitBox.getCount());
    }
}