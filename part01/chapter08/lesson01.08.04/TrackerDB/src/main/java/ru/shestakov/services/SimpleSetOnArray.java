package ru.shestakov.services;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleSetOnArray<E>  {

    private Entry[] values;
    private int size;
    private static final Object PRESENT = new Object();

    public SimpleSetOnArray() {
        this(10);
    }

    public SimpleSetOnArray(int initialCapacity) {
        this.values = new Entry[initialCapacity];
    }

    public class Entry<K,V> {
        final K key;
        V value;
        Entry<K,V> next;
        int hash;

        Entry(int h, K k, V v, Entry<K,V> n) {
            value = v;
            next = n;
            key = k;
            hash = h;
        }
    }

    void add (E e) {
        ensureCapacityInternal(size + 1);
        values[size] = put(e, PRESENT);
    }

    private void ensureCapacityInternal(int minCapacity) {
        if(minCapacity > values.length) {
            int newCapacity = values.length + (values.length >> 1);
            values = Arrays.copyOf(values, newCapacity);
        }
    }


    public Entry put(E key, Object value) {
        Entry result = null;
        for(int i=0; i<values.length; i++) {
            int hash = hash(key);
            if (values[i] != null && values[i].hash == hash && (values[i].key == key || values[i].key.equals(key))) {
                result = values[i];
                break;
            }
        }
        if(result == null) {
            result = new Entry<>(hash(key), key, value, null);
            size++;
        }
        return result;
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
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
            return (E) values[cursor++].key;
        }

        public void remove() {

        }
    }

    public int size() {
        return size;
    }
}
