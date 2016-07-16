package shestakov.start;

import shestakov.models.CacheManager;
import shestakov.services.ConsoleInput;
import shestakov.services.Input;

import java.io.IOException;

public class StartUI {
    private Input input;

    public StartUI(Input input) {
        this.input = input;
    }

    public void init(CacheManager cm) throws IOException {
        while (true) {
            String key = this.input.ask("input key or 'e' to exit:");
            if ("e".equals(key)) { break; }
            cm.checkKey(key);
            cm.showValue(key);
        }
    }

    public static void main(String[] args) throws IOException {
        CacheManager cm = new CacheManager();
        Input input = new ConsoleInput();
        new StartUI(input).init(cm);
    }

}
