package ru.shestakov.start;

public class BeautyMenu extends MenuTracker{

    /**
     * Initializes a newly created menu action
     *
     * @param input
     * @param tracker
     * @param size
     */
    public BeautyMenu(Input input, Tracker tracker, int size) {
        super(input, tracker, size);
    }

    @Override
    public void showItem(UserAction action) {
        System.out.println("-=" + action.info());
    }
}