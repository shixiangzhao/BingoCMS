package com.shixzh.bcms.framework.enums;

import java.util.Arrays;
import java.util.Collection;

public class TestOperationImplEnum {
    public static void main(String[] args) {
        double x = 5.0;
        double y = 6.0;
        test(OperationImplEnum1.class, x, y);
        System.out.println("----------------------------");
        test1(Arrays.asList(OperationImplEnum1.values()), x, y);
    }

    private static <T extends Enum<T> & Operation> void test(Class<T> opSet, double x, double y) {
        for (Operation op : opSet.getEnumConstants()) {
            String result = String.format("%f %s %f = %f", x, op, y, op.apply(x, y));
            System.out.println(result);
        }
    }

    private static void test1(Collection<? extends Operation> opSet, double x, double y) {
        for (Operation op : opSet) {
            String result = String.format("%f %s %f = %f", x, op, y, op.apply(x, y));
            System.out.println(result);
        }
    }
}
