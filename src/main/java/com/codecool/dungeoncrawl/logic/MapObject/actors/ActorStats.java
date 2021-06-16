package com.codecool.dungeoncrawl.logic.MapObject.actors;

public enum ActorStats {

    PLAYER(15, 100, 30),
    BUCKET(10, 30, 0),
    DRUMSTICK( 6, 20, 0),
    COLONEL(6, 20, 0);

    public int damage;
    public int health;
    public int armor;

    ActorStats(int damage, int health, int armor) {
        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }
}
