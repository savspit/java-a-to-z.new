package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

/**
 * The type Binary search test.
 */
public class BinarySearchTest {

    private String[] arraySortedAsc;
    private String[] arraySortedDesc;

    /**
     * Prepare collections.
     */
    @Before
    public void prepareCollections() {
        this.arraySortedAsc = new String[]{"Анаконда","Волк","Дятел","Крокодил","Лев","Мул","Обезьяна"};
        this.arraySortedDesc = new String[]{"Обезьяна","Мул","Лев","Крокодил","Дятел","Волк","Анаконда"};
    }

    /**
     * When search array should do it correct.
     */
    @Test
    public void whenSearchArrayShouldDoItCorrect() {
        BinarySearch<String> bs = new BinarySearch<>();
        int position = bs.search(this.arraySortedAsc, "Лев");
        Assert.assertThat(position, is(4));
    }

    /**
     * When sorted asc should return correct value.
     */
    @Test
    public void whenSortedAscShouldReturnCorrectValue() {
        BinarySearch<String> bs = new BinarySearch<>();
        Assert.assertTrue(bs.isSorted(this.arraySortedAsc));
    }

    /**
     * When sorted desc should return correct value.
     */
    @Test
    public void whenSortedDescShouldReturnCorrectValue() {
        BinarySearch<String> bs = new BinarySearch<>();
        Assert.assertTrue(bs.isSorted(this.arraySortedDesc));
    }

}