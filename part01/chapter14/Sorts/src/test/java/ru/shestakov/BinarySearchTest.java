package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;

public class BinarySearchTest {

    private String[] sortedArray;

    @Before
    public void prepareCollections() {
        this.sortedArray = new String[]{"Анаконда","Волк","Дятел","Крокодил","Лев","Мул","Обезьяна"};
    }

    @Test
    public void whenSearchShouldDoItCorrect() {
        BinarySearch<String> bs = new BinarySearch<>();
        int position = bs.search(this.sortedArray, "Лев");
        Assert.assertThat(position, is(4));
    }

}