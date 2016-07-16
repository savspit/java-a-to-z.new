package ru.shestakov.models;

public class Chip {
    private String value;
    private boolean active;

    public Chip(String value) {
        this.value = value;
    }

    public Chip(String value, boolean active) {
        this.value = value;
        this.active = active;
    }

    public String getValue() {
        return this.value;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
