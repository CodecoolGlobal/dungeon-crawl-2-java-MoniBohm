package com.codecool.dungeoncrawl.logic.MapObject.items;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.BodyArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.HeadGear;
import com.codecool.dungeoncrawl.logic.MapObject.items.armor.LegArmor;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.HealthPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.booster.ManaPotion;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import com.codecool.dungeoncrawl.logic.MapObject.items.weapon.MagicStaff;
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
    Item testHeadGear;
    Item testLegArmor;
    Cell testCell;


    @BeforeEach
    void setup(){
        this.testCoin = new Coin(gameMap.getCell(0, 0));
        this.testDungeonExit = new DungeonExit(gameMap.getCell(0, 1));
        this.testKey = new Key(gameMap.getCell(0, 2));
        this.testUzi = new Uzi(gameMap.getCell(1, 0));
        this.testHealthPotion = new HealthPotion(gameMap.getCell(1, 1));
        this.testBodyArmor = new BodyArmor(gameMap.getCell(1, 2));
        this.testHeadGear = new HeadGear(gameMap.getCell(2, 0));
        this.testLegArmor = new LegArmor(gameMap.getCell(2, 1));
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


    @Test
    void getTestBodyArmorString(){
       String excepted = "bodyarmor";
        assertEquals(excepted, testBodyArmor.getTileName());
    }


    @Test
    void getTestHeaderGearString(){
        String excepted = "headgear";
        assertEquals(excepted, testHeadGear.getTileName());
    }

    @Test
    void getTestLegArmorString(){
        String excepted = "legarmor";
        assertEquals(excepted, testLegArmor.getTileName());
    }



    @Test
    void getTestHealthPotionString(){
        String excepted = "healthpotion";
        assertEquals(excepted, testHealthPotion.getTileName());
    }

    @Test
    void getTestManaPotionString(){
        Item testManaPotion = new ManaPotion(gameMap.getCell(1, 1));
        String excepted = "manapotion";
        assertEquals(excepted, testManaPotion.getTileName());
    }



    @Test
    void getTestUziString(){
        String excepted = "uzi";
        assertEquals(excepted, testUzi.getTileName());
    }

    @Test
    void getTestMagicStaffString(){
        Item testMagicStaff = new MagicStaff(gameMap.getCell(1, 1));

        String excepted = "magicstaff";
        assertEquals(excepted, testMagicStaff.getTileName());
    }
}