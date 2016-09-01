package ru.shestakov.services;

import java.util.Comparator;

public class LeafBinary<E> {

    private Leaf<E> root = null;
    private int size;

    public class Leaf<E> {
        E e;
        Leaf<E> left = null;
        Leaf<E> right = null;
        Leaf<E> parent;

        Leaf(E e, Leaf<E> parent) {
            this.e = e;
            this.parent = parent;
        }

        public void addValue(E e) {
            this.e = e;
        }
    }

    public void add(E e) {
        Leaf<E> r = root;
        if (r == null) {
            root = new Leaf<>(e, null);
            size = 1;
            return;
        }
        int cmp;
        Leaf<E> parent;
        Comparable<? super E> value = (Comparable<? super E>) e;
        do {
            parent = r;
            cmp = value.compareTo(r.e);
            if (cmp < 0)
                r = r.left;
            else if (cmp > 0)
                r = r.right;
            else
                r.addValue(e);
        } while (r != null);

        Leaf<E> leaf = new Leaf<>(e, parent);
        if (cmp < 0)
            parent.left = leaf;
        else
            parent.right = leaf;
        size++;
    }

    public Leaf<E> getLeaf(E e) {
        Comparable<? super E> value = (Comparable<? super E>) e;
        Leaf<E> r = root;
        while (r != null) {
            int cmp = value.compareTo(r.e);
            if (cmp < 0)
                r = r.left;
            else if (cmp > 0)
                r = r.right;
            else
                return r;
        }
        return null;
    }

    public int size() {
        return this.size;
    }
}
