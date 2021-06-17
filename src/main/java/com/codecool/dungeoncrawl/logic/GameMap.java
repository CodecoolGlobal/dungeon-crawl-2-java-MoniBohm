package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.logic.MapObject.actors.Actor;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Enemy;
import com.codecool.dungeoncrawl.logic.MapObject.actors.Player;
import com.codecool.dungeoncrawl.logic.MapObject.items.general.NextStageDoor;

import java.util.LinkedList;
import java.util.List;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    private List<Actor> enemies;

    private Player player;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        enemies = new LinkedList<>();
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
        if (isIndexInRange(x, true) && isIndexInRange(y, false)) {
            return cells[x][y];
        }
        return null;
    }

    private boolean isIndexInRange(int index, boolean isWidth) {
        if (isWidth) return index >= 0 && index < cells[0][0].getGameMap().getWidth();
        return index >= 0 && index < cells[0][0].getGameMap().getHeight();
    }


    public void collectEnemies() {
        for (Cell[] row : cells) {
            addEnemiesToCell(row);
        }
    }

    private void addEnemiesToCell(Cell[] row) {
        for (Cell cell : row) {
            if (cell.isEnemyCell()) {
                enemies.add(cell.getActor());
            }
        }
    }

    public void moveEnemies() {
        for (Actor enemy : enemies) {
            if (enemy.getCell() != null) {
                ((Enemy) enemy).initMove();
            }
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void removeKilledEnemyFromEnemies(Actor killedEnemy) {
        enemies.remove(killedEnemy);
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
        for (Cell[] row : cells) {
            Cell currentCell = findDoorInRow(row);
            if (currentCell != null) return currentCell;
        }
        return null;
    }

    private Cell findDoorInRow(Cell[] row) {
        for (Cell cell : row) {
            if (isNextDoor(cell)) {
                return cell;
            }
        }
        return null;
    }

    private boolean isNextDoor(Cell cell) {
        return cell.getItem() instanceof NextStageDoor;
    }
}
