package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    private GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    private Player testPlayer;
    private Enemy testEnemy;
    private Enemy testColonel;
    private Enemy testDrumstick;
    private Enemy testGhostChicken;
    private Item testItem;
    private Item testDoor;
    private List<Item> testInventory ;
    private int testArmor ;
    private int testHash ;

    @BeforeEach
    void setUp() {
        this.testPlayer = new Player(gameMap.getCell(1, 1), "name");
        this.testEnemy = new Bucket(gameMap.getCell(2, 2));
        this.testColonel = new Colonel(gameMap.getCell(2, 2));
        this.testDrumstick = new Drumstick(gameMap.getCell(2, 2));
        this.testGhostChicken = new GhostChicken(gameMap.getCell(2, 2));
        this.testItem = new Coin(gameMap.getCell(2, 2));
        this.testDoor = new DungeonExit(gameMap.getCell(2, 2));
        this.testInventory = new ArrayList<>();
        this.testArmor = ActorStats.PLAYER.armor;
        this.testHash = Math.abs("name".hashCode());
    }

    @Test
    void playerHealthIsOne() {
        testPlayer.health = 1;
        int excepted = 1;
        assertEquals(excepted, testPlayer.getHealth());
    }

    @Test
    void playerHealthIsZero() {
        testPlayer.health = 0;
        int excepted = 0;
        assertEquals(excepted, testPlayer.getHealth());
    }

    @Test
    void playerHealthIsMinus() {
        testPlayer.health = -1;
        int excepted = -1;
        assertEquals(excepted, testPlayer.getHealth());
    }

    @Test
    void givenPlayerHealthIsNull_ThenDead() {
        testPlayer.health = 0;
        boolean excepted = true;
        int actorHealth = testPlayer.health;
        assertEquals(excepted, testPlayer.isActorDead(actorHealth));
    }


    @Test
    void givenPlayerHealthIsNull_ThenAlive() {
        testPlayer.health = 1;
        boolean excepted = false;
        int actorHealth = testPlayer.health;
        assertEquals(excepted, testPlayer.isActorDead(actorHealth));
    }


    @Test
    void hitEnemy() {
        testEnemy.health = 1;
        testPlayer.damage = 10;
        assertEquals(-9, testPlayer.hitEnemy(testPlayer, testEnemy));
    }

    @Test
    void hitPlayer() {
        testPlayer.health = 10;
        testPlayer.damage = 10;
        assertEquals(0, testPlayer.hitPlayer(testPlayer, testEnemy));
    }


    @Test
    void giveTwoItemToInventory_ThenLSizeIsTwo(){
        testInventory.add(testItem);
        testInventory.add(testItem);
        assertEquals(2, testInventory.size());
    }




    @Test
    void askInventory_GetUpdatedInventory(){
        testPlayer.putItemToInventory(testItem);
        testPlayer.putItemToInventory(testItem);
        testInventory.add(testItem);
        testInventory.add(testItem);
        assertEquals(testInventory, testPlayer.getInventory());
    }


    @Test
    void getPlayerName(){
        assertEquals("name", testPlayer.getName());
    }


    @Test
    void getPlayerUpdatedArmor(){
        testPlayer.setArmor(33);
        testArmor += 3;
        assertEquals(33, testPlayer.getArmor());
    }

    @Test
    void getPlayerUpdatedCoin(){
        testPlayer.putItemToInventory(testItem);
        testPlayer.putItemToInventory(testItem);
        testInventory.add(testItem);
        testInventory.add(testItem);
        assertEquals(2, testPlayer.getCoin());

    }

    @Test
    void getPlayerNameLikeHash(){
        assertEquals(testHash, testPlayer.getHash());

    }


    @Test
    void onGivenCellIsItem_AfterValidationReturnTrue(){
        Cell itemCell = testItem.getCell();
        assertTrue(testPlayer.validateCell(itemCell));
    }


    @Test
    void onGivenCellIsColonel_AfterValidationReturnTrue(){
        Cell colonelCell = testColonel.getCell();
        assertTrue(testPlayer.validateCell(colonelCell));
    }


    @Test
    void onGivenCellIsDoor_AfterValidationReturnTrue(){
        Cell doorCell = testDoor.getCell();
        assertTrue(testPlayer.validateCell(doorCell));
    }

    @Test
    void onGivenCellIsItem_ThenReturnTrue() {
        Cell itemCell = testItem.getCell();
        assertFalse(testDrumstick.isEmptyCell(itemCell));
    }

    @Test
    void onGivenCellIsNotItem_ThenReturnFalse() {
        Cell itemCell = testItem.getCell();
        itemCell.setActor(null);
        assertFalse(testDrumstick.isEmptyCell(itemCell));
    }


    @Test
    void givenCellIsEmpty_ThenReturnTrue() {
        Cell enemyCell = testEnemy.getCell();
        enemyCell.setActor(null);
        assertFalse(testDrumstick.isEmptyCell(enemyCell));
    }

    @Test
    void givenCellIsNotEmpty_ThenReturnFalse() {
        Cell enemyCell = testEnemy.getCell();
        assertFalse(testDrumstick.isEmptyCell(enemyCell));
    }


    @Test
    void givenCellIsEmpty_ThenReturnTrueIsisEmptyCellOrPlayerCell() {
        Cell playerCell = testPlayer.getCell();
        assertTrue(testDrumstick.isEmptyCellOrPlayerCell(playerCell));
    }


    @Test
    void givenCellIsNotEmpty_ThenReturnFalseIsisEmptyCellOrPlayerCell() {
        Cell enemyCell = testEnemy.getCell();
        assertFalse(testDrumstick.isEmptyCellOrPlayerCell(enemyCell));
    }


    @Test
    void getColonelName() {
        String result = "colonel";
        assertEquals(result, testColonel.getTileName());
    }

    @Test
    void getBucketName() {
        String result = "bucket";
        assertEquals(result, testEnemy.getTileName());
    }

    @Test
    void getDrumstickName() {
        String result = "drumstick";
        assertEquals(result, testDrumstick.getTileName());
    }


    @Test
    void getGhostChickenName() {
        String result = "ghost";
        assertEquals(result, testGhostChicken.getTileName());
    }

    @Test
    void getXCoordinateTest() {
        assertEquals(1, testPlayer.getX());
    }

    @Test
    void getYCoordinateTest() {
        assertEquals(1, testPlayer.getX());
    }

    @Test
    void getCellCoor() {
        assertEquals(gameMap.getCell(1,1), testPlayer.getCell());
    }


}