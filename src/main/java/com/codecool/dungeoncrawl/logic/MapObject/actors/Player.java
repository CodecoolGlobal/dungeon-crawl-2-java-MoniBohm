package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.Key;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    private List<Item> inventory;
    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<Item>();
    }

    public String inventoryToString(){
        StringBuilder sb = new StringBuilder();
        for(Item item : inventory){
            sb.append(item).append(" \n");
        }
        return sb.toString();
    }

    @Override
    protected void putItemToInventroy(Item item) {
        inventory.add(item);
    }

    @Override
    public String getInventory() {
        System.out.println(inventory.toString());
        return inventory.toString();
    }

    public String getTileName() {
        return "player";
    }
}
