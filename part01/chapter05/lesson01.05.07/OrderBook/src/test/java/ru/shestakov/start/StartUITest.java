package ru.shestakov.start;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StartUITest {

    @Test
    public void whenExecute10TimesShouldBeAround6000Millis() throws IOException {
        OrderBook orderBook = new OrderBook();
        int howManyTimes = 10;
        long counterMillis = 0;
        for (int i=0; i<howManyTimes; i++) {
            long time = System.currentTimeMillis();
            orderBook.parse(new File("C:\\orders.xml"));
            orderBook.showParseTime();
            counterMillis = counterMillis + (System.currentTimeMillis()-time);
        }
        System.out.println(String.format("\nexec time is ~ %d ms", counterMillis/howManyTimes));
    }

}