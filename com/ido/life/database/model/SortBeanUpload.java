package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SortBeanUpload {
    private String attrName;
    private String attrValue;
    private long id;
    private long timestamp;

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getAttrName() {
        return this.attrName;
    }

    public void setAttrName(String str) {
        this.attrName = str;
    }

    public String getAttrValue() {
        return this.attrValue;
    }

    public void setAttrValue(String str) {
        this.attrValue = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public String toString() {
        return "SortBean{id=" + this.id + ", attrName='" + this.attrName + "', attrValue='" + this.attrValue + "', timestamp=" + this.timestamp + '}';
    }
}