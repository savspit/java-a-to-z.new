package shestakov.services;

import shestakov.models.User;

public class Buffer {

    public void show(User user, String value) {
        System.out.println(String.format("%s: %s", user, value));
    }

}
