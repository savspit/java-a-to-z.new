package ru.shestakov.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class IteratorFlattenTest {

    @Test
    public void whenBigIteratorThenDoFlattenIterator() {

        List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> list2 = new ArrayList<>(Arrays.asList(5, 6, 7, 8));
        List<Iterator<Integer>> combined = new ArrayList<>(Arrays.asList(list1.iterator(), list2.iterator()));

        IteratorFlatten ioi = new IteratorFlatten();
        Iterator<Integer> iterator = ioi.convert(combined.iterator());
        int[] expected = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] actual = new int[8];
        int i = 0;
        while (iterator.hasNext()) {
            actual[i] = iterator.next();
            i++;
        }

        assertArrayEquals(expected, actual);
    }
}