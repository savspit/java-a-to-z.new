package ru.shestakov.services;

import java.util.*;

public class IteratorFlatten implements Iterator {

    Iterator<Iterator<Integer>> it;
    Iterator<Integer> cursor;

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        this.it = it;
        return this;
    }

    public boolean hasNext() {
        return it.hasNext() || (cursor != null && cursor.hasNext());
    }

    public Integer next() {
        if(cursor == null) {
            cursor = it.next();
        }else if (!cursor.hasNext()) {
            cursor = it.next();
        }
        return cursor.next();
    }

    public void remove() {

    }

}

