package ru.shestakov.services;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleSetOnArrayTest {

    @Test
    public void whenAddThenContainerHaveValue() {

        SimpleSetOnArray<Integer> arrL = new SimpleSetOnArray<Integer>();
        arrL.add(1);

        assertThat(arrL.size(), is(1));
    }

    @Test
    public void whenAddDuplixateThenContainerHaveNotDuplicate() {

        SimpleSetOnArray<Integer> arrL = new SimpleSetOnArray<Integer>();
        arrL.add(1);
        arrL.add(1);

        assertThat(arrL.size(), is(1));
    }

    @Test
    public void whenLoopThenHaveCorrectCursor() {

        SimpleSetOnArray<Integer> arrL = new SimpleSetOnArray<Integer>();
        arrL.add(1);
        arrL.add(2);

        Integer count = 0;
        Iterator iterator = arrL.iterator();
        while (iterator.hasNext()) {
            count = (Integer) iterator.next();
        }

        assertThat(count, is(2));
    }
}