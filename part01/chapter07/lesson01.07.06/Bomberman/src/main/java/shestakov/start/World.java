package shestakov.start;

import shestakov.models.Cell;
import shestakov.services.CellEnum;
import shestakov.services.DirectionEnum;

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
     * Instantiates a new World.
     */
    public World() {
        this(31, 13);
    }

    /**
     * Instantiates a new World.
     *
     * @param width  the width
     * @param height the height
     */
    public World(int width, int height) {
        this.board = new Cell[width][height];
        this.width = width;
        this.height = height;
    }
}
