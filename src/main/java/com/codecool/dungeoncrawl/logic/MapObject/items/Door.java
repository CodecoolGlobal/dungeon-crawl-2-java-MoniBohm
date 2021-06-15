package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Item{

    public Door(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Door";
    }

    @Override
    public String getTileName() {
        return "door";
    }
}
