package shestakov;

import java.util.ArrayList;
import java.util.List;

public class ProducerCustomer {
    private final Object lock = new Object();
    private boolean blockCustomer = true;
    private final List<String> data = new ArrayList<>();

    public void doSomething() {
        synchronized (this.lock) {
            while (this.blockCustomer) {
                if (data.isEmpty()) {
                    try {
                        System.out.println(String.format("%s wait", Thread.currentThread().getId()));
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(String.format("%s usefull work", Thread.currentThread().getId()));
            for (String currentData : data) {
                // todo some useful things
            }
            this.data.clear();
        }
    }

    public void addData(boolean value) {
        synchronized (this.lock) {
            System.out.println(String.format("%s enable", Thread.currentThread().getId()));
            // todo add some data
            this.data.add("Some data");
            this.blockCustomer = value;
            this.lock.notifyAll();
        }
    }

    public static void main(String[] args) {
        final ProducerCustomer blockingWork = new ProducerCustomer();

        // producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                blockingWork.addData(false);
            }
        };

        // customer
        Thread customer = new Thread() {
            @Override
            public void run() {
                blockingWork.doSomething();
            }
        };

        producer.start();
        customer.start();
    }
}
