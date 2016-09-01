package ru.shestakov.services;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SimpleArrayTest {

    @Test
    public void whenGetShouldGet() {

        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(1);

        assertThat(simpleArray.get(0), is(1));

    }

    @Test
    public void whenAddShouldAdd() {

        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(1);

        assertThat(simpleArray.get(0), is(1));

    }

    @Test
    public void whenUpdateShouldUpdate() {

        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(1);
        simpleArray.update(0,2);

        assertThat(simpleArray.get(0), is(2));

    }

    @Test
    public void whenDeleteShouldDelete() {

        SimpleArray<Integer> simpleArray = new SimpleArray<Integer>(10);
        simpleArray.add(1);
        simpleArray.delete(0);

        Assert.assertEquals(simpleArray.get(0), null);

    }

}