package ru.shestakov.models;

public class Cell {

    public String x;
    public int y;
    public boolean inUse;

    public Cell(String x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(boolean inUse) {
        this.inUse = inUse;
    }

    public boolean getInUse() {
        return this.inUse;
    }

    public String getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

}
