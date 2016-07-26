package start;

import org.junit.Test;
import shestakov.ThreadPool;
import shestakov.Work;

import static org.junit.Assert.*;

public class StartUITest {

    @Test
    public void whenThreadsAreWorkingThreadPoolIsOk() {
        ThreadPool tp = new ThreadPool(0);
        for (int i=0; i<10; i++) {
            tp.add(new Work());
        }
    }

}