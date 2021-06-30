package com.codecool.dungeoncrawl.model;

import com.codecool.dungeoncrawl.logic.GameMap;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private String saveName;       // for DB only
    private Date savedAt;         // for DB only
    private String mapFilename;
    private int currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel playerModel;  // TODO to delete playerModel!
    private Player player;
    private GameMap map;

    // Database Loading constructor
    public GameState(int id, String saveName, String mapFilename, int currentMap, Date savedAt, PlayerModel playerModel) {
        this.id = id;
        this.mapFilename = mapFilename;
        this.saveName = saveName;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerModel = playerModel;
    }

    // File Export Saving constructor
    public GameState(String mapFilename, int currentMap, Date savedAt, PlayerModel playerModel, Player player, GameMap map) {
        this.mapFilename = mapFilename;
        this.saveName = null;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerModel = playerModel;
        this.player = player;
        this.map = map;
    }


    // Database Saving constructor
    public GameState(String saveName, String mapFilename, int currentMap, Date savedAt, PlayerModel playerModel) {
        this.mapFilename = mapFilename;
        this.saveName = saveName;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.playerModel = playerModel;

    }

    public String getSaveName() {
        return saveName;
    }

    public Player getPlayer() {
        return player;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }

    public String getMapFilename() {
        return mapFilename;
    }

    public int getCurrentMap() {
        return currentMap;
    }

    public void setMapFilename(String mapFilename) {
        this.mapFilename = mapFilename;
    }

    public List<String> getDiscoveredMaps() {
        return discoveredMaps;
    }

    public void addDiscoveredMap(String map) {
        this.discoveredMaps.add(map);
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    public GameMap getMap(){
        return map;
    }

    public void setPlayerModel(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
}
