package shestakov.models;

import shestakov.services.BlockEnum;
import shestakov.services.DirectionEnum;
import shestakov.start.World;

import java.util.concurrent.Callable;

public class Monster implements Callable{
    public boolean killed = false;
    public DirectionEnum direction = DirectionEnum.LEFT;
    public final int speed = 1;
    public World world;
    public Block curBlock;

    public void setKilled(boolean killed) {
        this.killed = killed;
    }

    public Boolean call() throws Exception {
        while (!Thread.currentThread().isInterrupted()) {
            while (!killed) {
                BlockEnum nextBlock = this.world.checkNextBlock(this.speed, this.direction, this.curBlock);
                if (nextBlock == BlockEnum.USED_BY_MONSTER || nextBlock == BlockEnum.USED_BY_STEEL_BLOCK || nextBlock == BlockEnum.USED_BY_BRICK_BLOCK) {
                    Thread.sleep(500);
                    changeDirection();
                    continue;
                } else if (nextBlock == BlockEnum.USED_BY_BOMBERMAN) {
                    return true;
                } else {
                    this.curBlock = this.world.go(this.speed, this.direction, this.curBlock);
                }
            }
        }
        return false;
    }

    public void changeDirection() {
        if (this.direction == DirectionEnum.DOWN) {
            this.direction = DirectionEnum.LEFT;
        } else if (this.direction == DirectionEnum.LEFT) {
            this.direction = DirectionEnum.UP;
        } else if (this.direction == DirectionEnum.UP) {
            this.direction = DirectionEnum.RIGHT;
        } else if (this.direction == DirectionEnum.RIGHT) {
            this.direction = DirectionEnum.DOWN;
        }
    }
}
