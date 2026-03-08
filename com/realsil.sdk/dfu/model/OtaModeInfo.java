package com.realsil.sdk.dfu.model;

/* JADX INFO: loaded from: classes3.dex */
public class OtaModeInfo {
    public String name;
    public int pe;
    public int qe;

    public OtaModeInfo(int i) {
        this.pe = i;
    }

    public OtaModeInfo(int i, int i2) {
        this.pe = i;
        this.qe = i2;
    }

    public String getName() {
        return this.name;
    }

    public int getNameResId() {
        return this.qe;
    }

    public int getWorkmode() {
        return this.pe;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setNameResId(int i) {
        this.qe = i;
    }

    public void setWorkmode(int i) {
        this.pe = i;
    }
}