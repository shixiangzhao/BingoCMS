package com.shixzh.bcms.framework.mq;

public class PCData {

    private int data;

    public PCData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "data=" + data +
                '}';
    }
}
