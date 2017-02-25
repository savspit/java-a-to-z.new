package ru.shestakov;

public class CheckNull {

    public int[] clear(int[] arr) {
        int[] newArr = new int[getNewSize(arr)];
        int elemNum = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] != 0) {
                newArr[elemNum] = arr[i];
                elemNum++;
            }
        }
        return newArr;
    }

    private int getNewSize(int[] arr) {
        int newSize = 0;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] != 0) {
                newSize++;
            }
        }
        return newSize;
    }

}
