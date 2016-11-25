package ru.shestakov.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
    private static final Logger Log = LoggerFactory.getLogger(User.class);
    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
