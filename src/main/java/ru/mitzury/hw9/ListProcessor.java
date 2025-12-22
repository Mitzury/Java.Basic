package ru.mitzury.hw9;

import java.util.List;

public class ListProcessor {
    public void fillWithNumber(int value, List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, value);
        }
    }
}
