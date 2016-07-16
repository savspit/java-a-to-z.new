package ru.shestakov.start;

import ru.shestakov.models.Chip;
import ru.shestakov.models.Player;

public class GameBoard {

    private int boardSize;
    private Chip[][] field;
    private Player[] players = new Player[2];
    private Chip[] chips = new Chip[2];

    public GameBoard(int boardSize) {
        this.boardSize = boardSize;
        this.field = new Chip[boardSize][boardSize];
    }

    public int getBoardSize() {
        return this.boardSize;
    }

    public Chip getChipByValue(String value) {
        Chip result = null;
        for(int i = 0; i < this.chips.length; i++) {
            if(this.chips[i] != null && this.chips[i].getValue().equals(value) ) {
                result = this.chips[i];
                break;
            }
        }
        return result;
    }

    public Chip getActiveChip() {
        Chip result = null;
        for(int i = 0; i < this.chips.length; i++) {
            if(this.chips[i] != null && this.chips[i].getActive()) {
                result = this.chips[i];
                break;
            }
        }
        return result;
    }

    public Player getNextPlayer() {
        Player result = null;
        for(int i = 0; i < this.players.length; i++) {
            if(this.players[i] != null && this.players[i].getChip().getActive()) {
                result = this.players[i];
                break;
            }
        }
        return result;
    }

    public String getFreeCell() {
        int x = (int) (Math.random() * (this.boardSize));
        int y = (int) (Math.random() * (this.boardSize));
        while (field[x][y] != null) {
            x = (int) (Math.random() * (this.boardSize));
            y = (int) (Math.random() * (this.boardSize));
        }
        return String.valueOf(x) + String.valueOf(y);
    }

    public void setPlayer(Player player) {
        for(int i = 0; i < this.players.length; i++) {
            if(this.players[i] == null) {
                this.players[i] = player;
                break;
            }
        }
    }

    public void changeChipsActivity() {
        for(int i = 0; i < this.chips.length; i++) {
            if(this.chips[i] != null) {
                this.chips[i].setActive(!this.chips[i].getActive());
            }
        }
    }

    public void initialize() {
        this.chips[0] = new Chip("X", true);
        this.chips[1] = new Chip("O");
    }

    public void print() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                System.out.print((field[i][j] == null ? "°" : field[i][j].getValue()) + "\t");
            }
            System.out.println();
        }
    }

    public boolean isNextStep() {
        boolean result = isGameOver();
        changeChipsActivity();
        return result;
    }

    public boolean isGameOver() {
        return boardIsFull() || playerIsWin();
    }

    public boolean boardIsFull() {
        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (field[i][j] == null) {
                    return false;
                };
            }
        }
        return true;
    }

    public boolean playerIsWin() {
        return winOnFieldString() || winOnFieldColumn() || winOnFieldDiagonal();
    }

    public boolean winOnFieldColumn() {
        int count = 0;
        for (int j = 0; j < this.boardSize; j++) {
            count = 0;
            for (int i = 0; i < this.boardSize; i++) {
                if (field[i][j] != null && field[i][j].equals(getActiveChip())) {
                    count++;
                }
            }
            if(count == this.boardSize) { return true; }
        }
        return false;
    }

    public boolean winOnFieldString() {
        int count = 0;
        for (int i = 0; i < this.boardSize; i++) {
            count = 0;
            for (int j = 0; j < this.boardSize; j++) {
                if (field[i][j] != null && field[i][j].equals(getActiveChip())) {
                    count++;
                }
            }
            if(count == this.boardSize) { return true; }
        }
        return false;
    }

    public boolean winOnFieldDiagonal() {

        int count = 0;
        for (int i = 0; i < this.boardSize; i++) {
            if (field[i][i] != null && field[i][i].equals(getActiveChip())) {
                count++;
            }
        }
        if(count == this.boardSize) { return true; }

        count = 0;
        for (int i = 0, j = this.boardSize-1; i < this.boardSize; i++, j--) {
            if (field[i][j] != null && field[i][j].equals(getActiveChip())) {
                count++;
            }
        }
        if(count == this.boardSize) { return true; } else { return false;}
    }

    public void move(String cell) {
        int x = Character.getNumericValue(cell.charAt(0));
        int y = Character.getNumericValue(cell.charAt(1));
        if (field[x][y] == null) {
            field[x][y] = getActiveChip();
        } else {
            System.out.println("Cell is busy");
        }
    }
}
