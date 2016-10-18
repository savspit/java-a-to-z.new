package shestakov.models;

import shestakov.db.CellEnum;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * The type Bomber man.
 */
public class BomberMan extends Character {
    /**
     * The Steps.
     */
    public BlockingQueue<Cell> steps = new LinkedBlockingQueue<Cell>();
    /**
     * The Current cell.
     */
    public Cell currentCell;

    @Override
    public void run() {
        while (!Thread.currentThread().interrupted()) {
            while (this.steps.isEmpty()) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < this.steps.size(); i++) {
            try {
                Cell nextCell = this.steps.take();
                synchronized (nextCell) {
                    synchronized (this.currentCell) {
                        if (nextCell.getStatus() == CellEnum.FREE) {
                            nextCell.setStatus(CellEnum.USED_BY_BOMBERMAN);
                            nextCell.setCharacter(this);
                            this.currentCell.setStatus(CellEnum.FREE);
                            this.currentCell.setCharacter(null);
                            this.currentCell = nextCell;
                        } else if (nextCell.getStatus() == CellEnum.USED_BY_MONSTER) {
                            this.currentCell.setStatus(CellEnum.FREE);
                            this.currentCell.setCharacter(null);
                            this.currentCell = nextCell;
                            this.interrupt();
                            return;
                        } else {
                            this.steps.clear();
                            return;
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
