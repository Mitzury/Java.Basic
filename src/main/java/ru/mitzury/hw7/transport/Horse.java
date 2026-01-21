package ru.mitzury.hw7.transport;

import ru.mitzury.hw7.Enums.Terrain;

import java.util.Set;

public class Horse extends BasicTransport {

    public Horse(int stamina) {
        super(stamina, Set.of());
    }

    @Override
    protected String getName() {
        return "Лошадь";
    }
}