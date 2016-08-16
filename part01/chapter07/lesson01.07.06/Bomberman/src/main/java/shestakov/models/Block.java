package shestakov.models;

import shestakov.services.BlockEnum;

public class Block {
    private BlockEnum status;
    public int x;
    public int y;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = BlockEnum.FREE;
    }


}
