package com.shixzh.bcms.framework.pattern;

public class SingletonPattern {
    private static final SingletonPattern INSTANCE = new SingletonPattern();
    private SingletonPattern(){}
    public SingletonPattern getInstance(){
        return INSTANCE;
    }
    public void levelTheBuilding(){
        //...
    }
}