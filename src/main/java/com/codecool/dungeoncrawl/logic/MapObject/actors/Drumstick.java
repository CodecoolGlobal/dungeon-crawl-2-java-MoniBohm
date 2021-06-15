package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Drumstick extends Enemy {
    public Drumstick(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "drumstick";
    }
}
