package ru.shestakov.start;

/**
 * Init StubInput class
 */
//public class StubInput2 implements Input {
public class StubInput2 {

    /** The value is used for answers storage. */
    private String[] answers;
    /** The value is used for position in answers storage. */
    private int position = 0;

    /**
     * Initializes a newly created object
     * @param answers
     */
    public StubInput2(String[] answers) {
        this.answers = answers;
    }

    /**
     * Returns the <code>String</code> value at the
     * specified index.
     */
    public String ask(String question) {
        return answers[position++];
    }


    public long ask(String question, boolean check) {
        return 0;
    }

    public int ask(String question, int[] range) {
        return 0;
    }
}