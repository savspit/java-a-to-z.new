package ru.shestakov.services;

import java.util.Iterator;

public class IteratorArraySimple implements Iterator{

    private final int[] values;
    private int index = 0;

    public IteratorArraySimple(final int[] values) {
        this.values = values;
    }

    public boolean hasNext() {
        boolean result = false;
        while (index < values.length) {
            if (indexIsSimple()) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    public boolean indexIsSimple() {
        int counter = 0;
        for (int i = 2; i <= index; i++) {
            if (index % i == 0 && index % 1 == 0 && index % index == 0) {
                counter++;
            }
        }
        return counter == 1;
    }

    public Object next() {
        int result = values[index];
        index++;
        return result;
    }

    public void remove() {

    }

}
