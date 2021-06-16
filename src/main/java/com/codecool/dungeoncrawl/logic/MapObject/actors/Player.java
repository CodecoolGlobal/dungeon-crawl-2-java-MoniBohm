package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.NextStageDoor;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;


    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<>();
        damage = ActorStats.PLAYER.damage;
        health = ActorStats.PLAYER.health;
    }

    public String inventoryToString(){
        StringBuilder sb = new StringBuilder();
        for(Item item : inventory){
            sb.append(item).append("  ");
        }
        return sb.toString();
    }

    private boolean validateCell(Cell nextCell) {
        if(isColonel(nextCell)){
            fightColony(nextCell);
        }
        else if (isEnemyCell(nextCell)) {
            fightEnemy(nextCell);
            return true;
        }else if(isDoor(nextCell)){
            manageDoor(nextCell);
            return true;
        }else if (isItemCell(nextCell)){
            pickupItem(nextCell);
            return true;
        }else if (isEmptyCell(nextCell)) {
            move(nextCell);
            return true;
        }
        return false;
    }

    private void manageDoor(Cell nextCell){
        if(isEnoughOfKey("Key")){
            openDoor(nextCell);
        }
    }

    private void openDoor(Cell nextCell) {
        NextStageDoor nextStageDoor = (NextStageDoor) nextCell.getItem();
        nextStageDoor.setOpen(true);
        Main.isNextMap = true;
    }


    private void fightColony(Cell nextCell){
        if(isEnoughOfCoin("coin")){
            removeFromInventoryKeys();
            move(nextCell);
        }
    }

    private int numberOfItem(String nameOfItem){
        return (int) inventory.stream()
                .filter(item1 -> item1.getTileName().equalsIgnoreCase(nameOfItem))
                .count();
    }

    private void removeFromInventoryKeys(){
        inventory.removeIf(element -> element.getTileName().equalsIgnoreCase("coin"));
    }

    private boolean isEnoughOfKey(String itemName){
        return  numberOfItem(itemName) == Key.count;
    }


    private boolean isEnoughOfCoin(String itemName){
        return numberOfItem(itemName) >= 10;
    }


    private boolean isEnoughOfCoin(String itemName, int numberOfCoin){
        return  numberOfItem(itemName) == numberOfCoin;
    }

    private boolean isDoor(Cell nextCell){
        Item currentItem = nextCell.getItem();
        return currentItem instanceof NextStageDoor;
    }

    private void pickupItem(Cell nextCell){
        cell.setActor(null);
        this.putItemToInventory(nextCell.getItem());
        if(nextCell.getItem() instanceof Key){
            if(isEnoughOfKey("Key")){
                tryToOpenDoor(nextCell);
            }
        }
        moveToItemPosition(nextCell);
    }

    private void tryToOpenDoor(Cell nextCell) {
        GameMap map =  nextCell.getGameMap();
        Cell cell =  map.getNextDoor();
        if (cell != null){
            NextStageDoor nextStageDoor = (NextStageDoor) cell.getItem();
            nextStageDoor.setOpen(true);
        }
    }

    private void moveToItemPosition(Cell nextCell) {
        nextCell.setItem(null);
        nextCell.setActor(this);
        this.cell = nextCell;
    }

    public boolean initMove(int dx, int dy) {
        boolean succesfullmove = false;
        Cell nextCell = cell.getNeighbor(dx, dy);
        succesfullmove = validateCell(nextCell);
        return succesfullmove;
    }

    private void fightEnemy(Cell nextCell){
        Actor player = cell.getActor();
        Actor enemy = nextCell.getActor();
        boolean isFightOver = false;
        fightToTheDeath(nextCell, player, enemy, isFightOver);
    }

    private void fightToTheDeath(Cell nextCell, Actor player, Actor enemy, boolean isFightOver) {
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

    protected boolean isActorAlive(int actorHealth) {
        return actorHealth > 0;
    }

    protected boolean isActorDead(int actorHealth) {
        return actorHealth <= 0;
    }

    private void move(Cell nextCell) {
        cell.setActor(null);
        this.cell = nextCell;
        nextCell.setActor(this);
    }


    protected void putItemToInventory(Item item) {
        inventory.add(item);
    }

    public List getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }
}
