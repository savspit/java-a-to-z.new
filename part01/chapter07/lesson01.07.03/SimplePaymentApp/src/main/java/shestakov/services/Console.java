package shestakov.services;

import shestakov.models.Account;

public class Console {

    public void show(Account account, String value) {
        System.out.println(String.format("%s: %s", account, value));
    }

}
