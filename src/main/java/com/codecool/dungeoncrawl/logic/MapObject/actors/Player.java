package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Door;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.Key;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
            sb.append(item).append(" \n");
        }
        return sb.toString();
    }

    private void validateCell(Cell nextCell) {
        if(isEnemyCell(nextCell)){
            fightEnemy(nextCell);
        }else if(isDoor(nextCell)){
            manageDoor(nextCell);
        }else if (isItemCell(nextCell)){
            pickupItem(nextCell);
        }else if (isEmptyCell(nextCell)) {
            move(nextCell);
        }
    }

    private void manageDoor(Cell nextCell){
        Item item = nextCell.getItem();
        Key.count = 0;
//        pickedUpAllItemsOfType(item.getTileName());
    }


    private int pickedUpAllItemsOfType(String item){
        int counter = 0;
        for (Item element : inventory){
            String elementName  = element.getTileName();
            if(elementName.equalsIgnoreCase(item)){
                counter+=1;}
        }

//
//        int counter = inventory.stream()
//                .flatMap(element -> Arrays.stream(element.getTileName()))
//                .collect(Collectors.groupingBy(Function.identity(),
//                        Collectors.counting()));
        return counter;
    }


    private boolean isEnoughKey(int numberOfKey){
        return  numberOfKey== Key.count;
    }

    private boolean isDoor(Cell nextCell){
        Item currentItem = nextCell.getItem();
        return currentItem instanceof Door;
    }

    private void pickupItem(Cell nextCell){
        cell.setActor(null);
        this.putItemToInventory(nextCell.getItem());
        nextCell.setItem(null);
        nextCell.setActor(this);
        this.cell = nextCell;
//        isPickedUpAllItemsOfType("key");
    }

    public void initMove(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        validateCell(nextCell);
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

    protected boolean isActorAlive(int actorHealth) {
        return actorHealth > 0;
    }

    protected boolean isActorDead(int actorHealth) {
        return actorHealth <= 0;
    }


    private void move(Cell nextCell) {
        cell.setActor(null);
        nextCell.setActor(this);
        this.cell = nextCell;
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
