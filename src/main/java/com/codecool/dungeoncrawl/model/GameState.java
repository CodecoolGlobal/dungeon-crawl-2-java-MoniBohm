package com.codecool.dungeoncrawl.model;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class GameState extends BaseModel {
    private Date savedAt;
    private String mapFilename;
    private int currentMap;
    private List<String> discoveredMaps = new ArrayList<>();
    private PlayerModel player;

    // Loading constructor
    public GameState(int id, String mapFilename, int currentMap, Date savedAt, PlayerModel player) {
        this.id = id;
        this.mapFilename = mapFilename;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
    }


    // Saving constructor
    public GameState(String mapFilename, int currentMap, Date savedAt, PlayerModel player) {
        this.mapFilename = mapFilename;
        this.currentMap = currentMap;
        this.savedAt = savedAt;
        this.player = player;
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

    public PlayerModel getPlayer() {
        return player;
    }


    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
}
