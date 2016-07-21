package shestakov.threads;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class TaskThreadPool extends Thread{
    private final LinkedList<Thread> toDoList;
    private final List<Thread> workingQueue;
    private static final int DEFAULT_CAPACITY = 100;
    private boolean needToBeStoped;

    public TaskThreadPool(final int capacity) {
        super("parserThreadPool-thread");
        this.toDoList = new LinkedList<>();
        this.workingQueue = new ArrayList<>(capacity == 0 ? this.DEFAULT_CAPACITY : capacity);
        this.needToBeStoped = false;
    }

    public void add(Thread pt) {
        synchronized (this) {
            this.toDoList.add(pt);
            notifyAll();
        }
    }

    @Override
    public void run() {
        while (!this.needToBeStoped) {
            synchronized (this) {
                if (this.toDoList.isEmpty()) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        interrupt();
                    }
                }
                for (int i=0; i<this.toDoList.size() && i<this.DEFAULT_CAPACITY; i++) {
                    this.workingQueue.add(this.toDoList.poll());
                }
                this.toDoList.clear();
            }
            runTasks();
        }
        // get the rest of files which need to be parsed when app need to be stopped
        if (this.needToBeStoped && !this.toDoList.isEmpty()) {
            synchronized (this) {
                this.workingQueue.addAll(this.toDoList);
                this.toDoList.clear();
            }
            runTasks();
        }
    }

    public void runTasks() {
        for (Thread pt : this.workingQueue) {
            try {
                pt.run();
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        this.workingQueue.clear();
    }

    public void setNeedToBeStoped() {
        synchronized (this) {
            this.needToBeStoped = true;
        }
    }

}
