package shestakov.start;

import shestakov.models.CounterManager;
import shestakov.services.ConsoleInput;
import shestakov.services.Input;

import java.io.IOException;

public class StartUI {
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public void init(CounterManager cm) throws IOException {
        while (true) {
            String text = this.input.ask("input text or 'e' to exit:");
            if ("e".equals(text)) { break; }
            cm.count(text);
        }
    }

    public static void main(String[] args) throws IOException {
        int timeoutInMillis = 1000;
        CounterManager cm = new CounterManager(timeoutInMillis);
        Input input = new ConsoleInput();
        new StartUI(input).init(cm);
    }

}
