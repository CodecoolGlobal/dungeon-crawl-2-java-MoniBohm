package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.UI.AlertBox;
import com.codecool.dungeoncrawl.UI.GameOverBox;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.RandomHelper;


public class GhostChicken extends Enemy {
    public static final int MAX_TRY = 10;
    int count = 0;
    int WIDTH_LOWER_BOUND;
    int WIDTH_UPPER_BOUND;
    int HEIGHT_LOWER_BOUND;
    int HEIGHT_UPPER_BOUND;

    public GhostChicken(Cell cell) {
        super(cell);

        WIDTH_UPPER_BOUND = cell.getGameMap().getWidth() - 5;
        WIDTH_LOWER_BOUND = 5;
        HEIGHT_UPPER_BOUND = cell.getGameMap().getHeight() - 5;
        HEIGHT_LOWER_BOUND = 5;

        damage = ActorStats.DRUMSTICK.damage;
        health = ActorStats.DRUMSTICK.health;
        armor = ActorStats.BUCKET.armor;
    }

    public void initMove() {
        if (tryMoveAgain()) {
            doWhileDontFindNewPosition();
        } else {
            tryToMoveRandom();
            count++;
        }
    }

    private void doWhileDontFindNewPosition() {
        Cell nextCell;
        do {
            nextCell = getRandomCell();
        } while (!isEmptyCellOrPlayerCell(nextCell));
        move(nextCell);
        count = 0;
    }

    private void tryToMoveRandom() {
        Cell nextCell;
        for (int i = 0; i < MAX_TRY; i++) {
            nextCell = getRandomNextCell();
            if (isEmptyCellOrPlayerCell(nextCell)) {
                move(nextCell);
                break;
            }
        }
    }

    private Cell getRandomCell() {
        int randomX = RandomHelper.getRandomInt(WIDTH_LOWER_BOUND, WIDTH_UPPER_BOUND);
        int randomY = RandomHelper.getRandomInt(HEIGHT_LOWER_BOUND, HEIGHT_UPPER_BOUND);
        Cell nextCell = cell.getGameMap().getCell(randomX, randomY);
        return nextCell;
    }

    private boolean tryMoveAgain() {
        return count >= 5;
    }

    private Cell getRandomNextCell() {
        Direction nextDirection;
        Cell nextCell;
        nextDirection = Direction.getRandom();
        nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
        return nextCell;
    }

    public void move(Cell nextCell) {
        if (nextCell.getActor() instanceof Player) {
            gameOver();
            System.exit(0);
        } else {
            moveNextCell(nextCell);
        }
    }

    private void moveNextCell(Cell nextCell) {
        cell.setActor(null);
        this.cell = nextCell;
        nextCell.setActor(this);
    }

    private void gameOver() {
        AlertBox.display("Sudden Death", "Bad luck a ghost just killed you randomly :( ");
        gameOver = true;
        GameOverBox.display();
    }

    @Override
    public String getTileName() {
        return "ghost";
    }
}

