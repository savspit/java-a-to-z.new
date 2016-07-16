package ru.shestakov.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.shestakov.models.Book;
import ru.shestakov.models.Order;
import ru.shestakov.services.BookCompAsc;
import ru.shestakov.services.KeySort;
import ru.shestakov.services.OperationEnum;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class HandlerSAX extends DefaultHandler {

    private TreeMap<String, Book> ts = new TreeMap<>(new BookCompAsc());

    public void convert() {
        for (Map.Entry<String, Book> bk : ts.entrySet()) {
            bk.getValue().convert();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("ADDORDER")) {
            addOrder(attributes);
        }
        if (qName.equalsIgnoreCase("DELETEORDER")) {
            delOrder(attributes);
        }
    }

    public void addOrder(Attributes at) {
        Book book = ts.get(at.getValue("book"));
        if (book == null) {
            Book bk = new Book(at.getValue("book"));
            bk.add(at.getValue("operation").charAt(0) == 'S' ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(at.getValue("price")), Integer.parseInt(at.getValue("volume")), Integer.parseInt(at.getValue("orderId")));
            ts.put(at.getValue("book"), bk);
        } else {
            book.add(at.getValue("operation").charAt(0) == 'S' ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(at.getValue("price")), Integer.parseInt(at.getValue("volume")), Integer.parseInt(at.getValue("orderId")));
            ts.put(at.getValue("book"), book);
        }
    }

    public void delOrder(Attributes at) {
        Book book = ts.get(at.getValue("book"));
        book.remove(Integer.parseInt(at.getValue("orderId")));
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
