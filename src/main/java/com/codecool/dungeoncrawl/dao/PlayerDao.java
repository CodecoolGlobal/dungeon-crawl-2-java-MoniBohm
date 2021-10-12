package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;

import java.util.List;

public interface PlayerDao {
    void add(Player player);
    void update(Player player);
    boolean isPlayerInDb(int hashcode);
    Player get(int id);
    List<Player> getAll();
}
