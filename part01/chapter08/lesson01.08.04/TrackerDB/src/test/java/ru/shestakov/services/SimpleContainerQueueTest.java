package ru.shestakov.services;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleContainerQueueTest {

    @Test
    public void whenAddThenPollFirstValue() {

        SimpleContainerQueue<Integer> arrL = new SimpleContainerQueue<Integer>();
        arrL.add(1);
        arrL.add(2);

        assertThat(arrL.poll(), is(1));
    }

    @Test
    public void whenPollThenFirstValueIsOut() {

        SimpleContainerQueue<Integer> arrL = new SimpleContainerQueue<Integer>();
        arrL.add(1);
        arrL.add(2);

        Integer integ = arrL.poll();

        assertThat(arrL.poll(), is(2));
    }

    @Test
    public void whenPeekThenFirstValueIsNotOut() {

        SimpleContainerQueue<Integer> arrL = new SimpleContainerQueue<Integer>();
        arrL.add(1);
        arrL.add(2);

        Integer integ = arrL.peek();

        assertThat(arrL.peek(), is(1));
    }
}