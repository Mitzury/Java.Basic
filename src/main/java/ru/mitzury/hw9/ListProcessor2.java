package ru.mitzury.hw9;

import java.util.List;

public class ListProcessor2 {
    public void incrementAll(int value, List<Integer> list) {
        if (list == null) {
            throw new IllegalArgumentException("Список не может быть null");
        }

        for (int i = 0; i < list.size(); i++) {
            list.set(i, list.get(i) + value);
        }
    }
}
