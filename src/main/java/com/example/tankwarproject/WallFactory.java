package com.example.tankwarproject;

public class WallFactory {

    public static Wall createIndestructibleWall(int x, int y) {
        return new Wall(x, y, false);
    }
}