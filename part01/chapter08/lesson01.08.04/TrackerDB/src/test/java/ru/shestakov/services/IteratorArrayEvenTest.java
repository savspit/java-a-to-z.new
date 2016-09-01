package ru.shestakov.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class IteratorArrayEvenTest {

    @Test
    public void whenGetNextCallShouldPointerForward() {
        IteratorArrayEven it = new IteratorArrayEven(new int[] {1, 3, 7});

        int result = 0;
        while (it.hasNext()) {
            result = (Integer) it.next();
        }
        assertThat(result, is(7));
    }

    @Test
    public void whenCheckNextPositionShouldReturnConstantValue() {
        IteratorArrayEven it = new IteratorArrayEven(new int[] {1, 2});

        it.hasNext();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }
}