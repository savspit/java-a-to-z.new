package shestakov.models;

public class CounterOfWords extends Counter{

    public CounterOfWords(String text) {
        super(text);
    }

    @Override
    public int count() {
        int counter = 0;
        boolean isWord = false;
        for (int i=0; i<this.text.length(); i++) {
            if (Character.isLetter(text.charAt(i))) {
                isWord = true;
            } else if (!Character.isLetter(text.charAt(i)) && isWord) {
                isWord = false;
                counter++;
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
