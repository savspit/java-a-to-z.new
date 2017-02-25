package ru.shestakov;

public class WordsCompilation {

    public boolean check(String first, String second) {
        char[] firstArr = first.toCharArray();
        char[] secondArr = second.toCharArray();
        boolean result = true;
        for (int i=0; i<firstArr.length; i++) {
            boolean exist = false;
            for (int j=0; j<secondArr.length; j++) {
                if (firstArr[i] == secondArr[j]) {
                    exist = true;
                    break;
                }
            }
            if (!exist) {
                result = false;
                break;
            }
        }
        return result;
    }

}
