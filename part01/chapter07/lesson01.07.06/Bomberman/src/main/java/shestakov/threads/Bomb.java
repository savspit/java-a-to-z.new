package shestakov.threads;

import shestakov.models.Block;
import shestakov.services.BlockEnum;

import java.util.concurrent.Callable;

public class Bomb implements Callable {
    public Block[][] board;
    public Block block;
    public int power = 3;
    public int timeUntilBoom = 3000;

    public Bomb(Block[][] board, Block block) {
        this.board = board;
        this.block = block;
    }

    public Object call() throws Exception {
        Thread.sleep(this.timeUntilBoom);
        return doBigBadaboom();
    }

    public boolean doBigBadaboom() {
        return bomberManDiedOnX() || bomberManDiedOnY();
    }

    public boolean bomberManDiedOnX() {
        for (int i = this.block.getX()-1; i<this.power; i++) {
            Block curBlock = this.board[i][this.block.getY()];
            if (curBlock != null) {
                if (curBlock.getStatus() == BlockEnum.USED_BY_BOMBERMAN) {
                    curBlock.setStatus(BlockEnum.FREE);
                    return true;
                } else if (curBlock.getStatus() == BlockEnum.USED_BY_BRICK_BLOCK) {
                    curBlock.setStatus(BlockEnum.FREE);
                } else if (curBlock.getStatus() == BlockEnum.USED_BY_MONSTER) {
                    curBlock.setStatus(BlockEnum.FREE);
                    curBlock.getMonster().setKilled(true);
                }
            }
        }
        return false;
    }

    public boolean bomberManDiedOnY() {
        for (int i = this.block.getY()-1; i<this.power; i++) {
            Block curBlock = this.board[i][this.block.getX()];
            if (curBlock != null) {
                if (curBlock.getStatus() == BlockEnum.USED_BY_BOMBERMAN) {
                    curBlock.setStatus(BlockEnum.FREE);
                    return true;
                } else if (curBlock.getStatus() == BlockEnum.USED_BY_BRICK_BLOCK) {
                    curBlock.setStatus(BlockEnum.FREE);
                } else if (curBlock.getStatus() == BlockEnum.USED_BY_MONSTER) {
                    curBlock.setStatus(BlockEnum.FREE);
                    curBlock.getMonster().setKilled(true);
                }
            }
        }
        return false;
    }

}
