package com.codecool.dungeoncrawl.logic.MapObject.actors;

import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.util.Direction;
import com.codecool.dungeoncrawl.util.RandomHelper;


public class GhostChicken extends Enemy{
    public static final int MAX_TRY = 10;
    int count = 0;
        int WIDTH_LOWER_BOUND;
        int WIDTH_UPPER_BOUND;
        int HEIGHT_LOWER_BOUND;
        int HEIGHT_UPPER_BOUND;
        public GhostChicken(Cell cell) {
            super(cell);

            WIDTH_UPPER_BOUND = cell.getGameMap().getWidth() -5;
            WIDTH_LOWER_BOUND = 5;
            HEIGHT_UPPER_BOUND = cell.getGameMap().getHeight() -5;
            HEIGHT_LOWER_BOUND = 5;

            damage = ActorStats.DRUMSTICK.damage;
            health = ActorStats.DRUMSTICK.health;
            armor = ActorStats.BUCKET.armor;
        }

        public void initMove() {
            int randomX;
            int randomY;
            Cell nextCell;
            if(count >= 5){
                do {
                    randomX = RandomHelper.getRandomInt(WIDTH_LOWER_BOUND, WIDTH_UPPER_BOUND);
                    randomY = RandomHelper.getRandomInt(HEIGHT_LOWER_BOUND, HEIGHT_UPPER_BOUND);
                    nextCell = cell.getGameMap().getCell(randomX, randomY);
                } while (isEmptyCell(nextCell));

                move(nextCell);
                count = 0;
            }else{
                for (int i = 0; i < MAX_TRY; i++) {
                    nextCell = getRandomNextCell();
                    if(isEmptyCell(nextCell)){
                        move(nextCell);
                        break;
                    }
                }
                count++;
            }
        }

        private boolean isGhostProofCell(Cell nextCell) {
        return nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.WALL
                && nextCell.getActor() == null
                && nextCell.getItem() == null;
        }

        private Cell getRandomNextCell() {
            Direction nextDirection;
            Cell nextCell;
            nextDirection = Direction.getRandom();
            nextCell = cell.getNeighbor(nextDirection.dx, nextDirection.dy);
            return nextCell;
        }

        public void move(Cell nextCell) {
            cell.setActor(null);
            this.cell = nextCell;
            nextCell.setActor(this);
        }

        @Override
        public String getTileName() {
            return "ghost";
        }
    }

