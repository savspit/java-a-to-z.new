package ru.shestakov.models;

public class Task extends Item {

    public Task(String name, String desc, long create) {
        this.name = name;
        this.description = desc;
        this.create = create;
    }

    public String calculatePrice() {
        return "100%";
    }

}