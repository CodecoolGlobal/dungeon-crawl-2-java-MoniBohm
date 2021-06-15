package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Drumstick extends Enemy {
    public Drumstick(Cell cell) {
        super(cell);
        damage = ActorStats.DRUMSTICK.damage;
        health = ActorStats.DRUMSTICK.health;
    }

    @Override
    void move() {

    }

    @Override
    public String getTileName() {
        return "drumstick";
    }
}
