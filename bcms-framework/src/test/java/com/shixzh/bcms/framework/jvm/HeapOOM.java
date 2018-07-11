package com.shixzh.bcms.framework.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 1. Java堆溢出
 * 配置参数: -Xms20m -Xmx20m;
 * 抛出OOM: java.lang.OutOfMemoryError.
 */
class HeapOOM {

    static class OOMObject {
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());//对象位于堆内存中
        }
    }
}

