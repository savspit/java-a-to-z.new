package shestakov.start;

import shestakov.services.KeysValidator;

public class StartUI {

    public static void main(String[] args) {
        KeysValidator kv = new KeysValidator();
        if (kv.parsedSuccessful(args)) {
            SearchApp sa = new SearchApp();
            sa.setKeys(kv.getKeys());
            new StartUI().init(sa);
        }
    }

    public void init(SearchApp sa) {
        sa.startSearching();
    }
}
