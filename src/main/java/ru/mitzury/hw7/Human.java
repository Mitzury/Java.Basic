package ru.mitzury.hw7;

import ru.mitzury.hw7.Enums.Terrain;
import ru.mitzury.hw7.transport.ITransport;

public class Human {

    private final String name;
    private ITransport currentTransport;

    public Human(String name) {
        this.name = name;
    }

    public void mount(ITransport transport) {
        this.currentTransport = transport;
    }

    public void dismount() {
        currentTransport = null;
        System.out.println(name + " спешился");
    }

    public boolean move(int distance, Terrain terrain) {
        if (currentTransport == null) {
            System.out.println(name + " прошел пешком " + distance + " по " + terrain);
            return true;
        }
        return currentTransport.move(distance, terrain);
    }
}