package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bucket extends Enemy {
    public Bucket(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "bucket";
    }
}
