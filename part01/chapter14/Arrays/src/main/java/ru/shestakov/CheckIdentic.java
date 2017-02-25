package ru.shestakov;

public class CheckIdentic {

    public boolean isIdentic(String correctWord, String inputWord) {
        boolean result = false;
        char[] correctWordArr = correctWord.toCharArray();
        char[] inputWordArr = inputWord.toCharArray();
        if ((!missedOneLetterCheck(correctWordArr, inputWordArr))|| (!addedOneLetter(correctWordArr, inputWordArr)) || (!changedOneLetter(correctWordArr, inputWordArr))) {
            result = true;
        }
        return result;
    }

    private boolean missedOneLetterCheck(char[] correctWordArr, char[] inputWordArr) {
        if (correctWordArr.length - inputWordArr.length != 1) {
            return false;
        }
        boolean result = true;
        for (int i=0; i<inputWordArr.length; i++) {
            boolean exist = false;
            for (int j=0; j<correctWordArr.length; j++) {
                exist = true;
            }
            if (!exist) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean addedOneLetter(char[] correctWordArr, char[] inputWordArr) {
        if (inputWordArr.length - correctWordArr.length != 1) {
            return false;
        }
        boolean result = true;
        for (int i=0; i<correctWordArr.length; i++) {
            boolean exist = false;
            for (int j=0; j<inputWordArr.length; j++) {
                exist = true;
            }
            if (!exist) {
                result = false;
                break;
            }
        }
        return result;
    }

    private boolean changedOneLetter(char[] correctWordArr, char[] inputWordArr) {
        int mainLeingth = correctWordArr.length;
        boolean result = true;
        for (int i=0; i<mainLeingth; i++) {
            if ( correctWordArr[i] != inputWordArr[i] && ( correctWordArr[i] != inputWordArr[i+1] && correctWordArr[i+1] != inputWordArr[i] ) ) {
                result = false;
                break;
            }
        }
        return result;
    }
}
