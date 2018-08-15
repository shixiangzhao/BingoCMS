package com.shixzh.bcms.framework.util;

import java.util.List;

/**
 * Created by zhaoshixiang on 2018/8/15.
 */
public class SheetConfig<T> {

    private String sheetname; //sheet名称

    private String title; //表格标题，传“空值”，表示无标题

    private Class<T> cls; //实体对象，通过annotation.ExportField获取标题

    private List<T> data;

    public SheetConfig() {
    }

    public SheetConfig(String sheetname, String title, Class<T> cls, List<T> data) {
        this.sheetname = sheetname;
        this.title = title;
        this.cls = cls;
        this.data = data;
    }

    public String getSheetname() {
        return sheetname;
    }

    public void setSheetname(String sheetname) {
        this.sheetname = sheetname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Class<T> getCls() {
        return cls;
    }

    public void setCls(Class<T> cls) {
        this.cls = cls;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "SheetConfig{" +
                "sheetname='" + sheetname + '\'' +
                ", title='" + title + '\'' +
                ", cls=" + cls +
                ", data=" + data +
                '}';
    }
}
