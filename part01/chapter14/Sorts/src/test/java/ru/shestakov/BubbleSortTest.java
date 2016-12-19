package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;

/**
 * The type Bubble sort test.
 */
public class BubbleSortTest {

    private ArrayList<String> sortedList = new ArrayList<>();
    private ArrayList<String> unsortedList = new ArrayList<>();

    /**
     * Prepare collections.
     */
    @Before
    public void prepareCollections() {
        this.sortedList.add("Анаконда");
        this.sortedList.add("Волк");
        this.sortedList.add("Дятел");
        this.sortedList.add("Крокодил");
        this.sortedList.add("Лев");
        this.sortedList.add("Мул");
        this.sortedList.add("Обезьяна");

        this.unsortedList.add("Лев");
        this.unsortedList.add("Крокодил");
        this.unsortedList.add("Анаконда");
        this.unsortedList.add("Дятел");
        this.unsortedList.add("Волк");
        this.unsortedList.add("Мул");
        this.unsortedList.add("Обезьяна");
    }

    /**
     * When sort should do it correct.
     */
    @Test
    public void whenSortShouldDoItCorrect() {
        BubbleSort<String> bs = new BubbleSort<>();
        bs.sort(this.unsortedList);
        for (int i=0; i<this.unsortedList.size(); i++) {
            Assert.assertThat(this.unsortedList.get(i), is(this.sortedList.get(i)));
        }
    }

}