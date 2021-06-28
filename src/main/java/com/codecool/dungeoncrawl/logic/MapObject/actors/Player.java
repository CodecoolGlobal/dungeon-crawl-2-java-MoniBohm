package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.Main;
import com.codecool.dungeoncrawl.UI.AlertBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.Armor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealthPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.ManaPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.*;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {
    public static final int MINIMUM_NR_COIN = 7;
    private List<Item> inventory;
    private final int healthIncrease = 20;
    private final int armorIncrease = 20;
    private final int weaponIncrease = 20;


    public Player(Cell cell) {
        super(cell);
        inventory = new ArrayList<>();
        damage = ActorStats.PLAYER.damage;
        health = ActorStats.PLAYER.health;
        armor = ActorStats.BUCKET.armor;
    }

    public void setCell(Cell newCell) {
        cell = newCell;
    }

    private boolean validateCell(Cell nextCell) {
        if (isColonel(nextCell)) {
            fightColonel(nextCell);
        } else if (isEnemyCell(nextCell)) {
            fightEnemy(nextCell);
            return true;
        } else if (isDungeonDoor(nextCell)) {
            manageDungeonDoor(nextCell);
            return true;
        }else if (isDoor(nextCell)) {
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
            removeFromInventory("key");
            openNextDoor(nextCell);
        }else{
            interactionWithDoor();
        }
       }

    private void interactionWithDoor() {
        AlertBox.display("Door says", "Collect all the keys!");
    }

    private void manageDungeonDoor(Cell nextCell) {
        Item door = nextCell.getItem();
        if (door instanceof DungeonEntrance) {
            enterDungeon();
        }else {
            exitDungeon();
        }
    }

    private void openPrevDoor() {
        Main.isPreviousMap = true;
    }

    private void exitDungeon() {
        Main.isExitingDungeon = true;
    }

    private void openNextDoor(Cell nextCell) {
        NextStageDoor nextStageDoor = (NextStageDoor) nextCell.getItem();
        nextStageDoor.setOpen(true);
        Main.isNextMap = true;
    }

    private void enterDungeon() {
        Main.isEnteringDungeon = true;
    }

    private void fightColonel(Cell nextCell) {
        if (isEnoughOfCoin("coin")) {
            removeFromInventory("coin");
            move(nextCell);
        }else{
            interactionWithColonal();
        }
    }

    private void interactionWithColonal() {
        AlertBox.display("Colonal says", "Collect minimum " + MINIMUM_NR_COIN + " coins!");
    }

    private int numberOfItem(String nameOfItem) {
        return (int) inventory.stream()
                .filter(item1 -> item1.getTileName().equalsIgnoreCase(nameOfItem))
                .count();
    }

    private void removeFromInventory(String item) {
        inventory.removeIf(element -> element.getTileName().equalsIgnoreCase(item));
    }

    private boolean isEnoughOfKey(String itemName) {
        return numberOfItem(itemName) >= Key.count;
    }


    private boolean isEnoughOfCoin(String itemName) {
        return numberOfItem(itemName) >= MINIMUM_NR_COIN;
    }


    private boolean isDoor(Cell nextCell) {
        Item currentItem = nextCell.getItem();
        return currentItem instanceof NextStageDoor || currentItem instanceof PrevStageDoor;
    }

    private boolean isDungeonDoor(Cell nextCell) {
        Item currentItem = nextCell.getItem();
        return currentItem instanceof DungeonEntrance || currentItem instanceof DungeonExit;
    }

    private void pickupItem(Cell nextCell) {
        cell.setActor(null);
        Item itemType = nextCell.getItem();

        if (itemType instanceof Coin) {
            putItemToInventory(nextCell);
        }
        if (itemType instanceof Key) {
            setKeys(nextCell);
        } else if (itemType instanceof Armor) {
            setArmor(nextCell);
        } else if (itemType instanceof Weapon) {
            setWeapons(nextCell);

        } else if (itemType instanceof HealthPotion) {
            putItemToInventory(nextCell);
            setHealth(this.health + healthIncrease);

        } else if (itemType instanceof ManaPotion) {
            putItemToInventory(nextCell);
        }
        moveToItemPosition(nextCell);
    }

    private void setWeapons(Cell nextCell) {
        setDamage(weaponIncrease);
        putItemToInventory(nextCell);
    }

    private void setArmor(Cell nextCell) {
        setArmor(armorIncrease);
        putItemToInventory(nextCell);
    }

    private void setKeys(Cell nextCell) {
        putItemToInventory(nextCell);
        if (isEnoughOfKey("Key")) {
            tryToOpenDoor(nextCell);
        }
    }

    private void putItemToInventory(Cell nextCell) {
        this.putItemToInventory(nextCell.getItem());
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
        boolean successfulMove;
        Cell nextCell = cell.getNeighbor(dx, dy);
        successfulMove = validateCell(nextCell);
        return successfulMove;
    }

    private void fightEnemy(Cell nextCell) {
        Actor player = cell.getActor();
        Actor enemy = nextCell.getActor();
        boolean isFightOver = false;
        fightToTheDeath(nextCell, player, enemy, isFightOver);
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

    public void setArmor(int increaseValue) {
        this.armor+=increaseValue;
    }

    public void setDamage(int increaseValue) {
        this.damage+=increaseValue;
    }

    public void setCheatHealth(int increaseValue) {
        this.health+=increaseValue;
    }

    public void setCheatHealthToMin(int newValue) {
        this.health=newValue;
    }

    public int getArmor() {
        return this.armor;
    }

    public int getCoin(){
        return numberOfItem("coin");
    }
}





