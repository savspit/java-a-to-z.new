package shestakov.models;

import shestakov.services.BlockEnum;

public class Block {
    private BlockEnum status;
    public int x;
    public int y;
    public Monster monster;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.status = BlockEnum.FREE;
    }

    public Monster getMonster() {
        return monster;
    }

    public BlockEnum getStatus() {
        return status;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public void setStatus(BlockEnum status) {
        this.status = status;
    }

}
