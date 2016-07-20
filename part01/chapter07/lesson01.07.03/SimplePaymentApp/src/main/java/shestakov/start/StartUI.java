package shestakov.start;

import shestakov.models.UsersStorage;

public class StartUI {

    public static void main(String[] args) {
        UsersStorage us = new UsersStorage();
        new StartUI().init(us);
    }

    public void init(UsersStorage us) {

    }
}
