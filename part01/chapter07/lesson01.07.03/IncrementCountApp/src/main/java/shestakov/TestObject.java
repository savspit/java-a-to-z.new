package shestakov;

import java.util.concurrent.atomic.AtomicInteger;

public class TestObject {
    private final AtomicInteger counter;

    public TestObject() {
        this.counter = new AtomicInteger(0);
    }

    public int getCounter() {
        return this.counter.get();
    }

    public void increment() {
        this.counter.incrementAndGet();
    }
}
