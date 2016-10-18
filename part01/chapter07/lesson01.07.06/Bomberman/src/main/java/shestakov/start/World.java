package shestakov.start;

import shestakov.models.Cell;
import shestakov.models.Monster;
import shestakov.db.DirectionEnum;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The type World.
 */
public class World {
    /**
     * The Board.
     */
    public Cell[][] board;
    /**
     * The Width.
     */
    public int width;
    /**
     * The Height.
     */
    public int height;
    /**
     * The Monsters pool.
     */
    public ExecutorService monstersPool;
    /**
     * The Number of monsters.
     */
    public int numberOfMonsters;

    /**
     * Instantiates a new World.
     */
    public World() {
        this(31, 13, 5);
    }

    /**
     * Instantiates a new World.
     *
     * @param width            the width
     * @param height           the height
     * @param numberOfMonsters the number of monsters
     */
    public World(int width, int height, int numberOfMonsters) {
        this.board = new Cell[width][height];
        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
        this.monstersPool = Executors.newFixedThreadPool(numberOfMonsters);
    }

    /**
     * Gets next cell.
     *
     * @param currentCell the current cell
     * @param direction   the direction
     * @return the next cell
     */
    public Cell getNextCell(Cell currentCell, DirectionEnum direction) {
        Cell nextCell = null;
        if (direction == DirectionEnum.RIGHT) {
            nextCell = this.board[currentCell.getX()][currentCell.getY()+1];
        } else if (direction == DirectionEnum.LEFT) {
            nextCell = this.board[currentCell.getX()][currentCell.getY()-1];
        } else if (direction == DirectionEnum.UP) {
            nextCell = this.board[currentCell.getX()-1][currentCell.getY()];
        } else if (direction == DirectionEnum.DOWN) {
            nextCell = this.board[currentCell.getX()+1][currentCell.getY()];
        }
        return nextCell;
    }

    /**
     * Run monsters.
     */
    public void runMonsters() {
        for (int i=0; i<this.numberOfMonsters; i++) {
            Runnable monster = new Monster(this);
            this.monstersPool.execute(monster);
        }
    }

}
