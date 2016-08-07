package shestakov.start;

import shestakov.services.KeysValidator;

import java.util.concurrent.ExecutionException;

/**
 * The type Start ui.
 */
public class StartUI {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        KeysValidator kv = new KeysValidator();
        if (kv.parsedSuccessful(args)) {
            SearchApp sa = new SearchApp(kv);
            new StartUI().init(sa);
        }
    }

    /**
     * Init.
     *
     * @param sa the sa
     * @throws ExecutionException   the execution exception
     * @throws InterruptedException the interrupted exception
     */
    public void init(SearchApp sa) throws ExecutionException, InterruptedException {
        sa.startSearching();
    }
}
