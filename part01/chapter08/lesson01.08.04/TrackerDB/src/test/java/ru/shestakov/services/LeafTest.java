package ru.shestakov.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LeafTest {

    public Leaf<String> fillTheTree() {

        Leaf<String> root = new Leaf<String>("root");
        Leaf<String> leaf1 = new Leaf<String>("leaf1");
        Leaf<String> leaf2 = new Leaf<String>("leaf2");
        Leaf<String> leaf3 = new Leaf<String>("leaf3");
        Leaf<String> leaf4 = new Leaf<String>("leaf4");
        root.addChild(leaf1);
        root.addChild(leaf2);
        root.addChild(leaf3);
        root.addChild(leaf4);
        Leaf<String> leaf21 = new Leaf<String>("leaf21");
        Leaf<String> leaf22 = new Leaf<String>("leaf22");
        leaf2.addChild(leaf21);
        leaf2.addChild(leaf22);
        Leaf<String> leaf31 = new Leaf<String>("leaf31");
        Leaf<String> leaf32 = new Leaf<String>("leaf32");
        leaf3.addChild(leaf31);
        leaf3.addChild(leaf32);
        Leaf<String> leaf41 = new Leaf<String>("leaf41");
        leaf4.addChild(leaf41);
        return root;
    }

    public Leaf<String> fillTheBalancedTree() {

        Leaf<String> root = new Leaf<String>("root");
        Leaf<String> leaf1 = new Leaf<String>("leaf1");
        Leaf<String> leaf2 = new Leaf<String>("leaf2");
        root.addChild(leaf1);
        root.addChild(leaf2);
        Leaf<String> leaf11 = new Leaf<String>("leaf11");
        Leaf<String> leaf12 = new Leaf<String>("leaf12");
        leaf1.addChild(leaf11);
        leaf1.addChild(leaf12);
        Leaf<String> leaf21 = new Leaf<String>("leaf21");
        Leaf<String> leaf22 = new Leaf<String>("leaf22");
        leaf2.addChild(leaf21);
        leaf2.addChild(leaf22);
        return root;
    }

    @Test
    public void whenBFSShouldFindElement() {

        Leaf<String> root = fillTheTree();

        long time1 = System.nanoTime();
        Leaf<String> foundedLeaf = root.getByBFS("leaf31");
        System.out.println("time: " + (System.nanoTime()-time1) + " ns");

        assertNotNull(foundedLeaf);
    }

    @Test
    public void whenDFSShouldFindElement() {

        Leaf<String> root = fillTheTree();

        long time1 = System.nanoTime();
        Leaf<String> foundedLeaf = root.getByDFS("leaf31");
        System.out.println("time: " + (System.nanoTime()-time1) + " ns");

        assertNotNull(foundedLeaf);
    }

    @Test
    public void whenNodesHasTwoBranchesTreeIsBalanced() {

        Leaf<String> root = fillTheTree();
        assertThat(root.treeIsBalanced(), is(false));

        Leaf<String> rootB = fillTheBalancedTree();
        assertThat(rootB.treeIsBalanced(), is(true));
    }
}