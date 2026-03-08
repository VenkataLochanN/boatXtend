package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class AlarmType implements Serializable {
    public int nameResId;
    public int type;

    public AlarmType() {
    }

    public AlarmType(int i, int i2) {
        this.type = i;
        this.nameResId = i2;
    }

    public String toString() {
        return "AlarmType{type=" + this.type + ", nameResId=" + this.nameResId + '}';
    }
}