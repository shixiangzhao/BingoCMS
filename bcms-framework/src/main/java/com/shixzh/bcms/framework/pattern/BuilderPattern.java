package com.shixzh.bcms.framework.pattern;

import java.util.Date;

public class BuilderPattern {
    private String name;
    private Integer age;
    private Date birthday;

    // 构造方法的入参为Builder
    public BuilderPattern(Builder builder) {
        this.age = builder.age;
        this.name = builder.name;
        this.birthday = builder.birthday;
    }

    static class Builder {
        private String name;
        private Integer age = 0;
        private Date birthday = new Date();

        // 必选字段
        public Builder(String n) {
            this.name = n;
        }

        //可选字段
        public Builder age(Integer a) {
            this.age = a;
            return this;
        }

        public Builder birthday(Date d) {
            this.birthday = d;
            return this;
        }

        public BuilderPattern builder() {
            return new BuilderPattern(this);
        }
    }

    @Override
    public String toString() {
        return "BuilderPattern{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    public static void main(String[] args) {
        BuilderPattern bp = new Builder("zhao").age(2).builder();
        System.out.println(bp);
    }
}
