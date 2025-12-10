package ru.mitzury.hw2;


public class MyApp {


    public static void repeatPrint(int count, String text) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }

    public static void sumElements(int[] arr) {
        int sum = 0;
        for (int num : arr) {
            if (num > 5) {
                sum += num;
            }
        }
        System.out.println("Сумма элементов больше 5: " + sum);
    }

    public static void fillArray(int value, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = value;
        }
    }

    public static void incrementArray(int value, int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] += value;
        }
    }

    public static void compareHalves(int[] arr) {
        if (arr.length == 0) {
            System.out.println("Массив пустой");
            return;
        }

        int mid = arr.length / 2;
        int sumFirstHalf = 0;
        int sumSecondHalf = 0;

        // Первая половина
        for (int i = 0; i < mid; i++) {
            sumFirstHalf += arr[i];
        }

        // Вторая половина
        for (int i = mid; i < arr.length; i++) {
            sumSecondHalf += arr[i];
        }

        // Сравнение
        if (sumFirstHalf > sumSecondHalf) {
            System.out.println("Сумма первой половины больше: " + sumFirstHalf);
        } else if (sumSecondHalf > sumFirstHalf) {
            System.out.println("Сумма второй половины больше: " + sumSecondHalf);
        } else {
            System.out.println("Суммы половин равны: " + sumFirstHalf);
        }
    }

    public static void main(String[] args) {

        // part 1
        System.out.println("Part 1");
        repeatPrint(5, "Привет, Java!");
        // part 2
        System.out.println("Part 2");
        int[] numbers = {1, 4, 6, 7, 3, 10, 5, 8};
        sumElements(numbers);
        // part 3
        System.out.println("Part 3");
        int[] numbers2 = new int[5];
        fillArray(7, numbers2);
        for (int num : numbers) {
            System.out.print(num + " " + "\n");
        }
        // part 4
        System.out.println("Part 4");
        int[] numbers3 = {1, 2, 3, 4, 5};
        incrementArray(3, numbers3); // увеличиваем все элементы на 3
        for (int n : numbers3) {
            System.out.print(n + " ");
        }
        // part 5
        System.out.println("Part 5");
        int[] numbers4 = {3, 5, 7, 2, 4, 6};
        compareHalves(numbers4);
    }
}
