package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Bucket;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player testPlayer;
    Enemy testEnemy;

    @BeforeEach
    void setUp() {
        this.testPlayer = new Player(gameMap.getCell(1, 1), "name");
        this.testEnemy = new Bucket(gameMap.getCell(2, 2));
    }

    @Test
    void getPlayer() {
        assertEquals(null, gameMap.getPlayer());
    }

    @Test
    void getWidth() {
        int excepted = 3;
        assertEquals(excepted, gameMap.getWidth());
    }

    @Test
    void getHeight() {
        int excepted = 3;
        assertEquals(excepted, gameMap.getHeight());
    }

}