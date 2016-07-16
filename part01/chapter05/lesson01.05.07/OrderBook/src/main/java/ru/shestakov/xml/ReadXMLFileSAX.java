package ru.shestakov.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class ReadXMLFileSAX {

    private File xmlFile;
    private HandlerSAX handler;

    public void setXmlFile(File xmlFile) {
        this.xmlFile = xmlFile;
    }

    public void convert() {
        handler.convert();
    }

    public void print() {
        handler.print();
    }

    public void parse() {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        handler = new HandlerSAX();
        try {
            SAXParser saxParser = saxFactory.newSAXParser();
            saxParser.parse(xmlFile, handler);
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
