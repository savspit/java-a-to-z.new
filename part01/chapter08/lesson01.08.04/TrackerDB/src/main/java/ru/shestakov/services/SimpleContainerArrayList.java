package ru.shestakov.services;

import java.util.*;

public class SimpleContainerArrayList<E> {

    private Object[] elementData;
    private int size;

    public SimpleContainerArrayList() {
        this(10);
    }

    public SimpleContainerArrayList(int size) {
        this.elementData = new Object[size];
    }

    public boolean add(E e) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = e;
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if(minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public boolean remove(Object o) {
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
        return false;
    }

    private void fastRemove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index+1, elementData, index,
                    numMoved);
        elementData[--size] = null;
    }

    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<E> {
        int cursor;

        public boolean hasNext() {
            return cursor != size;
        }

        public E next() {
            return (E) elementData[cursor++];
        }

        public void remove() {

        }
    }

}
