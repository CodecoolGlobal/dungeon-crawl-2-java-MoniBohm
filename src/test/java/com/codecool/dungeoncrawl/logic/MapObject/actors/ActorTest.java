package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player testPlayer;
    Enemy testEnemy;
    Enemy testColonel;
    Enemy testDrumstick;
    Enemy testGhostChicken;

    @BeforeEach
    void setUp() {
        this.testPlayer = new Player(gameMap.getCell(1, 1), "name");
        this.testEnemy = new Bucket(gameMap.getCell(2, 2));
        this.testColonel = new Colonel(gameMap.getCell(2, 2));
        this.testDrumstick = new Drumstick(gameMap.getCell(2, 2));
        this.testGhostChicken = new GhostChicken(gameMap.getCell(2, 2));
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
}