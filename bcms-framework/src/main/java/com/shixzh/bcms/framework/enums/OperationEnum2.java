package com.shixzh.bcms.framework.enums;

import java.util.EnumSet;

public enum OperationEnum2 {
    PLUS("+") {
        double apply(double x, double y) {
            return (x + y);
        }
    },
    MINUS("-") {
        double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;
    private EnumSet enumSet;

    OperationEnum2(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    abstract double apply(double x, double y);
}
