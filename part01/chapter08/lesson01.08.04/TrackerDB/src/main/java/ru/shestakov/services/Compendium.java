package ru.shestakov.services;

import java.util.Iterator;

public class Compendium<T,V> {

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    static final int MAXIMUM_CAPACITY = Integer.MAX_VALUE - 8;
    static final Entry<?,?>[] EMPTY_TABLE = {};
    int threshold;
    Entry<T,V>[] table = (Entry<T,V>[]) EMPTY_TABLE;
    int size;

    public Compendium() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public Compendium(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
    }

    static class Entry<K,V> {
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

        public final V getValue() {
            return value;
        }

    }

    boolean insert(T key, V value) {
        return put(key, value) != null;
    }

    private V put(T key, V value) {
        if (table == EMPTY_TABLE) {
            inflateTable(threshold);
        }
        int hash = hash(key);
        int i = indexFor(hash, table.length);

        for (Entry<T,V> e = table[i]; e != null; e = e.next) {
            Object k;
            if (e.hash == hash) {
                V oldValue = e.value;
                e.value = value;
                //e.recordAccess(this);
                return oldValue;
            }
        }

        addEntry(hash, key, value, i);
        return null;
    }

    void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;
        if (oldCapacity == MAXIMUM_CAPACITY) {
            return;
        }

        Entry[] newTable = new Entry[newCapacity];
        transfer(newTable);
        table = newTable;
    }

    void transfer(Entry[] newTable) {
        int newCapacity = newTable.length;
        for (Entry<T,V> e : table) {
            while(null != e) {
                Entry<T,V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                e.next = newTable[i];
                newTable[i] = e;
                e = next;
            }
        }
    }

    void addEntry(int hash, T key, V value, int bucketIndex) {
        if ((size >= threshold) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }

        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, T key, V value, int bucketIndex) {
        Entry<T,V> e = table[bucketIndex];
        table[bucketIndex] = new Entry<>(hash, key, value, e);
        size++;
    }

    public int roundUpToPowerOf2(int number) {
        return number >= MAXIMUM_CAPACITY
                ? MAXIMUM_CAPACITY
                : (number > 1) ? Integer.highestOneBit((number - 1) << 1) : 1;
    }

    public void inflateTable(int toSize) {
        int capacity = roundUpToPowerOf2(toSize);
        table = new Entry[capacity];
    }

    public int indexFor(int h, int length) {
        return h & (length-1);
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public V get(T key) {
        Entry<T,V> entry = getEntry(key);
        return null == entry ? null : entry.getValue();
    }

    public Entry<T,V> getEntry(Object key) {
        if (size == 0) {
            return null;
        }

        int hash = (key == null) ? 0 : hash(key);
        for (Entry<T,V> e = table[indexFor(hash, table.length)];
             e != null;
             e = e.next) {
            Object k;
            if (e.hash == hash)
                return e;
        }
        return null;
    }

    boolean delete(T key) {
        return remove(key);
    }

    public boolean remove(Object key) {
        Entry<T,V> e = removeEntryForKey(key);
        return (e instanceof Entry);
    }

    public Entry<T,V> removeEntryForKey(Object key) {
        if (size == 0) {
            return null;
        }
        int hash = (key == null) ? 0 : hash(key);
        int i = indexFor(hash, table.length);
        Entry<T,V> prev = table[i];
        Entry<T,V> e = prev;

        while (e != null) {
            Entry<T,V> next = e.next;
            Object k;
            if (e.hash == hash) {
                size--;
                if (prev == e)
                    table[i] = next;
                else
                    prev.next = next;
                return e;
            }
            prev = e;
            e = next;
        }

        return e;
    }

    public int size() {
        return size;
    }

    public Iterator<T> iterator() {
        return new Itr();
    }

    private class Itr implements Iterator<T> {
        int cursor;

        public boolean hasNext() {
            return cursor != size;
        }

        public T next() {
            return (T) table[cursor++].key;
        }

        public void remove() {

        }
    }


}
