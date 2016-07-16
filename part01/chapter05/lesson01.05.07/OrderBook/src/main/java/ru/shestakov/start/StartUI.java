package ru.shestakov.start;

import ru.shestakov.services.ConsoleInput;
import ru.shestakov.services.Input;

import java.io.IOException;

public class StartUI {

    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public void init(OrderBook orderBook) throws IOException {
        do {
            orderBook.parse(this.input.ask("input path to xml-file:"));
        } while(!orderBook.isParsed());
        orderBook.showParseTime();
    }

    public static void main(String[] args) throws IOException {
        OrderBook orderBook = new OrderBook();
        Input input = new ConsoleInput();
        new StartUI(input).init(orderBook);
    }

}
