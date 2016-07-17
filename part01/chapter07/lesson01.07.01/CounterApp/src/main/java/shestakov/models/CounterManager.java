package shestakov.models;

public class CounterManager {

    public void count(String text) {
        new Thread(new CounterOfWords(text)).start();
        new Thread(new CounterOfSpaces(text)).start();
    }

}
