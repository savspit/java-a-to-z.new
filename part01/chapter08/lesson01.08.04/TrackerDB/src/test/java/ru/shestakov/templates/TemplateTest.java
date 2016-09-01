package ru.shestakov.templates;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TemplateTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void whenTakeTestWithDataShouldReplaceParamsToData() throws SimpleGeneratorException {

        // assign
        SimpleGenerator template = new SimpleGenerator();
        String text = "Hello, ${name} ${surname}.";
        Map<String,String> data = new HashMap<String, String>();
        data.put("name", "Petr");
        data.put("surname", "Parsentev");
        String checked = "Hello, Petr Parsentev.";

        // act
        String result = template.generate(text, data);

        // action
        Assert.assertThat(result, is(checked));
    }

    @Test
    public void whenTakeTestWithKeysInDataLessKeysInTextShouldReturnError() throws SimpleGeneratorException {

        SimpleGenerator template = new SimpleGenerator();
        String text = "Hello, ${name}, ${surname}.";
        Map<String,String> data = new HashMap<String, String>();
        data.put("name", "Petr");

        exception.expect(SimpleGeneratorException.class);
        exception.expectMessage("Keys in data less keys in text");

        String result = template.generate(text, data);
    }

    @Test
    public void whenTakeTestWithKeysInTextLessKeysInDataShouldReturnError() throws SimpleGeneratorException {

        SimpleGenerator template = new SimpleGenerator();
        String text = "Hello, ${name}.";
        Map<String,String> data = new HashMap<String, String>();
        data.put("name", "Petr");
        data.put("surname", "Parsentev");

        exception.expect(SimpleGeneratorException.class);
        exception.expectMessage("Keys in text less keys in data");

        String result = template.generate(text, data);
    }
}