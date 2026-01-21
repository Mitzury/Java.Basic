package ru.mitzury.hw9;

public class SumProcessor {
    public int sumGreaterThanFive(int[] numbers) {
        if (numbers == null) {
            throw new IllegalArgumentException("Массив не может быть null");
        }

        int sum = 0;
        for (int n : numbers) {
            if (n > 5) {
                sum += n;
            }
        }
        return sum;
    }
}
