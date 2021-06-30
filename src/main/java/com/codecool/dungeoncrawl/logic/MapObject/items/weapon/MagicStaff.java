package com.codecool.dungeoncrawl.logic.MapObject.items.weapon;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class MagicStaff extends Item implements Weapon{
    public MagicStaff(Cell cell) {
        super(cell);
    }

    @Override
    public String toString() {
        return "MagicStaff";
    }

    @Override
    public String getTileName() {
        return "magicstaff";
    }

}
