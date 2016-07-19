package shestakov.models;

public class CounterOfSpaces extends Counter{

    public CounterOfSpaces(String text, int timeOut) {
        super(text, timeOut);
    }

    @Override
    public int count() {
        long timeToDead = System.currentTimeMillis() + this.timeOut;
        int counter = 0;
        for (int i=0; i<this.text.length(); i++) {
            if (Character.isSpaceChar(text.charAt(i))) {
                counter++;
            }
            if (this.timeOut != 0 && timeToDead < System.currentTimeMillis()) {
                return 0;
            }
        }
        return counter;
    }

    @Override
    public void countAndShow() {
        new Buffer("spaces:",count()).show();
    }


}
