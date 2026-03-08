package com.ido.life.module.user.sportrecord.model;

/* JADX INFO: loaded from: classes3.dex */
public class SportScreenType {
    private int index;
    private int typeId;
    private String typeName;

    public SportScreenType() {
    }

    public SportScreenType(String str, int i, int i2) {
        this.typeName = str;
        this.typeId = i;
        this.index = i2;
    }

    public String getTypeName() {
        return this.typeName;
    }

    public void setTypeName(String str) {
        this.typeName = str;
    }

    public int getTypeId() {
        return this.typeId;
    }

    public void setTypeId(int i) {
        this.typeId = i;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public String toString() {
        return "SportScreenType{typeName='" + this.typeName + "', typeId=" + this.typeId + ", index=" + this.index + '}';
    }
}