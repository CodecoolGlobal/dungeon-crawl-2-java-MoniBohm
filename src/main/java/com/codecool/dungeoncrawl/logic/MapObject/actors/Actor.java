package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.UI.GameOverBox;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected int health;
    protected int damage;
    protected int armor;
    protected boolean gameOver = false;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    protected boolean isEnemyCell(Cell nextCell) {
        Actor actor = nextCell.getActor();
        return actor instanceof Enemy;
    }


    protected boolean isColonel(Cell nextCell) {
        Actor actor = nextCell.getActor();
        return actor instanceof Colonel;
    }

    protected boolean isPlayerCell(Cell nextCell) {
        Actor actor = nextCell.getActor();
        return actor instanceof Player;
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

    public void incrementHealth(){
        this.health += 10;
    }

    public int getDamage() {
        return this.damage;
    }

    public boolean isActorDead(int actorHealth) {
        return actorHealth <= 0;
    }

    public int hitEnemy(Actor player, Actor enemy) {
        return (enemy.getHealth()) - player.damage;
    }

    public int hitPlayer(Actor player, Actor enemy) {
        return (player.getHealth()) - enemy.damage;
    }

    public void fightToTheDeath(Cell nextCell, Actor player, Actor enemy, boolean isFightOver) {
        while (!isFightOver){
            player.setHealth(hitPlayer(player, enemy));
            enemy.setHealth(hitEnemy(player, enemy));
            if(isActorDead(player.health)){
                isFightOver = true;
                GameOverBox.display();
                System.exit(0);
            }
            if(isActorDead(enemy.health)){
                isFightOver = true;
                removeEnemy(enemy);
                move(nextCell);
            }
        }
    }

    private void removeEnemy(Actor enemy) {
        GameMap map = cell.getGameMap();
        map.removeKilledEnemyFromEnemies(enemy);
    }

    protected void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(null);
        this.cell = nextCell;
        nextCell.setActor(this);
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

}
