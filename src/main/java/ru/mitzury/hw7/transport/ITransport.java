package ru.mitzury.hw7.transport;

import ru.mitzury.hw7.Enums.Terrain;

public interface ITransport {
    boolean move(int distance, Terrain terrain);
}