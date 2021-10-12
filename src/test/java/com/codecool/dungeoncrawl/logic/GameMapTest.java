package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Bucket;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.DungeonExit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameMapTest {
    private GameMap testGameMap;


    @BeforeEach
    void setUp() {
        this.testGameMap =  new GameMap(3, 3, CellType.FLOOR);
    }

    @Test
    void givenCellIsOutOfMap_ThenReturnNull(){
        assertEquals(null, testGameMap.getCell(5, 0));
    }


    @Test
    void getPlayer() {
        assertEquals(null, testGameMap.getPlayer());
    }

    @Test
    void getWidth() {
        int excepted = 3;
        assertEquals(excepted, testGameMap.getWidth());
    }

    @Test
    void getHeight() {
        int excepted = 3;
        assertEquals(excepted, testGameMap.getHeight());
    }

}