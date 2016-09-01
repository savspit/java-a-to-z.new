package ru.shestakov.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorArraySimpleTest {

    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorArraySimple it = new IteratorArraySimple(new int[] {5, 3, 7, 9, 0, 1, 4});

        int result = 0;
        while (it.hasNext()) {
            result = (Integer) it.next();
        }
        assertThat(result, is(1));
    }

    @Test
    public void whenCheckNextPositionShouldReturnConstantValue() {
        IteratorArraySimple it = new IteratorArraySimple(new int[] {1, 3});

        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}