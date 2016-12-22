package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class BinarySearchTest {

    private String[] arraySortedAsc;
    private String[] arraySortedDesc;

    @Before
    public void prepareCollections() {
        this.arraySortedAsc = new String[]{"Анаконда","Волк","Дятел","Крокодил","Лев","Мул","Обезьяна"};
        this.arraySortedDesc = new String[]{"Обезьяна","Мул","Лев","Крокодил","Дятел","Волк","Анаконда"};
    }

    @Test
    public void whenSearchArrayShouldDoItCorrect() {
        BinarySearch<String> bs = new BinarySearch<>();
        int position = bs.search(this.arraySortedAsc, "Лев");
        Assert.assertThat(position, is(4));
    }

    @Test
    public void whenSortedAscShouldReturnCorrectValue() {
        BinarySearch<String> bs = new BinarySearch<>();
        Assert.assertTrue(bs.isSorted(this.arraySortedAsc));
    }

    @Test
    public void whenSortedDescShouldReturnCorrectValue() {
        BinarySearch<String> bs = new BinarySearch<>();
        Assert.assertTrue(bs.isSorted(this.arraySortedDesc));
    }

}