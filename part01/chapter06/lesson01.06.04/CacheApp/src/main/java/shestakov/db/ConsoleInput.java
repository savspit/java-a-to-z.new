package shestakov.db;

import java.io.File;
import java.util.Scanner;

public class ConsoleInput implements Input{
    private Scanner scanner = new Scanner(System.in);

    public String ask(String question) {
        System.out.print(question);
        KeyMaskChecker kms = new KeyMaskChecker();
        while (true) {
            try {
                // checking need to exit & key is correct
                String nextLine = scanner.nextLine();
                if(!"e".equals(nextLine) && !kms.keyIsCorrect(nextLine)) {
                    throw new InputException("key have wrong format. please enter validate data again\n");
                }
                // checking file is correct
                File txtFile = new File(nextLine);
                if(!txtFile.exists()) {
                    throw new InputException("file is not exists");
                }
                if(txtFile.isDirectory()) {
                    throw new InputException("input path is a directory, not the file");
                }
                if (!txtFile.canRead()) {
                    throw new InputException("can`t read the file. check your rights");
                }
                return nextLine;
            } catch (InputException ie) {
                System.out.print(String.format("%s %s", ie.getMessage(), question));
            }
        }
    }
}
