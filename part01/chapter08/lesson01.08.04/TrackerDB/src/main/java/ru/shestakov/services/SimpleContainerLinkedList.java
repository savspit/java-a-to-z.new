package ru.shestakov.services;

import java.util.Iterator;

public class SimpleContainerLinkedList<E> {

    public int size;
    public Node<E> first;
    public Node<E> last;

    public SimpleContainerLinkedList() {
        this.size = 0;
    }

    public class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    void linkLast(E e) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
    }

    public E get(int index) {
        return node(index).item;
    }

    Node<E> node(int index) {

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    public int size() {
        return size;
    }

    public boolean remove(Object o) {
        for (Node<E> x = first; x != null; x = x.next) {
            if (o.equals(x.item)) {
                unlink(x);
                return true;
            }
        }
        return false;
    }

    E unlink(Node<E> x) {

        E element = x.item;
        Node<E> next = x.next;
        Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;

        return element;
    }

    public Iterator<E> iterator(int index) {
        return new Itr(index);
    }

    private class Itr implements Iterator<E> {
        private Node<E> lastReturned = null;
        private Node<E> next;
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
            return lastReturned.item;
        }

        public void remove() {
        }
    }

    boolean hasLoop(Node node) {
        Node<E> slow = node, fast = node;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    Node<E> addNode(Node<E> prev, E e) {
        return new Node<>(prev, e, null);
    }
}
