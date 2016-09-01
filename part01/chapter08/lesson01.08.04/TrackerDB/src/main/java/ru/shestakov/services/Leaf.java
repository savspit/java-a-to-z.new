package ru.shestakov.services;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leaf<E> {

    private List<Leaf<E>> root = new LinkedList<>();
    private Leaf<E> parent = null;
    private E e;

    public Leaf(E e) {
        this(e, null);
    }

    public Leaf(E e, Leaf<E> parent) {
        this.e = e;
        this.parent = parent;
    }

    public List<E> getChildren() {
        List<E> children = new LinkedList<E>();
        for (Leaf leaf : this.root) {
            if (leaf.parent == this.parent) {
                children.add((E) leaf.e);
            }
        }
        return children;
    }

    public void addChild (Leaf<E> child) {
        child.parent = this;
        this.root.add(child);
    }

    // breadth-first search — BFS
    public Leaf<E> getByBFS(E e) {

        LinkedList<Leaf<E>> queue = new LinkedList<>();
        queue.addAll(this.root);

        while (!queue.isEmpty()) {
            Leaf<E> current = queue.pop();
            if (current.e.equals(e)) {
                return current;
            }
            queue.addAll(current.root);
        }
        return null;
    }

    // depth-first search, DFS
    public Leaf<E> getByDFS(E e) {
        return getByDFSRec(this.root, e);
    }

    public Leaf<E> getByDFSRec(List<Leaf<E>> list, E e) {
        for (Leaf<E> current : list) {
            if (current.e.equals(e)) {
                return current;
            }
            Leaf<E> foundedLeaf = getByDFSRec(current.root, e);
            if (foundedLeaf != null) {
                return foundedLeaf;
            }
        }
        return null;
    }

    public boolean treeIsBalanced() {
        return treeIsBalancedRec(this.root);
    }

    public boolean treeIsBalancedRec(List<Leaf<E>> list) {

        if(list.size() != 2 && list.size() != 0) {
            return false;
        }

        for (Leaf<E> current : list) {
            if (current.root.size() != 2 && current.root.size() != 0) {
                return false;
            }
            return treeIsBalancedRec(current.root);
        }
        return true;
    }
}
