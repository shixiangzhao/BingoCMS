package com.shixzh.bcms.framework.enums;

public interface Operation {

    /**
     * 对x、y进行运算，返回结果
     * @param x
     * @param y
     * @return
     */
    double apply(double x, double y);
}
