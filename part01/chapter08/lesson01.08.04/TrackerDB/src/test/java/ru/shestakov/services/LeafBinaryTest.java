package ru.shestakov.services;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LeafBinaryTest {

    public LeafBinary<Integer> fillTheBinaryTree() {
        LeafBinary<Integer> searchBinaryTree = new LeafBinary<>();
        searchBinaryTree.add(100);
        searchBinaryTree.add(50);
        searchBinaryTree.add(25);
        searchBinaryTree.add(75);
        searchBinaryTree.add(150);
        searchBinaryTree.add(125);
        searchBinaryTree.add(175);
        return searchBinaryTree;
    }

    @Test
    public void whenAddValueShouldAdd() {
        LeafBinary<Integer> searchBinaryTree = fillTheBinaryTree();
        assertThat(searchBinaryTree.size(), is(7));
    }

    @Test
    public void whenAddValuesShouldHaveCorrectOrder() {
        LeafBinary<Integer> searchBinaryTree = fillTheBinaryTree();
        assertThat(searchBinaryTree.getLeaf(75).parent.e.intValue(), is(50));
        assertThat(searchBinaryTree.getLeaf(50).parent.e.intValue(), is(100));
        assertNull(searchBinaryTree.getLeaf(100).parent);
    }

    @Test
    public void whenFindValueShouldFind() {

        LeafBinary<Integer> searchBinaryTree = fillTheBinaryTree();

        long time1 = System.nanoTime();
        Object foundedLeaf = searchBinaryTree.getLeaf(175);
        System.out.println("time: " + (System.nanoTime()-time1) + " ns");

        assertNotNull(foundedLeaf);
    }

}