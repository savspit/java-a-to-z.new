package shestakov.start;

import shestakov.services.KeysValidator;

public class StartUI {

    public static void main(String[] args) {

        String[] myArgs = new String[5];
        myArgs[0] = "-f";
        myArgs[1] = "sdag";
        /*myArgs[2] = "-p";
        myArgs[3] = "C:\\sadf";*/
        //myArgs[4] = "-s";

        KeysValidator kv = new KeysValidator();
        //if (kv.parsedSuccessful(args)) {
        if (kv.parsedSuccessful(myArgs)) {
            SearchApp sa = new SearchApp();
            sa.setKeys(kv.getKeys());
            new StartUI().init(sa);
        }
    }

    public void init(SearchApp sa) {
        sa.startBigSearch();
    }
}
