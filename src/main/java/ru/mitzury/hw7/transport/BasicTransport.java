package ru.mitzury.hw7.transport;


import ru.mitzury.hw7.Enums.Terrain;

import java.util.Set;

public abstract class BasicTransport implements ITransport {

    protected int resource;
    protected final Set<Terrain> forbiddenTerrains;

    protected BasicTransport(int resource, Set<Terrain> forbiddenTerrains) {
        this.resource = resource;
        this.forbiddenTerrains = forbiddenTerrains;
    }

    @Override
    public boolean move(int distance, Terrain terrain) {
        if (forbiddenTerrains.contains(terrain)) {
            System.out.println(getName() + " не может ехать по " + terrain);
            return false;
        }
        if (resource < distance) {
            System.out.println(getName() + ": недостаточно ресурса");
            return false;
        }
        resource -= distance;
        System.out.println(getName() + " проехал " + distance + " по " + terrain +
                ". Осталось ресурса: " + resource);
        return true;
    }

    protected abstract String getName();
}