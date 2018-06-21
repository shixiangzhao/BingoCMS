package com.shixzh.bcms.framework.enums;

public enum OperationEnum {
    PLUS,
    MINUS,
    TIMES,
    DIVIDE;

    double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }
        throw new AssertionError("Unknown operation: " + this);
    }
}
