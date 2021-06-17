package com.codecool.dungeoncrawl.logic.MapObject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class LegArmor extends Item implements Armor{

    public LegArmor(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Leg Armor";
    }

    @Override
    public String getTileName() {
        return "legarmor";
    }

    @Override
    public void increaseArmor() {

    }
}
