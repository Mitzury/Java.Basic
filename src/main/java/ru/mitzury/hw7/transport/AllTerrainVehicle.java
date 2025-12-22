package ru.mitzury.hw7.transport;

import java.util.Set;

public class AllTerrainVehicle extends BasicTransport {

    public AllTerrainVehicle(int fuel) {
        super(fuel, Set.of());
    }

    @Override
    protected String getName() {
        return "Вездеход";
    }
}