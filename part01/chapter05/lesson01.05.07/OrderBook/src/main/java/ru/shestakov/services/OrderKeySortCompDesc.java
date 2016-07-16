package ru.shestakov.services;

import java.util.Comparator;

// from hi to low
public class OrderKeySortCompDesc implements Comparator<KeySort> {

    @Override
    public int compare(KeySort n1, KeySort n2) {
        return n1.compareToDesc(n2);
    }

}
