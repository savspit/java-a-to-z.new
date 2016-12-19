package ru.shestakov;

import java.util.ArrayList;

/**
 * The type Selection sort.
 *
 * @param <T> the type parameter
 */
public class SelectionSort<T extends Comparable<T>> {

    /**
     * Sort.
     *
     * @param list the list
     */
    public void sort(ArrayList<T> list) {
        int minIndex;
        int size = list.size();

        for (int x=0; x<size-1; x++) {
            minIndex = x;
            for (int y=x+1; y<size; y++) {
                if (list.get(y).compareTo(list.get(minIndex)) < 0) {
                    minIndex = y;
                }
            }
            T temp = list.get(x);
            list.set(x, list.get(minIndex));
            list.set(minIndex, temp);
        }
    }

}
