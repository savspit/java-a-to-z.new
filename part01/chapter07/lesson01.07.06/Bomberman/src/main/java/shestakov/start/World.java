package shestakov.start;

import shestakov.models.Block;

public class World {
    public Block[][] board;

    public World() {
        this(13,31);
    }

    public World(int x, int y) {
        this.board = new Block[x][y];
    }

    public void fill() {

    }

}
