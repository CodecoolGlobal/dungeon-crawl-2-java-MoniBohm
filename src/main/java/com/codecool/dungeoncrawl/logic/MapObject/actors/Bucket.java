package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class Bucket extends Enemy {

    public Bucket(Cell cell) {
        super(cell);
        damage = ActorStats.BUCKET.damage;
        health = ActorStats.BUCKET.health;
    }

    @Override
    public void initMove() { }

    @Override
    public String getTileName() {
        return "bucket";
    }
}
