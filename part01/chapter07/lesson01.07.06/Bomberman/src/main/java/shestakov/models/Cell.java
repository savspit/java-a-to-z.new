package shestakov.models;

import shestakov.db.CellEnum;

/**
 * The type Cell.
 */
public class Cell {
    private CellEnum status;
    /**
     * The Character.
     */
    public Character character;
    /**
     * The X.
     */
    public int x;
    /**
     * The Y.
     */
    public int y;

    /**
     * Instantiates a new Cell.
     *
     * @param x the x
     * @param y the y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = CellEnum.FREE;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public CellEnum getStatus() {
        return status;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Gets character.
     *
     * @return the character
     */
    public Character getCharacter() {
        return character;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(CellEnum status) {
        this.status = status;
    }

    /**
     * Sets character.
     *
     * @param character the character
     */
    public void setCharacter(Character character) {
        this.character = character;
    }
}
