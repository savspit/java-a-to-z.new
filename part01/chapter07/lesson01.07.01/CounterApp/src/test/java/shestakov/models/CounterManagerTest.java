package shestakov.models;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CounterManagerTest {

    @Test
    public void whenCountNumberOfWordsShouldReturnRightCounter() {
        CounterOfWords cow = new CounterOfWords("test test;test ;test test");
        assertThat(cow.count(), is(5));
    }

    @Test
    public void whenCountNumberOfWordsInEmptyStringShouldReturnZero() {
        CounterOfWords cow = new CounterOfWords("");
        assertThat(cow.count(), is(0));
    }

    @Test
    public void whenCountNumberOfWordsInStringWithoutWordsShouldReturnZero() {
        CounterOfWords cow = new CounterOfWords(" ;'<>");
        assertThat(cow.count(), is(0));
    }

    @Test
    public void whenCountNumberOfSpacesShouldReturnRightCounter() {
        CounterOfSpaces cos = new CounterOfSpaces("test test;test ;test test");
        assertThat(cos.count(), is(3));
    }

    @Test
    public void whenCountNumberOfSpacesInEmptyStringShouldReturnZero() {
        CounterOfSpaces cos = new CounterOfSpaces("");
        assertThat(cos.count(), is(0));
    }
    @Test
    public void whenCountNumberOfSpacesInStringWithoutSpacesShouldReturnZero() {
        CounterOfSpaces cos = new CounterOfSpaces("fg;sdfg;fdsg<fg>");
        assertThat(cos.count(), is(0));
    }

}