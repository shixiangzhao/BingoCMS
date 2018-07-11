package com.shixzh.bcms.framework.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 4. 本机直接内存溢出
 * 配置参数： -Xmx20M -XX:MaxDirectMemorySize=10M
 * 分析：抛出异常时并没有真正向操作系统申请分配内存，而是通过计算得知内存无法分配，于是手动抛出异常，
 * 真正申请分配内存的方法是unsafe.allocateMemory()
 */
class DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
