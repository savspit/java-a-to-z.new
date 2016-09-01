package ru.shestakov.templates;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleGenerator implements Template{

    final static Pattern P = Pattern.compile("\\$\\{(\\w+)\\}");

    public String generate(String template, Map<String,String> data) throws SimpleGeneratorException {

        StringBuilder sb = new StringBuilder(template);
        Matcher m = P.matcher(sb);

        int counter = 0;
        while (m.find()) {

            String value = data.get(m.group(1));
            if(value == null) { throw new SimpleGeneratorException("Keys in data less keys in text"); }
            sb.replace(m.start(), m.end(), value);
            m.reset();

            counter++;
        }

        if (counter != data.size()) { throw new SimpleGeneratorException("Keys in text less keys in data"); }

        return sb.toString();
    }
}