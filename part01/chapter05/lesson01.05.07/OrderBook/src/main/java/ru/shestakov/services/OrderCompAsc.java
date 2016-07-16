package ru.shestakov.services;

import java.util.Comparator;

// from low to hi
public class OrderCompAsc implements Comparator<KeySort> {

    @Override
    public int compare(KeySort p1, KeySort p2) {
        return p1.compareByPriceTo(p2);
    }
}
