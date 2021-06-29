package com.codecool.dungeoncrawl.model;


import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import java.util.List;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int playerHash;
    private int hp;
    private int x;
    private int y;
    private int armor;
    private int  damage;
    private String inventory;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }



    public PlayerModel(int playerHash, String playerName,int hp,  int x, int y) {
        this.playerName = playerName;
        this.playerHash = playerHash;
        this.x =x;
        this.y = y;
        this.hp = hp;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.playerHash = this.playerName.hashCode();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.armor = player.getArmor();
        this.damage = player.getDamage();
        this.inventory = inventoryToString(player.getInventory());
    }


    private String inventoryToString(List<Item> inventory) {
        StringBuilder sb = new StringBuilder();
        for (Item item : inventory) {
            sb.append(item).append(", ");
        }
        return sb.toString();
    }


    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHash() {
        return playerHash;
    }

    public void setPlayerHash(int playerHash) {
        this.playerHash = playerHash;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getDamage() {
        return this.damage;
    }

    public String getInventory() {
        return this.inventory;
    }
}
