package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Bucket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class generalActorTest {
    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @Test
    void moveUpdatesCells() {
        Player player = new Player(gameMap.getCell(1, 1));
        player.initMove(1, 0);

        assertEquals(2, player.getX());
        assertEquals(1, player.getY());
        assertEquals(null, gameMap.getCell(1, 1).getActor());
        assertEquals(player, gameMap.getCell(2, 1).getActor());
    }

    @Test
    void cannotMoveIntoWall() {
        gameMap.getCell(2, 1).setType(CellType.WALL);
        Player player = new Player(gameMap.getCell(1, 1));
        player.initMove(1, 0);

        assertEquals(1, player.getX());
        assertEquals(1, player.getY());
    }



//    @Test
//    void healthChange_WhenPlayerMeetWithEnemy_ThenHealthDecrease(){
//        Player player = new Player(gameMap.getCell(1, 1));
//        Skeleton enemy = new Skeleton(gameMap.getCell(0, 1));
//        player.initMove(0,1);
//        int currentHealth= player.getHealth();
//        int enemyDamage = enemy.getDamage();
//        assertEquals(currentHealth - (enemyDamage) * 5, player.getHealth());
//    }
}