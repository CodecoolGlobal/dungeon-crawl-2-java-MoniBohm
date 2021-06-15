package com.codecool.dungeoncrawl.logic.MapObject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class HeadGear extends Item implements Armor{

    public HeadGear(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "HeadGear";
    }

    @Override
    public String getTileName() {
        return "headgear";
    }

    @Override
    public void increaseArmor() {

    }
}
