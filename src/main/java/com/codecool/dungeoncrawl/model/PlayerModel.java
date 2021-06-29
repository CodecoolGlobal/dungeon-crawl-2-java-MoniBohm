package com.codecool.dungeoncrawl.model;


import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;

public class PlayerModel extends BaseModel {
    private String playerName;
    private int playerHash;
    private int hp;
    private int x;
    private int y;

    public PlayerModel(String playerName, int x, int y) {
        this.playerName = playerName;
        this.x = x;
        this.y = y;
    }



    public PlayerModel(int playerHash, String playerName,int hp,  int x, int y) {
        this.playerName = playerName;
        this.playerHash = playerHash;
        this.x =x;
        this.y = y;
        this.hp = hp;
    }

    public PlayerModel(Player player) {
        this.playerName = player.getName();
        this.playerHash = this.playerName.hashCode();
        this.x = player.getX();
        this.y = player.getY();
        this.hp = player.getHealth();

    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHash() {
        return playerHash;
    }

    public void setPlayerHash(int playerHash) {
        this.playerHash = playerHash;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
