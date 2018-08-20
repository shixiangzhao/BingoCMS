package com.shixzh.bcms.framework.enums;

import java.util.EnumSet;

public class EnumSetTest {
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("EnumSet.noneOf");
        EnumSet<Student> set = EnumSet.noneOf(Student.class);
        set.add(Student.HARRY);
        set.add(Student.ROBBIE);
        set.add(Student.ROBIN);
        for (Student p : set)
            System.out.println(p);
        set.clear();

        System.out.println("EnumSet.allOf");
        set = EnumSet.allOf(Student.class);
        for (Student p : set)
            System.out.println(p);
        set.clear();

        System.out.println("EnumSet.Of one");
        set = EnumSet.of(Student.ROBIN);
        for (Student p : set)
            System.out.println(p);

        System.out.println("EnumSet.Of two");
        set = EnumSet.of(Student.ROBIN, Student.HARRY);
        for (Student p : set)
            System.out.println(p);
    }
}

enum Student {
    ROBIN("robin"),
    HARRY("harry", 40),
    ROBBIE("robbie");
    String name;
    int age;

    Student(String name) {
        this(name, 0);
    }

    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return name;
    }
}