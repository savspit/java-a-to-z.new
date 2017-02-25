package ru.shestakov;

public class UniqCheck {

    public boolean check(char[] arr) {
        boolean result = true;
        for(int i=0; i<arr.length; i++) {
            for (int j=0; j<arr.length; j++) {
                if (i!=j && arr[i]==arr[j]) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

}
