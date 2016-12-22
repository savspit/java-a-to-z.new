package ru.shestakov;

public class BinarySearch<T extends Comparable<T>> {

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

}
