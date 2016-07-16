package shestakov.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeyMaskChecker {
    final static Pattern P = Pattern.compile(".+\\.(txt)");

    public boolean keyIsCorrect(String key) {
        StringBuilder sb = new StringBuilder(key);
        Matcher m = P.matcher(sb);
        return m.find();
    }
}
