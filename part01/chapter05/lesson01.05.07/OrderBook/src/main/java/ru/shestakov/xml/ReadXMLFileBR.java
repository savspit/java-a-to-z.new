package ru.shestakov.xml;

import ru.shestakov.models.Book;
import ru.shestakov.models.Order;
import ru.shestakov.services.BookCompAsc;
import ru.shestakov.services.KeySort;
import ru.shestakov.services.OperationEnum;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ReadXMLFileBR {

    private File xmlFile;
    private TreeMap<String, Book> ts = new TreeMap<>(new BookCompAsc());

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public void convert() {
        for (Map.Entry<String, Book> bk : ts.entrySet()) {
            bk.getValue().convert();
        }
    }

    public String[] splitStr(String s, int index) {
        String[] arr = new String[index];
        char regex = '\"';
        char[] ch = s.toCharArray();
        boolean firstRegex = false;
        boolean secondRegex = false;
        int firstIndex = 0;
        int secondIndex = 0;
        int counter = 0;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == regex && !firstRegex && !secondRegex) {
                firstIndex = i + 1;
                firstRegex = true;
            } else if (ch[i] == regex && firstRegex && !secondRegex) {
                secondIndex = i;
                secondRegex = true;
            }
            if (firstRegex && secondRegex) {
                arr[counter++] = new String(Arrays.copyOfRange(ch, firstIndex, secondIndex));
                firstRegex = false;
                secondRegex = false;
            }
        }
        return arr;
    }

    public void parse() {
        try (BufferedReader reader = new BufferedReader(new FileReader(xmlFile))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (line.charAt(1) == 'A') {
                    String[] linesA = splitStr(line, 5);
                    Book book = ts.get(linesA[0]);
                    if (book == null) {
                        Book bk = new Book(linesA[0]);
                        bk.add(linesA[1].charAt(0) == 'S' ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(linesA[2]), Integer.parseInt(linesA[3]), Integer.parseInt(linesA[4]));
                        ts.put(linesA[0], bk);
                    } else {
                        book.add(linesA[1].charAt(0) == 'S' ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(linesA[2]), Integer.parseInt(linesA[3]), Integer.parseInt(linesA[4]));
                        ts.put(linesA[0], book);
                    }
                } else if (line.charAt(1) == 'D') {
                    String[] linesD = splitStr(line, 2);
                    Book book = ts.get(linesD[0]);
                    book.remove(Integer.parseInt(linesD[1]));
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void print() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Book> currentEntry : ts.entrySet()) {
            builder.append(String.format("Order book: ${%s}\n", currentEntry.getKey()));
            builder.append("Volume@Price - Volume@Price\n");
            Iterator itSell = currentEntry.getValue().getSellOrders().entrySet().iterator();
            Iterator itBuy = currentEntry.getValue().getBuyOrders().entrySet().iterator();
            while (itSell.hasNext() || itBuy.hasNext()) {
                Map.Entry<KeySort, Order> pairS = itSell.hasNext() ? (Map.Entry<KeySort, Order>) itSell.next() : null;
                Map.Entry<KeySort, Order> pairB = itBuy.hasNext() ? (Map.Entry<KeySort, Order>) itBuy.next() : null;
                if (pairS != null && pairB != null) {
                    builder.append(String.format("%d@%.2f - %d@%.2f \n", pairB.getValue().getVolume(), pairB.getValue().getPrice(), pairS.getValue().getVolume(), pairS.getValue().getPrice()));
                }
                if (pairS == null && pairB != null) {
                    builder.append(String.format("%d@%.2f - ------- \n", pairB.getValue().getVolume(), pairB.getValue().getPrice()));
                }
                if (pairS != null && pairB == null) {
                    builder.append(String.format("------- - %d@%.2f \n", pairS.getValue().getVolume(), pairS.getValue().getPrice()));
                }
            }
        }
        System.out.println(builder.toString());
    }
}




