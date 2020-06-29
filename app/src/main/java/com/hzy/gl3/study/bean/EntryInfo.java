package com.hzy.gl3.study.bean;

import java.io.Serializable;

public class EntryInfo implements Serializable {

    public String title;
    public int id;

    public EntryInfo(String title, int id) {
        this.title = title;
        this.id = id;
    }
}
