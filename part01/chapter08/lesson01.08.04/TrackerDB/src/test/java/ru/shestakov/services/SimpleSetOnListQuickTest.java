package ru.shestakov.services;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SimpleSetOnListQuickTest {

    @Test
    public void compareSpeed() {

        SimpleSetOnList<Integer> sa = new SimpleSetOnList<Integer>();

        long time1 = System.currentTimeMillis();
        int count=0;
        while (count < 2000) {
            sa.add(count++);
        }
        System.out.println("simple: " + (System.currentTimeMillis()-time1) + " ms");


        SimpleSetOnListQuick<Integer> saq = new SimpleSetOnListQuick<Integer>();

        long time2 = System.currentTimeMillis();
        int countq=0;
        while (countq < 2000) {
            saq.add(countq++);
        }
        saq.add(countq);
        System.out.println("quick: " + (System.currentTimeMillis()-time2) + " ms");

    }
}