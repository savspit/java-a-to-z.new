package ru.shestakov.services;

import java.util.Iterator;

public class SimpleSetOnListQuick<E>  {

    public int size;
    private static final Object PRESENT = new Object();
    public Node<Entry> first;
    public Node<Entry> last;

    public SimpleSetOnListQuick() {
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

    public int binarySearchRec(int left, int right, E key, int hash) {

        int mid = left + (right - left) / 2;

        if (right < left) {
            return mid;
        }

        if (node(left).item.hash == hash && (node(left).item.key == key || node(left).item.key.equals(key))) { return -1 ;}
        if (node(right).item.hash == hash && (node(right).item.key == key || node(right).item.key.equals(key))) { return -1 ;}
        if (node(mid).item.hash == hash && (node(mid).item.key == key || node(mid).item.key.equals(key))) { return -1 ;}

        if (node(mid).item.hash < hash) {
            return binarySearchRec(mid + 1, right, key, hash);
        }else {
            return binarySearchRec(left, mid - 1, key, hash);
        }
    }

    void add (E e) {

        Entry result = null;

        int hash = hash(e);

        if (this.size == 0) {
            linkLast(new Entry<>(hash, e, PRESENT, null));
        }

        int searchResult = binarySearchRec(0, this.size, e, hash);

        if (searchResult != -1) {
            if (searchResult == this.size)
                linkLast(new Entry<>(hash, e, PRESENT, null));
            else
                linkAfter(new Entry<>(hash, e, PRESENT, null), node(searchResult));
        }

        /*for(int i=0; i<this.size; i++) {
            Entry item = node(i).item;
            int hash = hash(e);
            if (item != null && item.hash == hash && (item.key == e || item.key.equals(e))) {
                result = item;
                break;
            }
        }
        if(result == null) {
            linkLast(new Entry<>(hash(e), e, PRESENT, null));
        }*/
    }

    void linkAfter(Entry entry, Node<Entry> succ) {
        Node<Entry> posl = succ.next;
        Node<Entry> newNode = new Node<>(succ, entry, posl);
        succ.next = newNode;
        if (posl == null)
            last = newNode;
        else
            posl.prev = newNode;
        size++;
    }

    void linkBefore(Entry entry, Node<Entry> succ) {
        Node<Entry> pred = succ.prev;
        Node<Entry> newNode = new Node<>(pred, entry, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
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
