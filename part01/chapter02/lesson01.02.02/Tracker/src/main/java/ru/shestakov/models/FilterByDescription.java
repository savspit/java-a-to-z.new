package ru.shestakov.models;

public class FilterByDescription extends Filter {

    public String value;

    public FilterByDescription(String description) {
        this.value = description;
    }

    public Item checkIn (Item[] items) {

        Item result = null;
        for (Item item : items) {
            if (item != null && item.getDescription().contains(value)) {
                result = item;
                break;
            }
        }
        return result;

    }

}
