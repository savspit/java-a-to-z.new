package ru.shestakov.services;

import java.util.Comparator;

// from low to hi
public class BookCompAsc implements Comparator<String> {

    @Override
    public int compare(String n1, String n2) {
        return n1.compareTo(n2);
    }

}
