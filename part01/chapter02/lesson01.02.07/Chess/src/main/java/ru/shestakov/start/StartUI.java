package ru.shestakov.start;

import ru.shestakov.models.*;

public class StartUI {

    public static void main(String[] args) {

        ChessBoard chessboard = new ChessBoard();

        new StartUI().init(chessboard);

    }

    public void init(ChessBoard chessboard) {

        chessboard.fill();

        chessboard.go("d", 2, "h", 4);

        chessboard.show();

    }

}
