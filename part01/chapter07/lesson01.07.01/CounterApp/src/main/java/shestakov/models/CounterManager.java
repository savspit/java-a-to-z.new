package shestakov.models;

public class CounterManager {
    private int timeout;

    public CounterManager(int timeout) {
        this.timeout = timeout;
    }

    public void count(String text) {
        // will be stopped by interrupt() and sleep()
        Thread thNeedToBeStopped = new Thread(new CounterOfDigits(text, 0));
        thNeedToBeStopped.start();
        thNeedToBeStopped.interrupt();

        Thread th1 = new Thread(new Informer("start"));
        Thread th2 = new Thread(new CounterOfWords(text, this.timeout));
        Thread th3 = new Thread(new CounterOfSpaces(text, this.timeout));
        Thread th4 = new Thread(new Informer("stop"));
        th1.start();
        try {
            th1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th2.start();
        try {
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th3.start();
        try {
            th3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        th4.start();
        try {
            th4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
