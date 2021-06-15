package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import java.util.List;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int damage;
    protected boolean gameOver = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void initMove(int dx, int dy) { // emptyre mehet csak - item/enemy vizsgálat
        Cell nextCell = cell.getNeighbor(dx, dy);
        validateCell(nextCell);
    }

    private void validateCell(Cell nextCell) {
        if(isEnemyCell(nextCell)){
            fightEnemy(nextCell);
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

    private void fightEnemy(Cell nextCell){
        Actor player = cell.getActor();
        Actor enemy = nextCell.getActor();
        boolean isFightOver = false;

        while (!isFightOver){
            player.setHealth(hitPlayer(player, enemy));
            enemy.setHealth(hitEnemy(player, enemy));
            if(isActorDead(player.health)){
                isFightOver = true;
                gameOver = true;
            }
            if(isActorDead(enemy.health)){
                isFightOver = true;
                move(nextCell);
            }
        }

    }

    private int hitEnemy(Actor player, Actor enemy) {
        return (enemy.getHealth()) - player.damage;
    }

    private int hitPlayer(Actor player, Actor enemy) {
        return (player.getHealth()) - enemy.damage;
    }

    private boolean isActorAlive(int actorHealth ){return actorHealth > 0;}

    private boolean isActorDead(int actorHealth ){return actorHealth <= 0;}

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

    public void setHealth(int newHealth) {
        this.health = newHealth;
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

    public abstract List getInventory();
}
