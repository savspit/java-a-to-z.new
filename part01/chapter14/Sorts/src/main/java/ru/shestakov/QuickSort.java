package ru.shestakov;

/**
 * The type Quick sort.
 */
public class QuickSort {

    /**
     * Sort t [ ].
     *
     * @param <T>   the type parameter
     * @param array the array
     * @return the t [ ]
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void quickSort(T[] array, int start, int end) {
        if (start < end) {
            int startValue = start;
            int endValue = end;
            T mid = array[(startValue + endValue) / 2];

            do {
                while (array[startValue].compareTo(mid) < 0) {
                    startValue++;
                }
                while (mid.compareTo(array[endValue]) < 0) {
                    endValue--;
                }
                if (startValue <= endValue) {
                    T temp = array[startValue];
                    array[startValue] = array[endValue];
                    array[endValue] = temp;
                    startValue++;
                    endValue--;
                }
            } while (startValue <= endValue);

            quickSort(array, start, endValue);
            quickSort(array, startValue, end);
        }
    }
}
