package ru.shestakov.services;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetOnArrayQuickTest {

    @Test
    public void compareSpeed() {

        SimpleSetOnArray<Integer> sa = new SimpleSetOnArray<Integer>();

        long time1 = System.currentTimeMillis();
        int count=0;
        while (count < 10000) {
            sa.add(count++);
        }
        System.out.println("simple: " + (System.currentTimeMillis()-time1) + " ms");


        SimpleSetOnArrayQuick<Integer> saq = new SimpleSetOnArrayQuick<Integer>();

        long time2 = System.currentTimeMillis();
        int countq=0;
        while (countq < 10000) {
            saq.add(countq++);
        }

        System.out.println("quick: " + (System.currentTimeMillis()-time2) + " ms");

    }

}