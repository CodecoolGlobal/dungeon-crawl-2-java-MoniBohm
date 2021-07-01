package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealthPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Uzi;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Item testCoin;
    Item testDungeonExit;
    Item testKey;
    Item testUzi;
    Item testHealthPotion;
    Item testBodyArmor;


    @BeforeEach
    void setup(){
        this.testCoin = new Coin(gameMap.getCell(0, 0));
        this.testDungeonExit = new DungeonExit(gameMap.getCell(0, 1));
        this.testKey = new Key(gameMap.getCell(0, 2));
        this.testUzi = new Uzi(gameMap.getCell(1, 0));
        this.testHealthPotion = new HealthPotion(gameMap.getCell(1, 1));
        this.testBodyArmor = new BodyArmor(gameMap.getCell(1, 2));
    }


}