package ru.shestakov;

import java.util.ArrayList;

public class CocktailSort<T extends Comparable<T>> {

    public void sort(ArrayList<T> list) {
        boolean swapped = true;
        int start = 0;
        int end = list.size();

        while (swapped) {
            swapped = false;
            for (int x = start; x < end - 1; x++) {
                if (list.get(x).compareTo(list.get(x + 1)) > 0) {
                    T temp = list.get(x);
                    list.set(x, list.get(x + 1));
                    list.set(x + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
            swapped = false;
            end--;
            for (int x = end - 1; x >= start; x--) {
                if (list.get(x).compareTo(list.get(x + 1)) > 0) {
                    T temp = list.get(x);
                    list.set(x, list.get(x + 1));
                    list.set(x + 1, temp);
                    swapped = true;
                }
            }
            start++;
        }
    }

}
