package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Door extends Item{

    public boolean isOpen;

    public Door(Cell cell) {
        super(cell);
        isOpen = false;
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
