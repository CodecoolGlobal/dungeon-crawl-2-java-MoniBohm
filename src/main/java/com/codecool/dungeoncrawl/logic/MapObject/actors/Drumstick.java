package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Direction;

public class Drumstick extends Enemy {
    private static final int MAX_TRY = 10;

    public Drumstick(Cell cell) {
        super(cell);
        damage = ActorStats.DRUMSTICK.damage;
        health = ActorStats.DRUMSTICK.health;
        armor = ActorStats.BUCKET.armor;
    }

    public void initMove() {
        Cell nextCell;
        for (int i = 0; i < MAX_TRY; i++) {
            nextCell = getRandomNextCell();
            if(isEmptyCell(nextCell)){
                move(nextCell);
                break;
            }
        }
    }

    private Cell getRandomNextCell() {
        Direction nextDirection;
        Cell nextCell;
        nextDirection = Direction.getRandom();
        nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
        return nextCell;
    }

    public void move(Cell nextCell) {
        cell.setActor(null);
        this.cell = nextCell;
        nextCell.setActor(this);
    }

    @Override
    public String getTileName() {
        return "drumstick";
    }
}
