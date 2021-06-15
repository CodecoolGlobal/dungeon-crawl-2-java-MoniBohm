package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Enemy extends Actor {

    public Enemy(Cell cell) {
        super(cell);
    }

    abstract void move();
}
