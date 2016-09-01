package ru.shestakov.services;

import java.util.Iterator;

public class IteratorArrayEven implements Iterator{

    private final int[] values;
    private int index = 0;

    public IteratorArrayEven(final int[] values) {
        this.values = values;
    }

    public boolean hasNext() {

        boolean result = false;
        while (index < values.length) {
            if ((index % 2 == 0)) {
                result = true;
                break;
            }
            index++;
        }
        return result;
    }

    public Object next() {
        int result = values[index];
        index++;
        return result;
    }

    public void remove() {

    }

}
