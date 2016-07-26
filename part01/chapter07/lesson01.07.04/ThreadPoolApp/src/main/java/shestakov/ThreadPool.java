package shestakov;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private final List<ServiceThread> serviceThreads;
    private final BlockingQueue<Runnable> queue;
    private static final int DEFAULT_CAPACITY = Runtime.getRuntime().availableProcessors();

    public ThreadPool(final int capacity) {
        this.serviceThreads = new LinkedList<>();
        this.queue = new LinkedBlockingQueue<>(capacity == 0 ? this.DEFAULT_CAPACITY : capacity);
        initAndStartAllServiceThreads();
    }

    public void initAndStartAllServiceThreads() {
        for (int i=0; i<this.queue.remainingCapacity(); i++) {
            this.serviceThreads.add(new ServiceThread(queue));
        }
        for (ServiceThread serviceThr : this.serviceThreads) {
            serviceThr.start();
            System.out.println(String.format("%s start", Thread.currentThread().getId()));
        }
    }

    public void add(Work work) {
        synchronized (this.queue) {
            System.out.println(String.format("%s add & notify", Thread.currentThread().getId()));
            try {
                this.queue.put(work);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.queue.notifyAll();
        }
    }

}

