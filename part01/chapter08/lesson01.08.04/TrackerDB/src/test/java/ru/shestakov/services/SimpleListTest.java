package ru.shestakov.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleListTest {

    public class A {}
    public class B extends A { }
    public class C extends B { }

    /*public void wildTest() {
        SimpleList<B> list = new SimpleList<>(10);
        list.add(new A());
        list.add(new B());
        list.add(new C());

        print(list);

        printUpper(list);

        printLower(list);
    }

    public void print(SimpleList<?> list) {

    }

    public void printUpper(SimpleList<? extends B> list) {

    }

    public void printLower(SimpleList<? super B> list) {

    }*/

    @Test
    public void whenCreateContainerShouldReturnTheSameType() {
        Stack simple = new Stack(4);

        simple.add("test");
        String result = simple.get(1);

        assertThat(result, is("test"));
    }

    /*@Test
    public void whenCreateContainerShouldReturnInt() {
        SimpleList<Integer> simple = new SimpleList<Integer>(4);

        simple.add(2);
        int result = simple.get(1);

        assertThat(result, is(2));
    }*/

    public void showList() {
        List<String> list = new ArrayList<>(100);
        List<? super Integer> numbers = new LinkedList<>();
        numbers.add(1);
    }
}