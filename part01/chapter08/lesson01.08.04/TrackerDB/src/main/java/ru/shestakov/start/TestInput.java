package ru.shestakov.start;

/**
 * Init StubInput class
 */
//public class TestInput implements Input {
public class TestInput {

    /** The value is used for answers storage. */
    private String[] answers;
    /** The value is used for position in answers storage. */
    private int position = 0;

    /**
     * Initializes a newly created object
     * @param answers
     */
    public TestInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Returns the <code>String</code> value at the
     * specified index.
     */
    public String ask(String question) {
        return answers[position++];
    }


    /*public long ask(String question, boolean check) {
        return 0;
    }*/

    public int ask(String question, int[] range) {
        throw new UnsupportedOperationException("Unsupported operation");
    }
}