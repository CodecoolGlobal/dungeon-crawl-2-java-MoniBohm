package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
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
    protected void putItemToInventory(Item item) {
        inventory.add(item);
    }

    @Override
    public List getInventory() {
        return inventory;
    }

    public String getTileName() {
        return "player";
    }
}
