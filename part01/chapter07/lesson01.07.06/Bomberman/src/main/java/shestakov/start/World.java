package shestakov.start;

import javafx.concurrent.Worker;
import shestakov.models.Block;
import shestakov.models.BomberMan;
import shestakov.models.Monster;
import shestakov.services.BlockEnum;
import shestakov.services.DirectionEnum;

import java.util.List;
import java.util.concurrent.*;

public class World {
    public Block[][] board;
    public int width;
    public int height;
    public BomberMan hero;
    public List<Future> monsters;
    public int numberOfMonsters;
    public int numberOfBrickBlocks;
    public int level;

    public World() {
        this(1, 31, 13, 5, 10);
    }

    public World(int level, int width, int height, int numberOfMonsters, int numberOfBrickBlocks) {
        this.board = new Block[width][height];
        this.width = width;
        this.height = height;
        this.numberOfMonsters = numberOfMonsters;
        this.numberOfBrickBlocks = numberOfBrickBlocks;
        this.level = level;
    }

    public void setHero(BomberMan hero) {
        this.hero = hero;
    }

    public BomberMan getHero() {
        return hero;
    }

    public void createObjects() {
        fillBoardByFreeBlocks();
        putBomberManBlock();
        putSteelBlocksAround();
        putBrickBlocks();
    }

    public void fillBoardByFreeBlocks() {
        for (int i=0; i<this.width; i++) {
            for (int j=0; j<this.height; j++) {
                this.board[i][j] = new Block(i,j);
            }
        }
    }

    public void putBomberManBlock() {
        this.board[1][1].setStatus(BlockEnum.USED_BY_BOMBERMAN);
    }

    public void putSteelBlocksAround() {
        for (int i=0; i<this.height; i++) {
            for (int j=0; j<this.width; j++) {
                if(i == 0 || i == this.height-1 || ((i != 0 && i != this.height-1) && (j == 0 || j == this.width))) {
                    this.board[i][j].setStatus(BlockEnum.USED_BY_STEEL_BLOCK);
                }
            }
        }
    }

    public void putBrickBlocks() {
        for(int i=0; i<this.numberOfBrickBlocks+this.level; i++) {
            int x = (int) (Math.random() * (this.width));
            int y = (int) (Math.random() * (this.height));
            while (this.board[x][y].getStatus() != BlockEnum.FREE) {
                x = (int) (Math.random() * (this.width));
                y = (int) (Math.random() * (this.height));
            }
            this.board[x][y].setStatus(BlockEnum.USED_BY_BRICK_BLOCK);
        }
    }

    public void releaseMonsters() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor ex = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfMonsters);
        for(int i=0; i<numberOfMonsters; i++) {
            int x = (int) (Math.random() * (this.width));
            int y = (int) (Math.random() * (this.height));
            while (this.board[x][y].getStatus() != BlockEnum.FREE) {
                x = (int) (Math.random() * (this.width));
                y = (int) (Math.random() * (this.height));
            }
            this.board[x][y].setStatus(BlockEnum.USED_BY_MONSTER);
            Monster monster = new Monster();
            this.board[x][y].setMonster(monster);
            this.monsters.add(ex.submit(monster));
        }
        for (Future curMonster : this.monsters) {
            Boolean bombermanDied = (Boolean) curMonster.get();
            if (bombermanDied.booleanValue()) {
                ex.shutdown();
            }
        }
    }

    public Block go(int speed, DirectionEnum direction, Block curBlock) {
        if (direction == DirectionEnum.DOWN) {
            return this.board[curBlock.getY()+speed][curBlock.getX()];
        } else if (direction == DirectionEnum.LEFT) {
            return this.board[curBlock.getY()][curBlock.getX()-speed];
        } else if (direction == DirectionEnum.UP) {
            return this.board[curBlock.getY()-speed][curBlock.getX()];
        } else if (direction == DirectionEnum.RIGHT) {
            return this.board[curBlock.getY()][curBlock.getX() + speed];
        } else { return curBlock; }
    }

    public BlockEnum checkNextBlock(int speed, DirectionEnum direction, Block curBlock) {
        if (direction == DirectionEnum.DOWN) {
            return this.board[curBlock.getY()+speed][curBlock.getX()].getStatus();
        } else if (direction == DirectionEnum.LEFT) {
            return this.board[curBlock.getY()][curBlock.getX()-speed].getStatus();
        } else if (direction == DirectionEnum.UP) {
            return this.board[curBlock.getY()-speed][curBlock.getX()].getStatus();
        } else if (direction == DirectionEnum.RIGHT) {
            return this.board[curBlock.getY()][curBlock.getX() + speed].getStatus();
        } else { return BlockEnum.FREE; }
    }

}
