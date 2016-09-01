package ru.shestakov.services;

public class SimpleContainerStack<E> extends SimpleContainerLinkedList<E> {

    public E pop() {
        return removeFirst();
    }

    public E peek() {
        Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    public void push(E e) {
        addFirst(e);
    }

    public void addFirst(E e) {
        Node<E> f = first;
        Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    public E removeFirst() {
        Node<E> f = first;
        return unlinkFirst(f);
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
