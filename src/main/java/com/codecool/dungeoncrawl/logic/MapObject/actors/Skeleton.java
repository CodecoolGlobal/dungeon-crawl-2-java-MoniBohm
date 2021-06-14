package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class Skeleton extends Enemy {
    public Skeleton(Cell cell) {
        super(cell);
    }

    @Override
    protected void putItemToInventroy(Item item) {

    }

    @Override
    public String getInventory() {
        return null;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }
}
