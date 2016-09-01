package ru.shestakov.services;

public class SimpleContainerQueue<E> extends SimpleContainerLinkedList<E> {

    public E poll() {
        final Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    public E unlinkFirst(Node<E> f) {
        E element = f.item;
        Node<E> next = f.next;
        f.item = null;
        f.next = null;
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }
}
