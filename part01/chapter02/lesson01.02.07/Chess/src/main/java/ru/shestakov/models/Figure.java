package ru.shestakov.models;

public class Figure {

    public String type;
    public Cell cell;

    public Figure(Cell cell) {
        this.cell = cell;
    }

    public Figure(String type, Cell cell) {
        this.type = type;
        this.cell = cell;
    }

    public Cell getCell() {
        return this.cell;
    }

    public String getType() {
        return this.type;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }


}
