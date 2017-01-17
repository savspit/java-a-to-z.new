package ru.shestakov;

/**
 * The type Binary search.
 *
 * @param <T> the type parameter
 */
public class BinarySearch<T extends Comparable<T>> {

    /**
     * Search int.
     *
     * @param array the array
     * @param value the value
     * @return the int
     */
    public int search(T[] array, T value) {
        int start = 0;
        int end = array.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            int result = value.compareTo(array[mid]);
            if (result > 0) {
                start = mid + 1;
            } else if (result < 0) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * Is sorted boolean.
     *
     * @param array the array
     * @return the boolean
     */
    public boolean isSorted(T[] array) {
        if (array.length < 2) { return true; }
        int firstDirection = array[0].compareTo(array[1]);
        for (int i = 0; i < array.length - 1; i++) {
            int currentDirection = array[i].compareTo(array[i+1]);
            if ( (firstDirection > 0 && currentDirection < 0) ||
                    (firstDirection < 0 && currentDirection > 0) ) {
                return false;
            }
        }
        return true;
    }

}
