package ru.shestakov.services;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class LeafAndLeafBinaryTest {

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
    public void whenSearchInTreesShouldShowTimeEachOne() {

        Leaf<String> simpleTree = fillTheTree();

        long timeSimpleTree1 = System.nanoTime();
        Leaf<String> foundedElementInSimpleTreeByBFS = simpleTree.getByBFS("leaf31");
        System.out.println("SimpleTree (BFS) time: " + (System.nanoTime()-timeSimpleTree1) + " ns");

        long timeSimpleTree2 = System.nanoTime();
        Leaf<String> foundedElementInSimpleTreeByDFS = simpleTree.getByDFS("leaf31");
        System.out.println("SimpleTree (DFS) time: " + (System.nanoTime()-timeSimpleTree2) + " ns");

        LeafBinary<Integer> searchBinaryTree = fillTheBinaryTree();

        long timeSearchBinaryTree = System.nanoTime();
        Object foundedElementInSearchBinaryTree = searchBinaryTree.getLeaf(175);
        System.out.println("SearchBinaryTree (level search) time: " + (System.nanoTime()-timeSearchBinaryTree) + " ns");

    }

}
