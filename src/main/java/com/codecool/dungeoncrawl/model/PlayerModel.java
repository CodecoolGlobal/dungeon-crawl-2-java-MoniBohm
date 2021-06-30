package com.codecool.dungeoncrawl.model;


import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerModel extends BaseModel {
    private String name;
    private int hp;
    private int armor;
    private int damage;
    private int x;
    private int y;
    private List<Item> inventory;

    // After loading from DB, this construction is called
    public PlayerModel(int playerId, String name, int hp, int damage, int armor, int x, int y, List<Item> inventory) {
        this.name = name;
        this.id = playerId;
        this.x = x;
        this.y = y;
        this.hp = hp;
        this.armor = damage;
        this.damage = armor;
        this.inventory = inventory;
    }

    // Before saving to DB, this construction is called
    public PlayerModel(Player player) {
        this.name = player.getName();
        this.id = player.getHash();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();
        this.armor = player.getArmor();
        this.damage = player.getDamage();
        this.inventory = player.getInventory();
    }

    public Optional<ByteArrayOutputStream> getInventorySerialized() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(inventory);
            oout.close();
            return Optional.of(baos);
        } catch (IOException e) {
            System.out.println("Unable to serialize inventory");
            return Optional.empty();
        }
    }

    public static List<Item> getInventoryDeserialized(byte[] inventoryBytes) {
        try {
            if (inventoryBytes != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(inventoryBytes);
                ObjectInputStream oin = new ObjectInputStream(bais);
                Object obj = oin.readObject();
                return (ArrayList<Item>) obj;
            } else {
                return new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unable to deserialize inventory");
            return new ArrayList<>();
        }
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
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

    public int getArmor() {
        return this.armor;
    }

    public int getDamage() {
        return this.damage;
    }
}
