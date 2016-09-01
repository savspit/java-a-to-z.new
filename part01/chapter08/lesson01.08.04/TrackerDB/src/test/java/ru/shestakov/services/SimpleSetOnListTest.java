package ru.shestakov.services;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetOnListTest {

    @Test
    public void whenAddThenContainerHaveValue() {

        SimpleSetOnList<Integer> arrL = new SimpleSetOnList<Integer>();
        arrL.add(1);

        assertThat(arrL.size(), is(1));
    }

    @Test
    public void whenAddDuplixateThenContainerHaveNotDuplicate() {

        SimpleSetOnList<Integer> arrL = new SimpleSetOnList<Integer>();
        arrL.add(1);
        arrL.add(1);

        assertThat(arrL.size(), is(1));
    }

    @Test
    public void whenLoopThenHaveCorrectCursor() {

        SimpleSetOnList<Integer> arrL = new SimpleSetOnList<Integer>();
        arrL.add(1);
        arrL.add(2);

        Integer count = 0;
        Iterator iterator = arrL.iterator(0);
        while (iterator.hasNext()) {
            count = (Integer) iterator.next();
        }

        assertThat(count, is(2));
    }
}