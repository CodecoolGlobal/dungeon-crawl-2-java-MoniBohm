package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;

public class ArchEnemy extends Enemy{
    public ArchEnemy(Cell cell) {
        super(cell);
        damage = ActorStats.ARCHENEMY.damage;
        health = ActorStats.ARCHENEMY.health;
        armor = ActorStats.ARCHENEMY.armor;
    }

    @Override
    public String getTileName() {
        return "archenemy";
    }

    @Override
    public void initMove() { }

}