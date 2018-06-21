package com.shixzh.bcms.framework.enums;

public class TestOperationEnum {
    public static void main(String[] args) {
        double x = Double.valueOf(args[0]);
        double y = Double.valueOf(args[1]);
        for (OperationEnum2 op : OperationEnum2.values()) {
            String result = String.format("%f %s %f = %f", x, op, y, op.apply(x, y));
            System.out.println(result);
        }
        OperationEnum2 plus = OperationEnum2.valueOf("PLUS");
        System.out.println(plus.toString());
    }
}
