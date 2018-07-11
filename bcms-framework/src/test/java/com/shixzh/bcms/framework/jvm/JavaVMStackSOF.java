package com.shixzh.bcms.framework.jvm;

/**
 * 2. 虚拟机栈和本地方法栈溢出
 * 配置参数： -Xss128k
 * 分析：在单线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配时，抛出的都是StackOverflowError异常。
 * 注：一直创建线程，会导致OOM
 */
class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }
}
