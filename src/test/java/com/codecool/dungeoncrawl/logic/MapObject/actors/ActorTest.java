package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
    Player testPlayer;

    @BeforeEach
    void setUp() {
        Player tesPlayer = new Player(gameMap.getCell(1, 1));
    }

    @Test
    void getHealth() {
        testPlayer.health = 1;
        int excepted = 1;
        assertEquals(excepted, testPlayer.getHealth());
    }

    @Test
    void getDamage() {
    }

    @Test
    void isActorDead() {
    }

    @Test
    void hitEnemy() {
    }

    @Test
    void hitPlayer() {
    }

    @Test
    void fightToTheDeath() {
    }

    @Test
    void getCell() {
    }

    @Test
    void getX() {
    }

    @Test
    void getY() {
    }
}