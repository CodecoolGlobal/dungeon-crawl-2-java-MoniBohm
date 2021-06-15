package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;

public class Key extends Item {

    public static int count = 0;

    public Key(Cell cell) {
        super(cell);
        count += 1;
    }

    @Override
    public String toString() {
        return "Key";
    }

    @Override
    public String getTileName() {
        return "key";
    }

}
