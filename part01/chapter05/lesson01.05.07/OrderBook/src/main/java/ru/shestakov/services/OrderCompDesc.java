package ru.shestakov.services;

import java.util.Comparator;

// from hi to low
public class OrderCompDesc implements Comparator<KeySort>  {

    @Override
    public int compare(KeySort p1, KeySort p2) {
        return p2.compareByPriceTo(p1);
    }

}
