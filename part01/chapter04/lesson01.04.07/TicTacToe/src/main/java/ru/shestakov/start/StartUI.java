package ru.shestakov.start;

import ru.shestakov.models.Player;

public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public void init(GameBoard gameBoard) {

        showInfo();

        int boardSize = gameBoard.getBoardSize();
        do {
            if(gameBoard.getNextPlayer().getIsBot()) {
                gameBoard.move(gameBoard.getFreeCell());
                gameBoard.print();
            } else {
                gameBoard.move(this.input.ask("your move:", boardSize));
            }
        } while(!gameBoard.isNextStep());

        System.out.println("Game over");

    }

    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard(3);
        gameBoard.initialize();

        Player playerBot = new Player();
        Player playerHuman = new Player();

        playerBot.setIsBot(true);

        playerBot.setChip(gameBoard.getChipByValue("X"));
        playerHuman.setChip(gameBoard.getChipByValue("O"));

        gameBoard.setPlayer(playerBot);
        gameBoard.setPlayer(playerHuman);

        Input input = new ConsoleInput();

        new StartUI(input).init(gameBoard);

    }

    public void showInfo() {
        System.out.println("Your move is number in format XY, where X - is number of X-coordinates and Y - is number of Y-coordinates.");
        System.out.println("Example of field 3 x 3:");
        System.out.println("00" + "\t" + "01" + "\t" + "02");
        System.out.println("10" + "\t" + "11" + "\t" + "12");
        System.out.println("20" + "\t" + "21" + "\t" + "22");
        System.out.println("Good luck!");
        System.out.println("");
    }
}
