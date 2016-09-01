package ru.shestakov.services;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleContainerArrayListTest {

    @Test
    public void whenAddThenContainerHaveValue() {

        SimpleContainerArrayList<Integer> arrL = new SimpleContainerArrayList<Integer>();
        arrL.add(1);

        assertThat(arrL.get(0), is(1));
    }

    @Test
    public void whenRemoveThenContainerHaveNotValue() {

        SimpleContainerArrayList<Integer> arrL = new SimpleContainerArrayList<Integer>();
        arrL.add(1);
        arrL.remove(1);

        assertThat(arrL.size(), is(0));
    }

    @Test
    public void whenLoopThenHaveCorrectCursor() {

        SimpleContainerArrayList<Integer> arrL = new SimpleContainerArrayList<Integer>();
        arrL.add(1);
        arrL.add(2);

        Integer count = 0;
        Iterator iterator = arrL.iterator();
        while (iterator.hasNext()) {
            count = (Integer) iterator.next();
        }

        assertThat(arrL.get(1), is(count));
    }

    @Test
    public void whenGrowSizeThenGrow() {

        SimpleContainerArrayList<Integer> arrL = new SimpleContainerArrayList<Integer>(10);

        int count = 0;
        Iterator iterator = arrL.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        assertThat(arrL.size(), is(count));
    }
}