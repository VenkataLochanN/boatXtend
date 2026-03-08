package com.realsil.sdk.dfu.model;

/* JADX INFO: loaded from: classes3.dex */
public class FileTypeInfo {
    public boolean Sd;
    public int bitNumber;
    public String name;

    public FileTypeInfo(int i, String str) {
        this.bitNumber = i;
        this.name = str;
    }

    public int getBitNumber() {
        return this.bitNumber;
    }

    public String getName() {
        return this.name;
    }

    public boolean isSelected() {
        return this.Sd;
    }

    public void setBitNumber(int i) {
        this.bitNumber = i;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setSelected(boolean z) {
        this.Sd = z;
    }
}