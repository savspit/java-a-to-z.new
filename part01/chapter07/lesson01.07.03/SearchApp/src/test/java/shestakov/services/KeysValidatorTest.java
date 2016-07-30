package shestakov.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class KeysValidatorTest {

    @Test
    public void whenGetsAllArgsShouldWorkCorrect() {
        String[] args = new String[] {"-f", "tmp", "-p", "C:\\", "-r", "nio", "-t", "0", "-s"};
        KeysValidator kv = new KeysValidator();
        boolean result = kv.parsedSuccessful(args);

        assertTrue(result);
        assertThat(kv.pattern, is(args[1]));
        assertThat(kv.reader.name(), is(args[5].toUpperCase()));
        assertThat(kv.numberOfThreads, is(Integer.parseInt(args[7])));
        assertThat(kv.stopAtFirstResult, is(true));
    }

}