package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * The type Merge sort test.
 */
public class MergeSortTest {

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
        MergeSort ms = new MergeSort();
        String[] result = ms.sort(this.unsortedList);
        for (int i=0; i<result.length; i++) {
            Assert.assertThat(result[i], is(this.sortedList[i]));
        }
    }
}