package ru.shestakov.services;

public abstract class AbstractStore<T> implements Store{

    SimpleArray<T> sa;

    public AbstractStore(int size) {
        this.sa = new SimpleArray<T>(size);
    }

    public void add(T t) {
        this.sa.add(t);
    }

    public void update(int index, T t) {
        this.sa.update(index, t);
    }

    public void delete(int index) {
        this.delete(index);
    }

    public void get(int index) {
        this.get(index);
    }


}
