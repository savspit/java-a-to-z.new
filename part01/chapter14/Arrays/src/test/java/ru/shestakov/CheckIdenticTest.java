package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckIdenticTest {

    private String correctWord;
    private String inputWordChangedOneLetter;
    private String inputWordMissedOneLetter;
    private String inputWordAddedOneLetter;

    @Before
    public void prepareCollections() {
        this.correctWord = "привет";
        this.inputWordChangedOneLetter = "приевт";
        this.inputWordMissedOneLetter = "привт";
        this.inputWordAddedOneLetter = "прпиевт";
    }

    @Test
    public void whenChangedOneLetterShouldReturnCorrectResult() {
        CheckIdentic checkIdentic = new CheckIdentic();
        Assert.assertTrue(checkIdentic.isIdentic(this.correctWord, this.inputWordChangedOneLetter));
    }

    @Test
    public void whenMissedOneLetterShouldReturnCorrectResult() {
        CheckIdentic checkIdentic = new CheckIdentic();
        Assert.assertTrue(checkIdentic.isIdentic(this.correctWord, this.inputWordMissedOneLetter));
    }

    @Test
    public void whenAddedOneLetterShouldReturnCorrectResult() {
        CheckIdentic checkIdentic = new CheckIdentic();
        Assert.assertTrue(checkIdentic.isIdentic(this.correctWord, this.inputWordAddedOneLetter));
    }

}