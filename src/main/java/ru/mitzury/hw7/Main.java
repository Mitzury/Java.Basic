package ru.mitzury.hw7;

import ru.mitzury.hw7.Enums.Terrain;
import ru.mitzury.hw7.transport.*;

public class Main {

    public static void main(String[] args) {

        Human human = new Human("Машинист");

        ITransport car = new Car(50);
        ITransport horse = new Horse(30);
        ITransport bike = new Bicycle();
        ITransport atv = new AllTerrainVehicle(40);

        human.move(5, Terrain.PLAIN);

        human.mount(car);
        human.move(10, Terrain.PLAIN);
        human.move(5, Terrain.FOREST);

        human.mount(horse);
        human.move(10, Terrain.SWAMP);

        human.mount(atv);
        human.move(20, Terrain.SWAMP);
    }
}