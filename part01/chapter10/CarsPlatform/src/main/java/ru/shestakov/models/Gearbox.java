package ru.shestakov.models;

public class Gearbox {
    private int id;
    private String name;

    public Gearbox() {
    }

    public Gearbox(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
