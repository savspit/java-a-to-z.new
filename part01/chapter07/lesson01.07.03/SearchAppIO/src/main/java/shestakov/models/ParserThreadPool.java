package shestakov.models;

public class ParserThreadPool {
    private final ParserThread[] threads;

    public ParserThreadPool(ParserThread[] threads) {
        this.threads = threads;
    }
}
