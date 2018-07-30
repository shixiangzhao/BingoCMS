package com.shixzh.bcms.framework.basictype;

import org.junit.Test;

public class CharTest {

    @Test
    public void testChar() {
        char han = '永';
        System.out.println((short) han);
        System.out.format("%x", (short) han);
    }

    @Test
    public void testUnicode() {
        char han = 0x6c38;
        char han2 = 27704;
        System.out.println(han);
        System.out.println(han2);
    }
}
