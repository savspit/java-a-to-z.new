package ru.shestakov;

/**
 * The type Heap sort.
 */
public class HeapSort {

    /**
     * Sort.
     *
     * @param <T>   the type parameter
     * @param array the array
     * @return the t [ ]
     */
    public <T extends Comparable<T>> T[] sort(T[] array) {
        makeHeap(array, array.length);
        int end = array.length - 1;
        while (end >= 0) {
            doSwap(array, end, 0);
            end--;
            doSift(array, 0, end);
        }
        return array;
    }

    private <T extends Comparable<T>> T[] doSwap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
        return array;
    }

    private <T extends Comparable<T>> T[] makeHeap(T[] array, int count) {
        int start = (int) Math.floor((count - 2) / 2);
        while (start >= 0) {
            doSift(array, start, count - 1);
            start--;
        }
        return array;
    }

    private <T extends Comparable<T>> T[] doSift(T[] array, int start, int end) {
        int root = start;
        while (root * 2 + 1 <= end) {
            int child = root * 2 + 1;
            int swap = root;
            if (array[swap].compareTo(array[child]) < 0) {
                swap = child;
            }
            if ((child + 1 <= end) && (array[swap].compareTo(array[child + 1]) < 0)) {
                swap = child + 1;
            }
            if (swap==root) {
                return array;
            } else {
                doSwap(array, root, swap);
                root = swap;
            }
        }
        return array;
    }
}
