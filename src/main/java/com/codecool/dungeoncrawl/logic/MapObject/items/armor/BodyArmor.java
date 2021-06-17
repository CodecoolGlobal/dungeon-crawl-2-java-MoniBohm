package com.codecool.dungeoncrawl.logic.MapObject.items.armor;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class BodyArmor extends Item implements Armor{

    public BodyArmor(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Body Armor";
    }

    @Override
    public String getTileName() {
        return "bodyarmor";
    }

    @Override
    public void increaseArmor() {

    }
}
