package ru.shestakov.services;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleContainerStackTest {

    @Test
    public void whenPushThenPopLastValue() {

        SimpleContainerStack<Integer> arrL = new SimpleContainerStack<Integer>();
        arrL.push(1);
        arrL.push(2);

        assertThat(arrL.pop(), is(2));
    }

    @Test
    public void whenPopThenLastValueIsOut() {

        SimpleContainerStack<Integer> arrL = new SimpleContainerStack<Integer>();
        arrL.push(1);
        arrL.push(2);

        Integer integ = arrL.pop();

        assertThat(arrL.pop(), is(1));
    }

    @Test
    public void whenPeekThenLastValueIsNotOut() {

        SimpleContainerStack<Integer> arrL = new SimpleContainerStack<Integer>();
        arrL.push(1);
        arrL.push(2);

        Integer integ = arrL.peek();

        assertThat(arrL.peek(), is(2));
    }

}