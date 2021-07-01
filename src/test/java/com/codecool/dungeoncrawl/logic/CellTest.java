package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Bucket;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Colonel;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player testPlayer;
    Enemy testEnemy;
    Enemy testColonel;
    Item testCoin;
    Item testDoor;
    Item testKey;
    Cell testCell;

    @BeforeEach
    void setUp() {
        this.testPlayer = new Player(gameMap.getCell(1, 1), "name");
        this.testEnemy = new Bucket(gameMap.getCell(2, 2));
        this.testColonel = new Colonel(gameMap.getCell(2, 2));
        this.testCoin = new Coin(gameMap.getCell(2, 2));
        this.testDoor = new DungeonExit(gameMap.getCell(2, 2));
        this.testKey = new Key(gameMap.getCell(2, 2));
        this.testCell = new Cell (gameMap, 1, 0, CellType.FLOOR);

    }

    @Test
    void getNeighbor() {
        Cell cell = gameMap.getCell(1, 1);
        Cell neighbor = cell.getNeighbor(-1, 0);
        assertEquals(0, neighbor.getX());
        assertEquals(1, neighbor.getY());
    }

    @Test
    void cellOnEdgeHasNoNeighbor() {
        assertEquals(null, testCell.getNeighbor(0, -1));
        Cell cell = gameMap.getCell(1, 2);
        assertEquals(null, cell.getNeighbor(0, 1));
    }


    @Test
    void testCellIsFloor_ThenReturnFloor() {
        assertEquals(CellType.FLOOR, testCell.getType());
    }


    @Test
    void testCellIsBodyGuard_ThenReturnFloor() {
        testCell.setType(CellType.BODYGUARD);
        assertEquals(CellType.BODYGUARD, testCell.getType());
    }

    @Test
    void onTheGivenCellPlayer_ThenReturnPlayerActor() {
        testCell.setActor(testPlayer);
        assertEquals(testPlayer, testCell.getActor());
    }


    @Test
    void onTheGivenCellColonel_ThenReturnColonelActor() {
        testCell.setActor(testColonel);
        assertEquals(testColonel, testCell.getActor());
    }


    @Test
    void onTheGivenCellIsCoin_ThenReturnCoinItem() {
        testCell.setItem(testCoin);
        assertEquals(testCoin, testCell.getItem());
    }


    @Test
    void onTheGivenCellIsKey_ThenReturnKeyItem() {
        testCell.setItem(testKey);
        assertEquals(testKey, testCell.getItem());
    }


    @Test
    void onTheGivenCellIsDoor_ThenReturnDoorItem() {
        testCell.setItem(testDoor);
        assertEquals(testDoor, testCell.getItem());
    }

    @Test
    void getPlayer_ThenGivenPlayerString() {
        String excepted = "player";
        assertEquals(excepted, testPlayer.getTileName());
    }

    @Test
    void getBucket_ThenGivenBucketString() {
        String excepted = "bucket";
        assertEquals(excepted, testEnemy.getTileName());
    }

    @Test
    void getGameMap_WithEnemy() {

        assertEquals(gameMap, testEnemy.getCell().getGameMap());
    }


    @Test
    void getGameMap_WithPlayer() {
        assertEquals(gameMap, testPlayer.getCell().getGameMap());
    }

    @Test
    void onGivenCellEnemy_ThenReturnTrue(){
        Cell enemyCell = testEnemy.getCell();
        assertTrue(enemyCell.isEnemyCell());
    }

    @Test
    void onGivenCellEnemy_ThenReturnFalse(){
        Cell enemyCell = testPlayer.getCell();
        assertFalse(enemyCell.isEnemyCell() );
    }

    @Test
    void onTheGivenCellIsEnemy_ThenFalse(){
        Cell enemyCell = testEnemy.getCell();
        assertFalse(enemyCell.isEmptyCell(enemyCell));
    }

    @Test
    void givenCellIsNull_ThenTrue(){
        testCell.setItem(null);
        assertTrue(testCell.isEmptyCell(testCell));
    }

    @Test
    void givenCellIsNotNull_ThenFalse(){
        Cell enemyCell = testEnemy.getCell();
        assertFalse(enemyCell.isEmptyCell(enemyCell));
    }

    @Test
    void getGivenCellX(){
        assertEquals(1, testCell.getX());

    }

    @Test
    void getGivenCellY(){
        assertEquals(0, testCell.getY());
    }
}