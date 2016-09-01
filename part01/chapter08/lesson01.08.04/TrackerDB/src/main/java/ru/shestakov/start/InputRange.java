package ru.shestakov.start;

public interface InputRange {

    /**
     * Method`s instruction for subclasses
     * @param question
     * @return
     */
    long ask(String question, boolean check);

    /**
     * Method`s instruction for subclasses
     * @param question
     * @param range
     * @return
     */
    int ask(String  question, int[] range);

}