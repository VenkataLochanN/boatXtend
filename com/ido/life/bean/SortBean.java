package com.ido.life.bean;

import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public class SortBean implements Serializable {
    private int nameId;
    private boolean selected;
    private int type;

    public SortBean() {
    }

    public SortBean(int i, boolean z) {
        this.type = i;
        this.selected = z;
    }

    public SortBean(boolean z, int i) {
        this.selected = z;
        this.nameId = i;
    }

    public SortBean(int i, int i2, boolean z) {
        this.type = i;
        this.nameId = i2;
        this.selected = z;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public boolean isSelected() {
        return this.selected;
    }

    public void setSelected(boolean z) {
        this.selected = z;
    }

    public int getNameId() {
        return this.nameId;
    }

    public void setNameId(int i) {
        this.nameId = i;
    }

    public String toString() {
        return "SortBean{type=" + this.type + ", selected=" + this.selected + ", nameId=" + this.nameId + '}';
    }
}