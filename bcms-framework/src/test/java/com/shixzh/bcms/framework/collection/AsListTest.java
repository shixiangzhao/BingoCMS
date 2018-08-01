package com.shixzh.bcms.framework.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AsListTest {

    @Test
    public void testAsList() {
        List<Integer> asList = Arrays.asList(47, 48, 67);
        //asList.add(45);
        for (Integer i : asList) {
            System.out.println("print array: " + i);
        }

        for (int i = 0; i < asList.size(); i++) {
            System.out.println("print array by id: " + asList.get(i));
        }

        //asList.remove(1);
    }

    @Test
    public void testAsListContains() {

        List<String> asList = Arrays.asList("huahua", "xiaobai", "yunhao");
        //asList.add(45);
        for (String i : asList) {
            System.out.println("print array: " + i);
            if (asList.contains("xiaobai")) {
                System.out.println("I'm xiaobai.");
            }
        }
    }

    @Test
    public void testIntegerEqual() {

        List<Integer> asList = Arrays.asList(47, 48, 67);
        //asList.add(45);
        for (Integer i : asList) {
            System.out.println("print array: " + i);
            if (asList.contains(47)) {
                System.out.println("I'm xiaobai.");
            }
        }
    }
}
