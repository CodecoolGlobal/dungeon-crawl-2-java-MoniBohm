package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Skeleton extends Enemy {
    public Skeleton(Cell cell) {
        super(cell);
        damage = 10;
        health = 50;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
