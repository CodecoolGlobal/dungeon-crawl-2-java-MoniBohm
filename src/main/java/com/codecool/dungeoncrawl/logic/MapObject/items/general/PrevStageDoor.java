package com.codecool.dungeoncrawl.logic.MapObject.items.general;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class PrevStageDoor extends Item {
    public boolean isOpen;

    public PrevStageDoor(Cell cell) {
        super(cell);
        isOpen = true;
    }

    public String toString() {
        return "PrevDoor";
    }

    @Override
    public String getTileName() {
        return "prevdoor";
    }

}
