package shestakov;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool extends Thread{
    private final LinkedList<Work> freeThreads;
    private final List<Work> workingThreads;
    private static final int DEFAULT_CAPACITY = Runtime.getRuntime().availableProcessors();
    private boolean blockObj = false;

    public ThreadPool(final int capacity) {
        this.freeThreads = new LinkedList<>();
        this.workingThreads = new ArrayList<>(capacity == 0 ? this.DEFAULT_CAPACITY : capacity);
    }

    public void add(Work work) {
        synchronized (this) {
            System.out.println(String.format("%s enable", Thread.currentThread().getId()));
            this.freeThreads.add(work);
            this.blockObj = true;
            notifyAll();
        }
    }

    public void runThreads() {
        System.out.println(String.format("%s run threads", Thread.currentThread().getId()));
        for (Work work : this.workingThreads) {
            try {
                work.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        this.workingThreads.clear();
    }

    @Override
    public void run() {
        synchronized (this) {
            while (this.blockObj) {
                if (this.freeThreads.isEmpty()) {
                    try {
                        System.out.println(String.format("%s wait", Thread.currentThread().getId()));
                        wait();
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                }
                System.out.println(String.format("%s usefull work", Thread.currentThread().getId()));
                while (!this.freeThreads.isEmpty()) {
                    for (int i = 0; i < this.freeThreads.size() && i < this.DEFAULT_CAPACITY; i++) {
                        this.workingThreads.add(this.freeThreads.poll());
                    }
                    runThreads();
                }
                this.freeThreads.clear();
            }
        }
    }

    public static void main(String[] args) {
        final ThreadPool pool = new ThreadPool(0);
        pool.start();
    }

    public class Work extends Thread{
        @Override
        public void run() {
            //todo some useful things
        }
    }

}
