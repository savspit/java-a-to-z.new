package ru.shestakov.start;

import ru.shestakov.templates.InputException;
import java.util.Scanner;

public class ConsoleInput implements Input{

    private Scanner scanner = new Scanner(System.in);

    public String ask(String question, int boardSize) {
        System.out.print(question);
        while (true) {
            try {
                String nextLine = scanner.nextLine();
                if(nextLine.length() != 2) {
                    throw new InputException("Wrong length. ");
                } else if(Character.getNumericValue(nextLine.charAt(0))>boardSize || Character.getNumericValue(nextLine.charAt(1))>boardSize) {
                    throw new InputException("Big number. ");
                }
                else {
                    Long.parseLong(nextLine);
                }
                return nextLine;
            } catch (NumberFormatException nfe) {
                System.out.print("Incorrect value. " + question);
            } catch (InputException ie) {
                System.out.print(ie.getMessage() + question);
            }
        }
    }
}
