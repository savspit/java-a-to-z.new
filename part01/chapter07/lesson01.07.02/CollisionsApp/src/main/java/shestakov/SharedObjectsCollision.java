package shestakov;

public class SharedObjectsCollision {

    public class TestThread1 implements Runnable {
        public final TestObject to;

        public TestThread1(TestObject to) {
            this.to = to;
        }

        public void run() {
            new Buffer().show("thread1 until", to.value1, to.value2);
            this.to.value1 = 999;
            this.to.value2 = 999;
            new Buffer().show("thread1 after", to.value1, to.value2);
        }
    }

    public class TestThread2 implements Runnable {
        public final TestObject to;

        public TestThread2(TestObject to) {
            this.to = to;
        }

        public void run() {
            new Buffer().show("thread2 until", to.value1, to.value2);
            this.to.value1 = 0;
            this.to.value2 = 0;
            new Buffer().show("thread2 after", to.value1, to.value2);
        }
    }

    public void init() {
        TestObject to = new TestObject();
        new Buffer().show("start", to.value1, to.value2);
        new Thread(new TestThread1(to)).start();
        new Thread(new TestThread2(to)).start();
    }

    public static void main(String[] args) {
        SharedObjectsCollision col = new SharedObjectsCollision();
        col.init();
    }

}
