package ru.shestakov.services;


import java.util.Arrays;
import java.util.Iterator;

public class SimpleSetOnArrayQuick<E>  {

    private Entry[] values;
    private int size;
    private static final Object PRESENT = new Object();

    public SimpleSetOnArrayQuick() {
        this(10);
    }

    public SimpleSetOnArrayQuick(int initialCapacity) {
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
        put(e, PRESENT);
    }

    private void ensureCapacityInternal(int minCapacity) {
        if(minCapacity > values.length) {
            int newCapacity = values.length + (values.length >> 1);
            values = Arrays.copyOf(values, newCapacity);
        }
    }

    public int binarySearchRec(int left, int right, E key, int hash) {

        int mid = left + (right - left) / 2;

        if (right < left) {
            return mid;
        }

        if (values[left] != null && values[left].hash == hash && (values[left].key == key || values[left].key.equals(key))) { return -1 ;}
        if (values[right] != null && values[right].hash == hash && (values[right].key == key || values[right].key.equals(key))) { return -1 ;}
        if (values[mid] != null && values[mid].hash == hash && (values[mid].key == key || values[mid].key.equals(key))) { return -1 ;}

        if (values[mid] == null) {
            return binarySearchRec(left, mid - 1, key, hash);
        }

        if (values[mid] != null && values[mid].hash < hash) {
            return binarySearchRec(mid + 1, right, key, hash);
        }else {
            return binarySearchRec(left, mid - 1, key, hash);
        }
    }

    public void put(E key, Object value) {

        Entry result = null;

        int hash = hash(key);

        if(values.length == 0 || values[0] == null || (values[values.length-1] != null && values[values.length-1].hash < hash)) {
            result = new Entry<>(hash(key), key, value, null);
            ensureCapacityInternal(size + 1);
            values[size++] = result;
        }else {
            result = new Entry<>(hash(key), key, value, null);

            int searchResult = binarySearchRec(0, values.length-1, key, hash);

            if (searchResult != -1) {
                result = new Entry<>(hash(key), key, value, null);

                ensureCapacityInternal(size + 1);
                System.arraycopy(values, searchResult, values, searchResult + 1, size - searchResult);

                values[searchResult] = result;
                size++;
            }
        }
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
