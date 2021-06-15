package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    FIRE("fire"),
    GRAVE("grave"),
    CORPSE("corpse");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }
    public String getTileName() {
        return tileName;
    }
}
