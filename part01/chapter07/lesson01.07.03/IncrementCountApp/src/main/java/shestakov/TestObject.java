package shestakov;

public class TestObject {
    private volatile int counter = 0;

    public int getCounter() {
        return counter;
    }

    public void increment() {
        this.counter++;
    }
}
