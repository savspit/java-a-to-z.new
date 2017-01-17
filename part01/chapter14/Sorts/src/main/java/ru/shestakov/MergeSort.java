package ru.shestakov;

/**
 * The type Merge sort.
 */
public class MergeSort {

    /**
     * Sort.
     *
     * @param <T>   the type parameter
     * @param array the array
     * @return the t [ ]
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        mergeSort(array, 0, array.length - 1);
        return array;
    }

    private <T extends Comparable<T>> void mergeSort(T[] array, int start, int end) {
        if (start < end) {
            int mid = start + ((end - start) / 2);
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private <T extends Comparable<T>> void merge(T[] array, int start, int mid, int end) {
        int countOfSort = (end - start) + 1;
        Object[] temp = new Object[countOfSort];
        int startValue = start;
        int middleValue = mid + 1;
        int index = 0;

        while (startValue <= mid && middleValue <= end) {
            if (array[startValue].compareTo(array[middleValue]) < 0) {
                temp[index] = array[startValue];
                startValue++;
            } else {
                temp[index] = array[middleValue];
                middleValue++;
            }
            index++;
        }

        while (startValue <= mid) {
            temp[index] = array[startValue];
            startValue++;
            index++;
        }
        while (middleValue <= end) {
            temp[index] = array[middleValue];
            middleValue++;
            index++;
        }

        for (index = 0; index < countOfSort; index++)
            array[start + index] = (T) temp[index];
    }
}
