package ru.shestakov.start;

import java.util.Scanner;

/**
 * Init ConsoleInput class
 */
//public class ConsoleInput implements Input, InputRange {
public class ConsoleInput2 {
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

    /**
     * Reads input user`s value
     * @param question
     * @return
     */
    /*public long ask(String question, boolean check) {
        System.out.print(question);
        return Long.parseLong(scanner.nextLine());
    }*/

    /**
     * Validate value with exception
     * @param question
     * @param range
     * @return
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for(int value : range) {
            if(value == key) {
                exist = true;
                break;
            }
        }
        if (exist) { return key; } else { throw new MenuOutException("Out of menu range."); }
    }

}