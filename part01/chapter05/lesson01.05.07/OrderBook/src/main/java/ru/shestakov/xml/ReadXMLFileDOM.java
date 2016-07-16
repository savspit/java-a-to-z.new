package ru.shestakov.xml;

import org.w3c.dom.*;
import ru.shestakov.models.Book;
import ru.shestakov.models.Order;
import ru.shestakov.services.BookCompAsc;
import ru.shestakov.services.KeySort;
import ru.shestakov.services.OperationEnum;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ReadXMLFileDOM {

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

    public void parse() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName(doc.getDocumentElement().getChildNodes().item(1).getNodeName());
            for (int i=0; i <nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    if (node.getNodeName().equals("AddOrder")) {
                        NamedNodeMap nnm = node.getAttributes();
                        Book book = ts.get(nnm.getNamedItem("book").getNodeValue());
                        if (book == null) {
                            Book bk = new Book(nnm.getNamedItem("book").getNodeValue());
                            bk.add(nnm.getNamedItem("operation").getNodeValue().equals("SELL") ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(nnm.getNamedItem("price").getNodeValue()), Integer.parseInt(nnm.getNamedItem("volume").getNodeValue()), Integer.parseInt(nnm.getNamedItem("orderId").getNodeValue()));
                            ts.put(nnm.getNamedItem("book").getNodeValue(), bk);
                        } else {
                            book.add(nnm.getNamedItem("operation").getNodeValue().equals("SELL") ? OperationEnum.SELL : OperationEnum.BUY, Float.parseFloat(nnm.getNamedItem("price").getNodeValue()), Integer.parseInt(nnm.getNamedItem("volume").getNodeValue()), Integer.parseInt(nnm.getNamedItem("orderId").getNodeValue()));
                            ts.put(nnm.getNamedItem("book").getNodeValue(), book);
                        }
                    } else if (node.getNodeName().equals("DeleteOrder")) {
                        NamedNodeMap nnm = node.getAttributes();
                        Book book = ts.get(nnm.getNamedItem("book").getNodeValue());
                        book.remove(Integer.parseInt(nnm.getNamedItem("orderId").getNodeValue()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
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
