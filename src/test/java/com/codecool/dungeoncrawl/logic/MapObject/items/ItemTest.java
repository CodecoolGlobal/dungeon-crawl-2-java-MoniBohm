package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealthPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.Uzi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    private GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Item testCoin;
    Item testDungeonExit;
    Item testKey;
    Item testUzi;
    Item testHealthPotion;
    Item testBodyArmor;
    Cell testCell;


    @BeforeEach
    void setup(){
        this.testCoin = new Coin(gameMap.getCell(0, 0));
        this.testDungeonExit = new DungeonExit(gameMap.getCell(0, 1));
        this.testKey = new Key(gameMap.getCell(0, 2));
        this.testUzi = new Uzi(gameMap.getCell(1, 0));
        this.testHealthPotion = new HealthPotion(gameMap.getCell(1, 1));
        this.testBodyArmor = new BodyArmor(gameMap.getCell(1, 2));
        this.testCell = new Cell (gameMap, 0,0, CellType.FLOOR);
    }

    @Test
    void getTestUziXCoordinate(){
        testCell.setItem(testUzi);
        assertEquals(1, testUzi.getX());
    }

    @Test
    void getTestKeyYCoordinate(){
        testCell.setItem(testKey);
        assertEquals(2, testKey.getY());
    }

    @Test
    void getTestCoinCell(){
        testCell.setItem(testCoin);
        assertEquals(gameMap.getCell(0, 0), testCoin.getCell());
    }

}