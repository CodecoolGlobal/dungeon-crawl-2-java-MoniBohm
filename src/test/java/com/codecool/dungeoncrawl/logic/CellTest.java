package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Bucket;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player testPlayer;
    Enemy testEnemy;

    @BeforeEach
    void setUp() {
//        this.testPlayer = new Player(gameMap.getCell(1, 1));
        this.testEnemy = new Bucket(gameMap.getCell(2, 2));
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
        Cell cell = gameMap.getCell(1, 0);
        assertEquals(null, cell.getNeighbor(0, -1));

        cell = gameMap.getCell(1, 2);
        assertEquals(null, cell.getNeighbor(0, 1));
    }


    @Test
    void getItem() {
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
        Cell enemyCell = testEnemy.getCell();
        enemyCell.setActor(null);
        assertTrue(enemyCell.isEmptyCell(enemyCell));
    }

    @Test
    void givenCellIsNotNull_ThenFalse(){
        Cell enemyCell = testEnemy.getCell();
        assertFalse(enemyCell.isEmptyCell(enemyCell));
    }

}