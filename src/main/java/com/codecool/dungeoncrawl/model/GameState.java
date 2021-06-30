package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.Item;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class GameState extends BaseModel implements Serializable {
    private String saveName;       // for DB only
    private Timestamp savedAt;         // for DB only
    private String mapFilename;
    private int currentMap;
    private PlayerModel playerModel; // this is only needed for the database saving, not the file export
    private Player player;           // this is only used for the file export
    private GameMap map;

    // After loading from DB, this construction is called
    public GameState(int id, String saveName, String mapFilename, int currentMap, Timestamp savedAt, PlayerModel playerModel, GameMap map) {
        this.id = id;
        this.mapFilename = mapFilename;
        this.saveName = saveName;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerModel = playerModel;
        this.map = map;
    }

    // File Export Saving constructor
    public GameState(String mapFilename, int currentMap, Timestamp savedAt, Player player, GameMap map) {
        this.mapFilename = mapFilename;
        this.saveName = null;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
        this.map = map;
    }

    // Before saving to DB, this construction is called
    public GameState(String saveName, String mapFilename, int currentMap, Timestamp savedAt, PlayerModel playerModel, GameMap map) {
        this.mapFilename = mapFilename;
        this.saveName = saveName;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerModel = playerModel;
        this.map = map;
    }

    public String getSaveName() {
        return saveName;
    }

    public Player getPlayer() {
        return player;
    }

    public Timestamp getSavedAt() {
        return savedAt;
    }

    public String getMapFilename() {
        return mapFilename;
    }

    public Optional<ByteArrayOutputStream> getMapSerialized() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oout = new ObjectOutputStream(baos);
            oout.writeObject(map);
            oout.close();
            return Optional.of(baos);
        } catch (IOException e) {
            System.out.println("Unable to serialize inventory");
            return Optional.empty();
        }
    }

    public static GameMap getMapDeserialized(byte[] mapBytes) {
        try {
            if (mapBytes != null) {
                ByteArrayInputStream bais = new ByteArrayInputStream(mapBytes);
                ObjectInputStream oin = new ObjectInputStream(bais);
                Object obj = oin.readObject();
                return (GameMap) obj;
            } else {
                throw new IllegalStateException("No map not found.");
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("Unable to deserialize inventory");
        }
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public GameMap getMap(){
        return map;
    }

}
