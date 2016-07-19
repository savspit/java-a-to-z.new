package shestakov.models;

public abstract class Counter implements Runnable{
    public final String text;
    public final int timeOut;

    public Counter(final String text, final int timeOut) {
        this.text = text;
        this.timeOut = timeOut;
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
