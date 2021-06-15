package com.codecool.dungeoncrawl.logic.MapObject.items.general;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.Armor;

public class Coin extends Item{

    public Coin(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "Coin";
    }

    @Override
    public String getTileName() {
        return "coin";
    }

}
