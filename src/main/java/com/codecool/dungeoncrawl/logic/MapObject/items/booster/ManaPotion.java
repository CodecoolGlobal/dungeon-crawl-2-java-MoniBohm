package com.codecool.dungeoncrawl.logic.MapObject.items.booster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class ManaPotion extends Item {

    public ManaPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Mana Potion";
    }

    @Override
    public String getTileName() {
        return "manapotion";
    }

}