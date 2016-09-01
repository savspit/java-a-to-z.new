package ru.shestakov.services;

import java.util.Iterator;

public class SimpleSetOnList<E>  {

    public int size;
    private static final Object PRESENT = new Object();
    public Node<Entry> first;
    public Node<Entry> last;

    public SimpleSetOnList() {
        this.size = 0;
    }

    public class Node<Entry> {
        Entry item;
        Node<Entry> next;
        Node<Entry> prev;

        Node(Node<Entry> prev, Entry element, Node<Entry> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
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
        Entry result = null;
        for(int i=0; i<this.size; i++) {
            Entry item = node(i).item;
            int hash = hash(e);
            if (item != null && item.hash == hash && (item.key == e || item.key.equals(e))) {
                result = item;
                break;
            }
        }
        if(result == null) {
            linkLast(new Entry<>(hash(e), e, PRESENT, null));
        }
    }

    void linkLast(Entry entry) {
        Node<Entry> l = last;
        Node<Entry> newNode = new Node<>(l, entry, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    Node<Entry> node(int index) {

        if (index < (size >> 1)) {
            Node<Entry> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<Entry> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public Iterator<E> iterator(int index) {
        return new Itr(index);
    }

    private class Itr implements Iterator<E> {
        private Node<Entry> lastReturned = null;
        private Node<Entry> next;
        private int nextIndex;

        Itr(int index) {
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolean hasNext() {
            return nextIndex < size;
        }

        public E next() {
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return (E) lastReturned.item.key;
        }

        public void remove() {
        }
    }

    public int size() {
        return size;
    }
}
