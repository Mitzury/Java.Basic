package ru.mitzury.hw7.transport;

import ru.mitzury.hw7.Enums.Terrain;

import java.util.Set;

public class Car extends BasicTransport {

    public Car(int fuel) {
        super(fuel, Set.of(Terrain.FOREST, Terrain.SWAMP));
    }

    @Override
    protected String getName() {
        return "Машина";
    }
}