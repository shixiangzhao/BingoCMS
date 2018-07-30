package com.shixzh.bcms.framework.jvm;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestClassLoader {
    @Test
    public void testClassLoader() {
        try {
            ClassLoader.getSystemClassLoader().loadClass("org.junit.Test");
            System.out.println("ClassLoader is: " + ClassLoader.getSystemClassLoader().toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClass() throws ClassNotFoundException {
        Class clazz = Class.forName("org.junit.Test");
        System.out.println("ClassLoader is: " + clazz);
    }

    @Test
    public void testContextClassLoader() throws ClassNotFoundException {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        cl.loadClass("org.junit.Test");
        System.out.println("ClassLoader is: " + cl);
    }

    @Test
    public void testIntegerClassLoader() {
        ClassLoader cl = Integer.class.getClassLoader();
        System.out.println("ClassLoader is:" + cl.toString());
    }

    @Test
    public void testDiskClassLoader() {
        // TODO Auto-generated method stub
        //创建自定义classloader对象。
        DiskClassLoader diskLoader = new DiskClassLoader("D:\\Temp");
        try {
            //加载class文件
            Class c = diskLoader.loadClass("com.frank.test.Test");

            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
                    //通过反射调用Test类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException
                        | NoSuchMethodException
                        | SecurityException |
                        IllegalArgumentException |
                        InvocationTargetException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void testParentClassLoader() {
        ClassLoader cl = Test.class.getClassLoader();
        System.out.println("ClassLoader is:" + cl.toString());
        System.out.println("ClassLoader\'s parent is:" + cl.getParent().toString());
    }
}
