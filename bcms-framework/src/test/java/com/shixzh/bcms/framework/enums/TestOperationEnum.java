package com.shixzh.bcms.framework.enums;

import org.junit.Test;

public class TestOperationEnum {

    @Test
    public void testOperationEnum2() {
        double x = Double.valueOf("5");
        double y = Double.valueOf("6");
        for (OperationEnum2 op : OperationEnum2.values()) {
            String result = String.format("%f %s %f = %f", x, op, y, op.apply(x, y));
            System.out.println(result);
        }
        OperationEnum2 plus = OperationEnum2.valueOf("PLUS");
        System.out.println(plus.toString());
    }
}
