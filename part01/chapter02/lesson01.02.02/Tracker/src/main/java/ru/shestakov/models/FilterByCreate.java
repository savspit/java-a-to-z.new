package ru.shestakov.models;

public class FilterByCreate extends Filter {

    public long value;

    public FilterByCreate(long create) {
        this.value = create;
    }

    public Item checkIn (Item[] items) {

        Item result = null;
        for (Item item : items) {
            if (item != null && item.getCreate() == value) {
                result = item;
                break;
            }
        }
        return result;

    }

}
