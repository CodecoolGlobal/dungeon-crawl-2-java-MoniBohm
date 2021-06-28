package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Direction;

public class Bucket extends Enemy {

    Direction currentDirection;

    public Bucket(Cell cell) {
        super(cell);
        damage = ActorStats.BUCKET.damage;
        health = ActorStats.BUCKET.health;
        armor = ActorStats.BUCKET.armor;
        this.currentDirection = Direction.getRandom();
    }

    @Override
    public void initMove() {
        Direction nextDirection = currentDirection;
        Cell nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
        if (isEmptyCell(nextCell)){
            move(nextCell, nextDirection);
        } else {
            tryToGoReverseDirection(nextDirection);
        }
    }

    private void tryToGoReverseDirection(Direction nextDirection) {
        Cell nextCell;
        nextDirection = Direction.reverse(nextDirection);
        nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
        if (isEmptyCell(nextCell)) {
            move(nextCell, nextDirection);
        }
    }


    public void move(Cell nextCell, Direction nextDirection) {
        cell.setActor(null);
        this.cell = nextCell;
        this.cell.setActor(this);
        currentDirection = nextDirection;
    }

    @Override
    public String getTileName() {
        return "bucket";
    }
}
