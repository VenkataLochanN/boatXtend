package com.ido.life.module.sport.bean;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class OxyGenProgressBean {
    private int age_max;
    private int age_min;
    private int gender;
    private List<Integer> list_oxygen;
    private int type;
    private String type_name;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getType_name() {
        return this.type_name;
    }

    public void setType_name(String str) {
        this.type_name = str;
    }

    public int getAge_min() {
        return this.age_min;
    }

    public void setAge_min(int i) {
        this.age_min = i;
    }

    public int getAge_max() {
        return this.age_max;
    }

    public void setAge_max(int i) {
        this.age_max = i;
    }

    public List<Integer> getList_oxygen() {
        return this.list_oxygen;
    }

    public void setList_oxygen(List<Integer> list) {
        this.list_oxygen = list;
    }

    public OxyGenProgressBean(int i, int i2, int i3, List<Integer> list) {
        this.gender = i;
        this.age_min = i2;
        this.age_max = i3;
        this.list_oxygen = list;
    }
}