package ru.shestakov.services;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CompendiumTest {

    public static final class Stuff {
        public String name;

        public Stuff(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + name.hashCode();
            return result;
        }

        /*@Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!(o instanceof Stuff)) return false;

            Stuff user = (Stuff) o;

            return name.equals(user.name);
        }*/

    }

    @Test
    public void whenAddDifferentValuesShouldAddAllValues() {

        Stuff firstStuff = new Stuff("Candy");
        Stuff secondStuff = new Stuff("Apple");

        Compendium<Stuff,String> compendium = new Compendium();
        compendium.insert(firstStuff, "first");
        compendium.insert(secondStuff, "second");

        assertThat(compendium.size(), is(2));
    }

    @Test
    public void whenAdd1000DifferentValuesShouldAddAllValues() {

        Compendium<Stuff,String> compendium = new Compendium();

        for (int i=0; i<1000; i++) {
            String value = Integer.toString(i);
            Stuff newStuff = new Stuff(value);
            compendium.insert(newStuff, value);
        }

        assertThat(compendium.size(), is(1000));
    }

    @Test
    public void whenAddIdenticalKeysValuesShouldAddOnlyLastValue() {

        Stuff firstStuff = new Stuff("Candy");
        Stuff secondStuff = new Stuff("Candy");

        Compendium<Stuff,String> compendium = new Compendium();
        compendium.insert(firstStuff, "first");
        compendium.insert(firstStuff, "second");

        assertThat(compendium.size(), is(1));
    }

    @Test
    public void whenAddIdenticalHashCodeValuesShouldAddOnlyLastValue() {

        Stuff firstStuff = new Stuff("Candy");
        Stuff secondStuff = new Stuff("Candy");

        Compendium<Stuff,String> compendium = new Compendium();
        compendium.insert(firstStuff, "first");
        compendium.insert(secondStuff, "second");

        assertThat(compendium.get(firstStuff), is("second"));
    }

    @Test
    public void whenDeleteShouldDelete() {

        Stuff firstStuff = new Stuff("Candy");
        Stuff secondStuff = new Stuff("Apple");

        Compendium<Stuff,String> compendium = new Compendium();
        compendium.insert(firstStuff, "first");
        compendium.insert(secondStuff, "second");
        compendium.delete(firstStuff);

        assertThat(compendium.size(), is(1));
    }

    @Test
    public void whenLoopShouldHaveCorrectCursor() {

        Stuff firstStuff = new Stuff("Candy");
        Stuff secondStuff = new Stuff("Apple");

        Compendium<Stuff,String> compendium = new Compendium();
        compendium.insert(firstStuff, "first");
        compendium.insert(secondStuff, "second");

        Integer count = 0;
        Iterator iterator = compendium.iterator();
        while (iterator.hasNext()) {
            Object stuff = iterator.next();
            if (stuff instanceof Stuff) {
                count++;
            }
        }

        assertThat(count, is(2));
    }

}