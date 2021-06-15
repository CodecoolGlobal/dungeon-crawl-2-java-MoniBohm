package com.codecool.dungeoncrawl.logic.MapObject.items.booster;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class HealtPotion extends Item {

    public HealtPotion(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "HealtPotion";
    }

    @Override
    public String getTileName() {
        return "healtpotion";
    }

    public void increaseHealth() {

    }
}
