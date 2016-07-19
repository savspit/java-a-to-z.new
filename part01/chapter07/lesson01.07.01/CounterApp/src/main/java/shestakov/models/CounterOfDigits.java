package shestakov.models;

public class CounterOfDigits extends Counter{

    public CounterOfDigits(String text, int timeOut) {
        super(text, timeOut);
    }

    @Override
    public int count() {
        int counter = 0;
        for (int i=0; i<this.text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                counter++;
            } else {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("counterOfDigits is stopped by interrupt()");
                    return 0;
                }
            }
        }
        return counter;
    }

    @Override
    public void countAndShow() {
        new Buffer("digits:",count()).show();
    }

}
