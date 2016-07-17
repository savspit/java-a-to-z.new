package shestakov.models;

public abstract class Counter implements Runnable{
    public final String text;

    public Counter(final String text) {
        this.text = text;
    }

    public void run() {
        countAndShow();
    }

    public void countAndShow() {

    }

    public int count() {
        return 0;
    }
}
