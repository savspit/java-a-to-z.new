package shestakov;

public class Locker {
    private boolean locked = false;

    public void lock() {
        synchronized (this) {
            System.out.println(String.format("%s locked", Thread.currentThread().getId()));
            doLock(true);
        }
    }

    public void unlock() {
        synchronized (this) {
            System.out.println(String.format("%s unlocked", Thread.currentThread().getId()));
            doLock(false);
        }
    }

    private void doLock(boolean value) {
        this.locked = value;
        notifyAll();
    }

    public void doSomething() {
        synchronized (this) {
            while (this.locked) {
                try {
                    System.out.println(String.format("%s wait", Thread.currentThread().getId()));
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(String.format("%s do", Thread.currentThread().getId()));
        }
    }

    public static void main(String[] args) {
        final Locker locker = new Locker();
        new Thread() {
            @Override
            public void run() {
                locker.lock();
                locker.doSomething();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                locker.unlock();
                locker.doSomething();
            }
        }.start();
    }
}
