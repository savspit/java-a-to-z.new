package shestakov;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerCustomer {
    private final BlockingQueue<String> data = new ArrayBlockingQueue<>(5);

    public void doSomething() {
        synchronized (this.data) {
            if (this.data.isEmpty()) {
                try {
                    System.out.println(String.format("%s wait", Thread.currentThread().getId()));
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(String.format("%s usefull work", Thread.currentThread().getId()));
        for (int i = 0; i < this.data.size(); i++) {
            try {
                this.data.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addData() {
        synchronized (this.data) {
            System.out.println(String.format("%s enable", Thread.currentThread().getId()));
            // todo add some data
            try {
                this.data.put("Some data");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data.notifyAll();
        }
    }

    public static void main(String[] args) {
        final ProducerCustomer blockingWork = new ProducerCustomer();
        // producer
        Thread producer = new Thread() {
            @Override
            public void run() {
                blockingWork.addData();
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
