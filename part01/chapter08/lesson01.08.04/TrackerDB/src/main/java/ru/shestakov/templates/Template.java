package ru.shestakov.templates;

import java.util.Map;

public interface Template {
    /**
     * Hello world, ${name}
     * @param template
     * @param data
     * @return
     */
    String generate(String template, Map<String,String> data) throws SimpleGeneratorException;
}