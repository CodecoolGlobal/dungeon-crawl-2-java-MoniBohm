package com.codecool.dungeoncrawl.logic.MapObject.items.general;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public class DungeonExit extends Item {

    public DungeonExit(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "dungeon_exit";
    }

}
