package ru.mitzury.hw9;

import java.util.ArrayList;

public class RangeProcessor {
    public ArrayList<Integer> generateRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min не может быть больше max");
        }

        ArrayList<Integer> result = new ArrayList<>();
        for (int i = min; i <= max; i++) {
            result.add(i);
        }
        return result;
    }
}