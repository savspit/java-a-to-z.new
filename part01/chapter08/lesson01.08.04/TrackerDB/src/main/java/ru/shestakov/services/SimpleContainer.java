package ru.shestakov.services;

public interface SimpleContainer<E> extends Iterable<E> {

    void add(E e);

    E get(int index);

    void remove(E e);

    int size();
}
