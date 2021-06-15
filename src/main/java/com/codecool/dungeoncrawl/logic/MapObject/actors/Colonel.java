package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Colonel extends Enemy {
    public Colonel(Cell cell) {
        super(cell);
        damage = ActorStats.COLONEL.damage;
        health = ActorStats.COLONEL.health;
    }

    @Override
    public void initMove() { }


    @Override
    public String getTileName() {
        return "colonel";
    }
}
