package com.codecool.dungeoncrawl.logic.MapObject.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class Bazooka extends Item implements Weapon{
    public Bazooka(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Bazooka";
    }

    @Override
    public String getTileName() {
        return "bazooka";
    }

}
