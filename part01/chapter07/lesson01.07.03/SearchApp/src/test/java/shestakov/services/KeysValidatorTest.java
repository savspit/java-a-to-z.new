package shestakov.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KeysValidatorTest {

    @Test
    public void whenGetsAllArgsShouldWorkCorrect() {
        String[] args = new String[] {"-f", "tmp", "-s"};
        KeysValidator kv = new KeysValidator();
        boolean result = kv.parsedSuccessful(args);
        assertTrue(result);
        assertThat(kv.pattern, is(args[1]));
        assertThat(kv.stopAtFirstResult, is(true));
    }

    @Test
    public void whenGetsFirstArgShouldWorkCorrect() {
        String[] args = new String[] {"-f", "tmp"};
        KeysValidator kv = new KeysValidator();
        boolean result = kv.parsedSuccessful(args);
        assertTrue(result);
        assertThat(kv.pattern, is(args[1]));
    }

    @Test
    public void whenGetsSecondArgShouldWorkCorrect() {
        String[] args = new String[] {"-s"};
        KeysValidator kv = new KeysValidator();
        boolean result = kv.parsedSuccessful(args);
        assertTrue(result);
        assertThat(kv.stopAtFirstResult, is(true));
    }

}