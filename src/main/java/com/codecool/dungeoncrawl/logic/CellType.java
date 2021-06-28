package com.codecool.dungeoncrawl.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    WATER_VERTICAL("water_vertical"),
    WATER_HORIZONTAL("water_horizontal"),
    WATER_CORNER_1("water_corner_1"),
    WATER_CORNER_2("water_corner_2"),
    WATER_CORNER_3("water_corner_3"),
    WATER_CORNER_4("water_corner_4"),
    TREE_1("tree_1"),
    TREE_2("tree_2"),
    TREE_3("tree_3"),
    DIGIT_1("digit_1"),
    DIGIT_2("digit_2"),
    DIGIT_3("digit_3"),
    FIRE("fire"),
    GRAVE("grave"),
    CORPSE("corpse"),
    CAGE("cage"),
    DUCK("duck");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }
    public String getTileName() {
        return tileName;
    }
}
