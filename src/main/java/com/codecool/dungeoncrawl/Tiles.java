package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();

    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(1, 0));
        tileMap.put("wall", new Tile(7, 15));
        tileMap.put("fire", new Tile(28, 11));
        tileMap.put("grave", new Tile(1, 14));
        tileMap.put("corpse", new Tile(0, 15));
        tileMap.put("floor", new Tile(0, 0));
        tileMap.put("player", new Tile(26, 7));
        tileMap.put("bucket", new Tile(27, 7));
        tileMap.put("drumstick", new Tile(28, 7));
        tileMap.put("colonel", new Tile(27, 8));
        tileMap.put("key", new Tile(16, 23));
        tileMap.put("coin", new Tile(9, 25));
        tileMap.put("headgear", new Tile(6, 22));
        tileMap.put("bodyarmor", new Tile(3, 23));
        tileMap.put("legarmor", new Tile(7, 23));
        tileMap.put("door", new Tile(6, 17));
        tileMap.put("opendoor", new Tile(9, 17));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
