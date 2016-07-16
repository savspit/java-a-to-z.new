package ru.shestakov.start;

import ru.shestakov.xml.ReadXMLFileBR;
import ru.shestakov.xml.ReadXMLFileDOM;
import ru.shestakov.xml.ReadXMLFileSAX;

import java.io.File;
import java.io.IOException;

public class OrderBook {

    long startTime;
    boolean isParsed;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setParsed(boolean parsed) {
        isParsed = parsed;
    }

    public void parse(File xmlFile) throws IOException {
        setStartTime(System.currentTimeMillis());
        ReadXMLFileBR xmlParser = new ReadXMLFileBR();
        //ReadXMLFileSAX xmlParser = new ReadXMLFileSAX();
        //ReadXMLFileDOM xmlParser = new ReadXMLFileDOM();
        xmlParser.setXmlFile(xmlFile);
        xmlParser.parse();
        xmlParser.convert();
        xmlParser.print();
        setParsed(true);
    }

    public void showParseTime() {
        System.out.println(String.format("parsing time is: %d ms", System.currentTimeMillis()-this.startTime));
    }

    public boolean isParsed() {
        return this.isParsed;
    }

}
