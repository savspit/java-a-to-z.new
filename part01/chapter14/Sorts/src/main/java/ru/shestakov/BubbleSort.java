package ru.shestakov;

import java.util.ArrayList;

/**
 * The type Bubble sort.
 *
 * @param <T> the type parameter
 */
public class BubbleSort<T extends Comparable<T>> {

    /**
     * Sort.
     *
     * @param list the list
     */
    public void sort(ArrayList<T> list) {
        for (int x=0; x<list.size()-1; x++) {
            for (int y=0; y<list.size()-1-x; y++) {
                if(list.get(y).compareTo(list.get(y+1)) > 0) {
                    T temp = list.get(y);
                    list.set(y, list.get(y+1));
                    list.set(y+1, temp);
                }
            }
        }
    }

}
