package shestakov.start;

import shestakov.models.Cell;
import shestakov.services.CellEnum;
import shestakov.services.DirectionEnum;

public class World {
    public Cell[][] board;
    public int width;
    public int height;

    public World() {
        this(31, 13);
    }

    public World(int width, int height) {
        this.board = new Cell[width][height];
        this.width = width;
        this.height = height;
    }

    public Cell go(Cell currentCell, DirectionEnum direction) {
        Cell newCell = currentCell;
        if (direction == DirectionEnum.DOWN) {
            newCell = this.board[currentCell.getX()][currentCell.getY()+1];
        } else if(direction == DirectionEnum.UP) {
            newCell = this.board[currentCell.getX()][currentCell.getY()-1];
        } else if(direction == DirectionEnum.LEFT) {
            newCell = this.board[currentCell.getX()-1][currentCell.getY()];
        } else if(direction == DirectionEnum.RIGHT) {
            newCell = this.board[currentCell.getX()+1][currentCell.getY()];
        }
        currentCell.setStatus(CellEnum.FREE);
        newCell.setStatus(CellEnum.USED_BY_BOMBERMAN);
        return newCell;
    }
}
