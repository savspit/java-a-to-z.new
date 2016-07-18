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
            System.out.println("thread1: " + to.counter);
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
            System.out.println("thread2: " + to.counter);
        }
    }

    public void init() {
        TestObject to = new TestObject();
        System.out.println("start: " + to.counter);
        new Thread(new TestThread1(to)).start();
        new Thread(new TestThread2(to)).start();
        System.out.println("stop: " + to.counter);
    }

    public static void main(String[] args) {
        RaceConditionCollision col = new RaceConditionCollision();
        col.init();
    }

}
