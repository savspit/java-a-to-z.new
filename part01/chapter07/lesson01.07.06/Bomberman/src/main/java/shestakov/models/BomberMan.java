package shestakov.models;

import shestakov.services.CellEnum;
import shestakov.services.DirectionEnum;
import shestakov.start.World;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BomberMan extends Thread {
    public Cell[][] board;
    public BlockingQueue<DirectionEnum> steps = new LinkedBlockingQueue<DirectionEnum>();
    public Cell currentCell;

    public BomberMan(Cell[][] board) {
        this.board = board;
    }

    public void go(DirectionEnum direction) throws InterruptedException {
        this.steps.put(direction);
        this.steps.notifyAll();
    }

    public void setBomb(DirectionEnum direction) {
        synchronized (this.steps) {
            if (direction == DirectionEnum.DOWN) {
                this.board[currentCell.getX()][currentCell.getY() + 1].setStatus(CellEnum.USED_BY_BOMB);
            } else if (direction == DirectionEnum.UP) {
                this.board[currentCell.getX()][currentCell.getY() - 1].setStatus(CellEnum.USED_BY_BOMB);
            } else if (direction == DirectionEnum.LEFT) {
                this.board[currentCell.getX() - 1][currentCell.getY()].setStatus(CellEnum.USED_BY_BOMB);
            } else if (direction == DirectionEnum.RIGHT) {
                this.board[currentCell.getX() + 1][currentCell.getY()].setStatus(CellEnum.USED_BY_BOMB);
            }
        }
    }

    @Override
    public void run() {
        while (Thread.currentThread().interrupted()) {
            while (this.steps.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < this.steps.size(); i++) {
            DirectionEnum direction = null;
            try {
                direction = this.steps.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Cell newCell = currentCell;
            if (direction == DirectionEnum.DOWN) {
                newCell = this.board[currentCell.getX()][currentCell.getY() + 1];
            } else if (direction == DirectionEnum.UP) {
                newCell = this.board[currentCell.getX()][currentCell.getY() - 1];
            } else if (direction == DirectionEnum.LEFT) {
                newCell = this.board[currentCell.getX() - 1][currentCell.getY()];
            } else if (direction == DirectionEnum.RIGHT) {
                newCell = this.board[currentCell.getX() + 1][currentCell.getY()];
            }
            this.currentCell.setStatus(CellEnum.FREE);
            newCell.setStatus(CellEnum.USED_BY_BOMBERMAN);
        }
    }
}
