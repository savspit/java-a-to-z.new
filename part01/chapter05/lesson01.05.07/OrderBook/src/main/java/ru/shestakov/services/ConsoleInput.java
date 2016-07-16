package ru.shestakov.services;

import java.io.File;
import java.util.Scanner;

public class ConsoleInput implements Input{

    private Scanner scanner = new Scanner(System.in);

    public File ask(String question) {
        System.out.print(question);
        while (true) {
            try {
                String nextLine = scanner.nextLine();
                File xmlFile = new File(nextLine);

                if(!xmlFile.exists()) {
                    throw new InputException("file is not exists");
                }
                if(xmlFile.isDirectory()) {
                    throw new InputException("input path is a directory, not the file");
                }
                if (!xmlFile.canRead()) {
                    throw new InputException("can`t read the file. check your rights");
                }
                return xmlFile;
            } catch (InputException ie) {
                System.out.print(String.format("%s %s", ie.getMessage(), question));
            }
        }
    }
}
