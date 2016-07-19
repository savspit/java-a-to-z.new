package shestakov.models;

public class CounterOfWords extends Counter{

    public CounterOfWords(String text, int timeOut) {
        super(text, timeOut);
    }

    @Override
    public int count() {
        long timeToDead = System.currentTimeMillis() + this.timeOut;
        int counter = 0;
        boolean isWord = false;
        for (int i=0; i<this.text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                isWord = true;
            } else if (!Character.isLetter(text.charAt(i)) && isWord) {
                isWord = false;
                counter++;
            }
            if (this.timeOut != 0 && timeToDead < System.currentTimeMillis()) {
                return 0;
            }
        }
        if (isWord) { counter++; }
        return counter;
    }

    @Override
    public void countAndShow() {
        new Buffer("words:",count()).show();
    }

}
