package ru.mitzury.hw7.transport;


import ru.mitzury.hw7.Enums.Terrain;

import java.util.Set;

public class Bicycle extends BasicTransport {

    public Bicycle() {
        super(Integer.MAX_VALUE, Set.of(Terrain.SWAMP));
    }

    @Override
    protected String getName() {
        return "Велосипед";
    }
}