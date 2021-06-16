package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.NextStageDoor;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            getMapRow(height, defaultCellType, x);
        }
    }

    private void getMapRow(int height, CellType defaultCellType, int x) {
        for (int y = 0; y < height; y++) {
            cells[x][y] = new Cell(this, x, y, defaultCellType);
        }
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void moveEnemies() {
        for ( Cell[] row : cells) {
            for ( Cell cell : row) {
                if (cell.isEnemyCell()) {
                    ((Enemy) cell.getActor()).initMove();
                }
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Cell getNextDoor() {
        for ( Cell[] row : cells) {
            for ( Cell cell : row) {
                if (cell.getItem() instanceof NextStageDoor) {
                    return cell;
                }
            }
    }
        return null;
    }
}
