package ru.shestakov.start;

import java.util.Scanner;

/**
 * Init ValidateInput class
 */
public class ValidateInput2 extends ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Reads input user`s value
     * @param question
     * @return
     */
    public String ask(String question) {

        System.out.print(question);
        return scanner.nextLine();

    }

    public long ask(String question, boolean check) {

        System.out.print(question);
            try {
                return Long.parseLong(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                System.out.println("Incorrect value of task`s date. Please, enter correct date again.");
            }
        return -1;
    }

    /**
     * Validate value with exception
     * @param question
     * @param range
     * @return
     */
    public int ask (String question, int[] range) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                invalid = false;
            } catch (MenuOutException moe) {
                System.out.println("Please, select key from menu.");
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}