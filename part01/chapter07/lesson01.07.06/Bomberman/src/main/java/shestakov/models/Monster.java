package shestakov.models;

import shestakov.db.CellEnum;
import shestakov.db.DirectionEnum;
import shestakov.start.World;

/**
 * The type Monster.
 */
public class Monster extends Character {
    /**
     * The World.
     */
    public World world;
    /**
     * The Current direction.
     */
    public DirectionEnum currentDirection;
    /**
     * The Current cell.
     */
    public Cell currentCell;

    /**
     * Instantiates a new Monster.
     *
     * @param world the world
     */
    public Monster(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().interrupted()) {
            Cell nextCell = this.world.getNextCell(this.currentCell, this.currentDirection);
            synchronized (nextCell) {
                synchronized (this.currentCell) {
                    if (nextCell.getStatus() == CellEnum.FREE) {
                        nextCell.setStatus(CellEnum.USED_BY_MONSTER);
                        nextCell.setCharacter(this);
                        this.currentCell.setStatus(CellEnum.FREE);
                        this.currentCell.setCharacter(null);
                        this.currentCell = nextCell;
                    } else if (nextCell.getStatus() == CellEnum.USED_BY_BOMBERMAN) {
                        nextCell.getCharacter().interrupt();
                        nextCell.setStatus(CellEnum.USED_BY_MONSTER);
                        nextCell.setCharacter(this);
                        this.currentCell.setStatus(CellEnum.FREE);
                        this.currentCell.setCharacter(null);
                        this.currentCell = nextCell;
                        return;
                    } else {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        changeDirection();
                    }
                }
            }
        }
    }

    /**
     * Change direction.
     */
    public void changeDirection() {
        if (this.currentDirection == DirectionEnum.UP) {
            this.currentDirection = DirectionEnum.DOWN;
        } else if (this.currentDirection == DirectionEnum.DOWN) {
            this.currentDirection = DirectionEnum.UP;
        } else if (this.currentDirection == DirectionEnum.LEFT) {
            this.currentDirection = DirectionEnum.RIGHT;
        } else if (this.currentDirection == DirectionEnum.RIGHT) {
            this.currentDirection = DirectionEnum.LEFT;
        }
    }

}
