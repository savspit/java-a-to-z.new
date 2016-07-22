package shestakov.start;

import shestakov.models.AccountStorage;

public class StartUI {

    public static void main(String[] args) {
        AccountStorage us = new AccountStorage();
        new StartUI().init(us);
    }

    public void init(AccountStorage us) {

    }
}
