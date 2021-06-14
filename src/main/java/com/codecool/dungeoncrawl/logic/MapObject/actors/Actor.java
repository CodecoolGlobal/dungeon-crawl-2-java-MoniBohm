package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

public abstract class Actor implements Drawable {
    private Cell cell;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void initMove(int dx, int dy) { // emptyre mehet csak - item/enemy vizsgálat
        Cell nextCell = cell.getNeighbor(dx, dy);
        validateCell(nextCell);
    }

    private void validateCell(Cell nextCell) {
        if(isEmptyCell(nextCell) && isEnemyCell(nextCell)){
            fightEnemy();
        }else if (isItemCell(nextCell)){
            pickupItem(nextCell);
        }else if (isEmptyCell(nextCell)) {
            move(nextCell);
        }
    }

    private boolean isEnemyCell(Cell nextCell){
        Actor actor = nextCell.getActor();
        return actor instanceof Skeleton;
    }

    private void fightEnemy(){

    }

    private boolean isItemCell(Cell nextCell){
        return nextCell.getItem() != null;
    }

    private void pickupItem(Cell nextCell){
        cell.setActor(null);
        this.putItemToInventory(nextCell.getItem());
        nextCell.setItem(null);
        nextCell.setActor(this);
        this.cell = nextCell;
    }

    protected abstract void putItemToInventory(Item item);

    private void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        this.cell = nextCell;
    }

    private boolean isEmptyCell(Cell nextCell) {
        return nextCell.getType() == CellType.FLOOR
                && nextCell.getActor()==null
                && nextCell.getItem() == null;
    }

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    public abstract String getInventory();
}
