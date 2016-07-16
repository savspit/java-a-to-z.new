package ru.shestakov.start;

import ru.shestakov.models.*;

public class ChessBoard {

    private Cell[] cells = new Cell[64];
    private Figure[] figures = new Figure[32];
    private String[] Xs = {"a", "b", "c", "d", "e", "f", "g", "h"};

    public void fill() {

        addCells();
        addFigures();

    }

    public void addCells() {
        int z = 0;
        for(String x : Xs) {
            for (int y = 1; y <= 8; y++) {
                cells[z] = new Cell(x, y);
                z++;
            }
        }
    }

    public void addFigures() {

        for (int x = 0; x < figures.length-1; x++) {
            figures[x] = new Figure("Фигура", cells[x]);
            cells[x].setInUse(true);
        }

    }

    public void go(String x1, int y1, String x2, int y2) {

        Cell cell1 = findCell(x1, y1);
        Cell cell2 = findCell(x2, y2);

        if (cell2.getInUse()) {
            System.out.println("Cell " + cell2 + " in use");
        } else {
            for (int x=0; x < figures.length; x++) {
                if(figures[x].getCell().equals(cell1)) {
                    figures[x].setCell(cell2);
                    cell1.setInUse(false);
                    cell2.setInUse(true);
                    break;
                }
            }
        }
    }

    public Cell findCell(String x, int y) {
        for (int z=0; z<cells.length; z++) {
            if(cells[z].getX().equals(x) && cells[z].getY() == y) {
                return cells[z];
            }
        }
        System.out.println("Cell " + x + y + " does not exists");
        return null;
    }

    public void show() {
        for (Cell cell : cells) {
            if (cell.getInUse()) {
                for (Figure figure : figures) {
                    System.out.println("Cell " + cell.getX() + cell.getY() +" in use by " + figure.getType());
                    break;
                }
            } else {
                System.out.println("Cell " + cell.getX() + cell.getY() +" not in use");
            }
        }
    }

}
