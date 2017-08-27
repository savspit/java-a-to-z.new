package ru.shestakov;

import org.junit.Test;

public class CheckNullTest {

    @Test
    public void whenExistZeroElementsShouldZerosStringAndColumnIt() {
        int[][] arr = getRandomArrayWithZeroElement(5, 5);
        printArr(arr);
        CheckNull checkNull = new CheckNull();
        int[][] newArr = checkNull.clear(arr);
        printArr(newArr);
    }

    private void printArr(int[][] array) {
        for (int i=0; i< array.length; i++) {
            for (int j=0; j< array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private int[][] getRandomArrayWithZeroElement(int str, int col) {
        int[][] array = new int[str][col];
        for (int i=0; i < array.length; i++){
            for (int j=0; j < array[i].length; j++){
                array[i][j] = (int)(Math.random() * 10);
            }
        }
        array[array.length/2][array[0].length/2] = 0;
        return array;
    }

}