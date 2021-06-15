package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Cell;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int damage;
    protected boolean gameOver = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    protected boolean isEnemyCell(Cell nextCell) {
        Actor actor = nextCell.getActor();
        return actor instanceof Bucket;
    }


    protected boolean isItemCell(Cell nextCell) {
        return nextCell.getItem() != null;
    }


    protected boolean isEmptyCell(Cell nextCell) {
        return nextCell.getType() == CellType.FLOOR
                && nextCell.getActor() == null
                && nextCell.getItem() == null;
    }

    public int getHealth() {
        return health;
    }

    protected void setHealth(int newHealth) {
        this.health = newHealth;
    }

    public int getDamage() {
        return this.damage;
    }


    protected Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

}
