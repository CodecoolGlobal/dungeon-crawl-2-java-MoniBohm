package com.codecool.dungeoncrawl.logic.MapObject.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class Uzi extends Item implements Weapon{
    public Uzi(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Uzi";
    }

    @Override
    public String getTileName() {
        return "uzi";
    }


}
