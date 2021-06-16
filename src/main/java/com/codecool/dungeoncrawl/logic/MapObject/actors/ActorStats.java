package com.codecool.dungeoncrawl.logic.MapObject.actors;

public enum ActorStats {

    PLAYER(15, 1000),
    BUCKET(10, 30),
    DRUMSTICK( 6, 20),
    COLONEL(6, 20);

    public int damage;
    public int health;

    ActorStats(int damage, int health) {
        this.damage = damage;
        this.health = health;
    }
}
