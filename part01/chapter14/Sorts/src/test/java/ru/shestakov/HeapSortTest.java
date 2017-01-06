package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * The type Heap sort test.
 */
public class HeapSortTest {

    private String[] sortedList;
    private String[] unsortedList;

    /**
     * Prepare collections.
     */
    @Before
    public void prepareCollections() {
        this.sortedList = new String[]{"Анаконда","Волк","Дятел","Крокодил","Лев","Мул","Обезьяна"};
        this.unsortedList = new String[]{"Обезьяна","Мул","Лев","Крокодил","Дятел","Волк","Анаконда"};
    }

    /**
     * When sort should do it correct.
     */
    @Test
    public void whenSortShouldDoItCorrect() {
        HeapSort hs = new HeapSort();
        String[] result = hs.sort(this.unsortedList);
        for (int i=0; i<result.length; i++) {
            Assert.assertThat(result[i], is(this.sortedList[i]));
        }
    }

}