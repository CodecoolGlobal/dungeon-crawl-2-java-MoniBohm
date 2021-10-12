package com.codecool.dungeoncrawl.logic.MapObject.items.booster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class HealthPotion extends Item {

    public HealthPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Health Potion";
    }

    @Override
    public String getTileName() {
        return "healthpotion";
    }

}
