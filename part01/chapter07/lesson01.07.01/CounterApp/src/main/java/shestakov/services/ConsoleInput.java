package shestakov.services;

import java.util.Scanner;

public class ConsoleInput implements Input{
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();

    }
}
