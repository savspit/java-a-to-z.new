package shestakov.models;

public class CounterOfSpaces extends Counter{

    public CounterOfSpaces(String text) {
        super(text);
    }

    @Override
    public int count() {
        int counter = 0;
        for (int i=0; i<this.text.length(); i++) {
            if (Character.isSpaceChar(text.charAt(i))) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public void countAndShow() {
        new Buffer("spaces:",count()).show();
    }
}
