package ru.shestakov.services;

public class User extends Base{

    String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }
}
