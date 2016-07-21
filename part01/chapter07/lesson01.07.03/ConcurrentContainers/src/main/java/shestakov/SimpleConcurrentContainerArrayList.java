package shestakov;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleConcurrentContainerArrayList<E> {

    private volatile Object[] elementData;
    private volatile int size;

    public SimpleConcurrentContainerArrayList() {
        this(10);
    }

    public SimpleConcurrentContainerArrayList(int size) {
        this.elementData = new Object[size];
    }

    public boolean add(E e) {
        synchronized (this) {
            ensureCapacityInternal(size + 1);
            elementData[size++] = e;
        }
        return true;
    }

    private void ensureCapacityInternal(int minCapacity) {
        if(minCapacity > elementData.length) {
            int newCapacity = elementData.length + (elementData.length >> 1);
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    public boolean remove(Object o) {
        synchronized (this) {
            for (int index = 0; index < size; index++)
                if (o.equals(elementData[index])) {
                    fastRemove(index);
                    return true;
                }
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
        synchronized (this) {
            return (E) elementData[index];
        }
    }

    public int size() {
        synchronized (this) {
            return size;
        }
    }

    public Iterator<E> iterator() {
        synchronized (this) {
            return new Itr();
        }
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
