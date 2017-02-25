package ru.shestakov;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class WordsCompilationTest {

    @Test
    public void whenCanMakeWordShouldReturnCorrectResult() {
        WordsCompilation wordsCompilation = new WordsCompilation();
        boolean result = wordsCompilation.check("отк", "кот");
        Assert.assertTrue(result);
    }

    @Test
    public void whenCannotMakeWordShouldReturnCorrectResult() {
        WordsCompilation wordsCompilation = new WordsCompilation();
        boolean result = wordsCompilation.check("отк", "ктт");
        Assert.assertFalse(result);
    }

}