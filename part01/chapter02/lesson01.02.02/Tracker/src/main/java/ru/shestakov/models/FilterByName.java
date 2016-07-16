package ru.shestakov.models;

public class FilterByName extends Filter {

    public String value;

    public FilterByName(String name) {
        this.value = name;
    }

    public Item checkIn (Item[] items) {

        Item result = null;
        for (Item item : items) {
            if (item != null && item.getName().contains(value)) {
                result = item;
                break;
            }
        }
        return result;

    }

}
