package shestakov;

public class RaceConditionCollision {

    public class TestThread1 implements Runnable {
        public final TestObject to;

        public TestThread1(TestObject to) {
            this.to = to;
        }

        public void run() {
            this.to.counter++;
            this.to.counter++;
            new Buffer().show("thread1", to.counter);
        }
    }

    public class TestThread2 implements Runnable {
        public final TestObject to;

        public TestThread2(TestObject to) {
            this.to = to;
        }

        public void run() {
            this.to.counter++;
            this.to.counter++;
            new Buffer().show("thread2", to.counter);
        }
    }

    public void init() {
        TestObject to = new TestObject();
        new Buffer().show("start", to.counter);
        new Thread(new TestThread1(to)).start();
        new Thread(new TestThread2(to)).start();
        new Buffer().show("stop", to.counter);
    }

    public static void main(String[] args) {
        RaceConditionCollision col = new RaceConditionCollision();
        col.init();
    }

}
