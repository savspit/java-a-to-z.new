package ru.shestakov.services;

public class SimpleArray<T> {

    Object[] objects;
    int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void update(int index, T value) {
        this.objects[index] = value;
    }

    public void delete(int index) {
        this.objects[index] = null;
    }

    public void add(T value) {
        this.objects[index++] = value;
    }

    public T get(int index) {
        return (T) this.objects[index];
    }
}
