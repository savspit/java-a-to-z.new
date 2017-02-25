package ru.shestakov;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UniqCheckTest {

    private char[] uniqArray;
    private char[] notUniqArray;

    @Before
    public void prepareCollections() {
        this.uniqArray = new char[]{'q', 'w', 'e', 'r', 't', 'y'};
        this.notUniqArray = new char[]{'q', 'q', 'e', 'r', 't', 'y'};
    }

    @Test
    public void whenArrayIsUniqShouldReturnCorrectResult() {
        UniqCheck uniqCheck = new UniqCheck();
        boolean result = uniqCheck.check(uniqArray);
        Assert.assertTrue(result);
    }

    @Test
    public void whenArrayIsNotUniqShouldReturnCorrectResult() {
        UniqCheck uniqCheck = new UniqCheck();
        boolean result = uniqCheck.check(notUniqArray);
        Assert.assertFalse(result);
    }
}