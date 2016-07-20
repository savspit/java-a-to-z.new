package shestakov;

public class TestThread extends Thread{

    private TestObject to;

    public TestThread(TestObject to) {
        this.to = to;
    }

    @Override
    public void run() {
        to.increment();
    }
}
