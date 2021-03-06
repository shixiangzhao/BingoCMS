package com.shixzh.bcms.framework.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 3. 方法区和运行时常量池溢出
 * 分析：常量池已满，也会导致OOM
 */
class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        // 使用List保持着常量池引用，避免Full GC回收常量池行为
        List<String> list = new ArrayList<>();
        // 10MB的PermSize在integer范围内足够产生OOM了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }
}
