package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.UI.AlertBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.Armor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealtPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.ManaPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.NextStageDoor;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.PrevStageDoor;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    public static final int MINIMUM_NR_COIN = 2;
    private List<Item> inventory;


    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<>();
        damage = ActorStats.PLAYER.damage;
        health = ActorStats.PLAYER.health;
        armor = ActorStats.BUCKET.armor;
    }

    public String inventoryToString() {
        StringBuilder sb = new StringBuilder();
        for (Item item : inventory) {
            sb.append(item).append("  ");
        }
        return sb.toString();
    }

    private boolean validateCell(Cell nextCell) {
        if (isColonel(nextCell)) {
            fightColonel(nextCell);
        } else if (isEnemyCell(nextCell)) {
            fightEnemy(nextCell);
            return true;
        } else if (isDoor(nextCell)) {
            manageDoor(nextCell);
            return true;
        } else if (isItemCell(nextCell)) {
            pickupItem(nextCell);
            return true;
        } else if (isEmptyCell(nextCell)) {
            move(nextCell);
            return true;
        }
        return false;
    }

    private void manageDoor(Cell nextCell) {
       Item door = nextCell.getItem();
       if (door instanceof PrevStageDoor) {
            openPrevDoor();
       }else if (isEnoughOfKey("Key")) {
            openNextDoor(nextCell);
        }else{
            AlertBox.display("Door says", "Collect all the keys!");
        }
       }

    private void openPrevDoor() {
        Main.isPreviousMap = true;
    }


    private void openNextDoor(Cell nextCell) {
        NextStageDoor nextStageDoor = (NextStageDoor) nextCell.getItem();
        nextStageDoor.setOpen(true);
        Main.isNextMap = true;
    }


    private void fightColonel(Cell nextCell) {
        if (isEnoughOfCoin("coin")) {
            removeFromInventoryKeys();
            move(nextCell);
        }else{
            AlertBox.display("Colonal says", "Collect minimum 10 coins!");
        }
    }

    private int numberOfItem(String nameOfItem) {
        return (int) inventory.stream()
                .filter(item1 -> item1.getTileName().equalsIgnoreCase(nameOfItem))
                .count();
    }

    private void removeFromInventoryKeys() {
        inventory.removeIf(element -> element.getTileName().equalsIgnoreCase("coin"));
    }

    private boolean isEnoughOfKey(String itemName) {
        return numberOfItem(itemName) == Key.count;
    }


    private boolean isEnoughOfCoin(String itemName) {
        return numberOfItem(itemName) >= MINIMUM_NR_COIN;
    }


    private boolean isEnoughOfCoin(String itemName, int numberOfCoin) {
        return numberOfItem(itemName) == numberOfCoin;
    }

    private boolean isDoor(Cell nextCell) {
        Item currentItem = nextCell.getItem();
        return currentItem instanceof NextStageDoor || currentItem instanceof PrevStageDoor;
    }

    private void pickupItem(Cell nextCell) {
        cell.setActor(null);
        Item itemType = nextCell.getItem();
        if (itemType instanceof Coin) {
            this.putItemToInventory(nextCell.getItem());
            }
        if (itemType instanceof Key) {
            this.putItemToInventory(nextCell.getItem());
            if (isEnoughOfKey("Key")) {
                tryToOpenDoor(nextCell);
            }
        } else if (itemType instanceof Armor) {
            setArmor();
            this.putItemToInventory(nextCell.getItem());
        } else if (itemType instanceof Weapon) {
            setDamage();
            this.putItemToInventory(nextCell.getItem());
        } else if (itemType instanceof HealtPotion) {
            this.putItemToInventory(nextCell.getItem());
        } else if (itemType instanceof ManaPotion) {
            this.putItemToInventory(nextCell.getItem());
        }
        moveToItemPosition(nextCell);
    }

    private void tryToOpenDoor(Cell nextCell) {
        GameMap map = nextCell.getGameMap();
        Cell cell = map.getNextDoor();
        if (cell != null) {
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

    private void fightEnemy(Cell nextCell) {
        Actor player = cell.getActor();
        Actor enemy = nextCell.getActor();
        boolean isFightOver = false;
        fightToTheDeath(nextCell, player, enemy, isFightOver);
    }

    protected boolean isActorAlive(int actorHealth) {
        return actorHealth > 0;
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

    public void setArmor() {
        this.armor++;
    }

    public void setDamage() {
        this.damage++;
    }

    public void setHealth() {
        this.health++;
    }


    public int getArmor() {
        return this.armor;
    }
}
