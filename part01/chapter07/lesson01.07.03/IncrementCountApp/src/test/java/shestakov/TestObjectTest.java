package shestakov;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TestObjectTest {

    @Test
    public void whenIncrenentByDifferentThreadsShouldDoItCorrect() {
        TestObject to = new TestObject();
        new Thread(new TestThread(to)).start();
        new Thread(new TestThread(to)).start();
        new Thread(new TestThread(to)).start();
        new Thread(new TestThread(to)).start();
        new Thread(new TestThread(to)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(to.getCounter(), is(5));
    }

}