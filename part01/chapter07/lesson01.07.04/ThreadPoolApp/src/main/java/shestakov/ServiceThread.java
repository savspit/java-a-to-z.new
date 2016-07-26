package shestakov;

import java.util.concurrent.BlockingQueue;

public class ServiceThread extends Thread {
    private final BlockingQueue<Runnable> queue;
    private Runnable task = null;

    public ServiceThread(BlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            synchronized (this.queue) {
                while (this.queue.isEmpty()) {
                    try {
                        System.out.println(String.format("%s task wait", Thread.currentThread().getId()));
                        this.queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            try {
                System.out.println(String.format("%s take/remove run", Thread.currentThread().getId()));
                task = this.queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s task run", Thread.currentThread().getId()));
            task.run();
        }
    }
}
