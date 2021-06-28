package com.codecool.dungeoncrawl.util;

import java.util.ArrayList;
import java.util.Arrays;

public enum Direction {

    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    public int dx;
    public int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public static Direction getRandom() {
        int randomPick = RandomHelper.getRandomInt(Direction.values().length);
        return Direction.values()[randomPick];
    }

    public static Direction getRandom(boolean goesVertical) {
        int randomPick = RandomHelper.getRandomInt(Direction.values().length / 2);
        if (goesVertical) return new ArrayList<>(Arrays.asList(Direction.UP, Direction.DOWN)).get(randomPick);
        else return new ArrayList<>(Arrays.asList(Direction.LEFT, Direction.RIGHT)).get(randomPick);
    }

    public static Direction reverse(Direction currentDirection) {
        return switch (currentDirection) {
            case UP -> Direction.DOWN;
            case DOWN -> Direction.UP;
            case LEFT -> Direction.RIGHT;
            case RIGHT -> Direction.LEFT;
        };
    }
}
