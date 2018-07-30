package com.shixzh.bcms.framework.jvm;

import org.junit.Test;

public class AutoPlusTest {

    @Test
    public void testAutoPlus() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }
}
