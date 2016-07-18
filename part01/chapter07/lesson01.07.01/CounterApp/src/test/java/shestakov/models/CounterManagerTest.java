package shestakov.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterManagerTest {

    @Test
    public void whenCountNumberOfWordsShouldReturnRightCounter() {
        CounterOfWords cow = new CounterOfWords("test test;test ;test test", 0);
        assertThat(cow.count(), is(5));
    }

    @Test
    public void whenCountNumberOfWordsInEmptyStringShouldReturnZero() {
        CounterOfWords cow = new CounterOfWords("", 0);
        assertThat(cow.count(), is(0));
    }

    @Test
    public void whenCountNumberOfWordsInStringWithoutWordsShouldReturnZero() {
        CounterOfWords cow = new CounterOfWords(" ;'<>", 0);
        assertThat(cow.count(), is(0));
    }

    @Test
    public void whenCountNumberOfSpacesShouldReturnRightCounter() {
        CounterOfSpaces cos = new CounterOfSpaces("test test;test ;test test", 0);
        assertThat(cos.count(), is(3));
    }

    @Test
    public void whenCountNumberOfSpacesInEmptyStringShouldReturnZero() {
        CounterOfSpaces cos = new CounterOfSpaces("", 0);
        assertThat(cos.count(), is(0));
    }

    @Test
    public void whenCountNumberOfSpacesInStringWithoutSpacesShouldReturnZero() {
        CounterOfSpaces cos = new CounterOfSpaces("fg;sdfg;fdsg<fg>", 0);
        assertThat(cos.count(), is(0));
    }

    @Test
    public void whenTimeIsOutShouldBeTimeOut() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i < 100000; i++) {
            sb.append("test ");
        }
        CounterOfWords cow = new CounterOfWords(sb.toString(), 1);
        assertThat(cow.count(), is(0));
    }

}