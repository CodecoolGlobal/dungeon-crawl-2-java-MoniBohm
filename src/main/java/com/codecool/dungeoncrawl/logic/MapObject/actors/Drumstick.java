package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Direction;

public class Drumstick extends Enemy {
    public Drumstick(Cell cell) {
        super(cell);
        damage = ActorStats.DRUMSTICK.damage;
        health = ActorStats.DRUMSTICK.health;
    }

    public void initMove() {
        Cell nextCell;
        Direction nextDirection;
        do {
            nextDirection = Direction.getRandom();
            nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
        } while (isEmptyCell(nextCell));
        move(nextCell);
    }

    public void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        this.cell = nextCell;
    }

    @Override
    public String getTileName() {
        return "drumstick";
    }
}
