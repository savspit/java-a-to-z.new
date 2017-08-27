package ru.shestakov;

public class CheckNull {

    public int[][] clear(int[][] arr) {
        int [] stringsArr = new int [arr.length];
        int [] columnsArr = new int [arr[0].length];

        int countStr = 0;
        int countCol = 0;

        for (int i=0; i<arr.length; i++) {
            for (int j=0; j< arr[i].length; j++) {
                if (arr[i][j] == 0) {
                    stringsArr[countStr] = i;
                    columnsArr[countCol] = j;
                    countStr++;
                    countCol++;
                }
            }
        }
        for (int i=countStr; i<stringsArr.length; i++) {
            stringsArr[i] = Integer.MAX_VALUE;
        }
        for (int j=countCol; j<columnsArr.length; j++) {
            columnsArr[j] = Integer.MAX_VALUE;
        }

        for (int k=0; k<stringsArr.length; k++) {
            if (stringsArr[k] != Integer.MAX_VALUE) {
                for (int j=0; j<arr[stringsArr[k]].length; j++) {
                    arr[stringsArr[k]][j] = 0;
                }
            }
        }

        for (int m=0; m<columnsArr.length; m++) {
            if (columnsArr[m] != Integer.MAX_VALUE) {
                for (int i=0; i<arr.length; i++) {
                    arr[i][columnsArr[m]] = 0;
                }
            }
        }

        return arr;
    }


}
