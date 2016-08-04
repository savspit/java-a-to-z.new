package shestakov.start;

import shestakov.services.KeysValidator;

import java.util.concurrent.ExecutionException;

public class StartUI {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KeysValidator kv = new KeysValidator();
        if (kv.parsedSuccessful(args)) {
            SearchApp sa = new SearchApp(kv);
            new StartUI().init(sa);
        }
    }

    public void init(SearchApp sa) throws ExecutionException, InterruptedException {
        sa.startSearching();
    }
}
