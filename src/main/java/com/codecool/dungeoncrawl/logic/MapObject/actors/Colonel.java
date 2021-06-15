package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Colonel extends Enemy {
    public Colonel(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "colonel";
    }
}
