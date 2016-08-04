package shestakov.start;

import shestakov.services.KeysValidator;

import java.util.concurrent.ExecutionException;

public class StartUI {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String[] args1 = new String[3];
        args1[0] = "-f";
        args1[1] = "tmp";
        args1[2] = "-s";

        KeysValidator kv = new KeysValidator();
        if (kv.parsedSuccessful(args1)) {
            SearchApp sa = new SearchApp(kv);
            new StartUI().init(sa);
        }
    }

    public void init(SearchApp sa) throws ExecutionException, InterruptedException {
        sa.startSearching();
    }
}
