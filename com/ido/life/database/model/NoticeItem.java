package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class NoticeItem {
    private int offset;
    private int value;

    public NoticeItem() {
    }

    public NoticeItem(int i, int i2) {
        this.offset = i;
        this.value = i2;
    }

    public int getOffset() {
        return this.offset;
    }

    public void setOffset(int i) {
        this.offset = i;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public String toString() {
        return "NoticeItem{offset=" + this.offset + ", value=" + this.value + '}';
    }
}