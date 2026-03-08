package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class Language {
    private boolean mChecked;
    private int mCode;
    private String mName;
    private String mRegion;

    public Language() {
    }

    public Language(String str, int i) {
        this.mName = str;
        this.mCode = i;
    }

    public Language(String str, String str2, boolean z) {
        this.mName = str;
        this.mRegion = str2;
        this.mChecked = z;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public int getCode() {
        return this.mCode;
    }

    public void setCode(int i) {
        this.mCode = i;
    }

    public String getRegion() {
        return this.mRegion;
    }

    public void setRegion(String str) {
        this.mRegion = str;
    }

    public boolean isChecked() {
        return this.mChecked;
    }

    public void setChecked(boolean z) {
        this.mChecked = z;
    }
}