package com.codecool.dungeoncrawl.dao;

import com.codecool.dungeoncrawl.model.PlayerModel;

import java.util.List;

public interface PlayerDao {
    void add(PlayerModel player);
    void update(PlayerModel player);
    boolean isPlayerInDb(int hashcode);
    PlayerModel get(int id);
    List<PlayerModel> getAll();
}
