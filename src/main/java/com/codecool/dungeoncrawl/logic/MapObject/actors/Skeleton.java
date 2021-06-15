package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import java.util.List;

public class Skeleton extends Enemy {
    public Skeleton(Cell cell) {
        super(cell);
        damage = 10;
        health = 50;
    }

    @Override
    protected void putItemToInventory(Item item) {

    }

    @Override
    public List getInventory() {
        return null;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
