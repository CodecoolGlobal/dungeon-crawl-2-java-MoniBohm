package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Coin;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.Key;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer;
    private Item testCoin;
    private Item testKey;
    private GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);

    @BeforeEach
    void setUp() {
        this.testPlayer = new Player(gameMap.getCell(1, 1), "name");
        this.testCoin = new Coin(gameMap.getCell(2, 2));
        this.testKey = new Key(gameMap.getCell(0, 0));
        testPlayer.putItemToInventory(testCoin);
        testPlayer.putItemToInventory(testKey);
    }

    @Test
    void serialize_andDeserialized_ReturnSameResult(){
        Optional<ByteArrayOutputStream> inventoryOutputStream =  testPlayer.getInventorySerialized();
        byte[] inventoryBytes = inventoryOutputStream.orElse(null).toByteArray();
        List<Item> result =  Player.getInventoryDeserialized(inventoryBytes);
        assertEquals(testPlayer.getInventory().toString(), result.toString());
    }

}