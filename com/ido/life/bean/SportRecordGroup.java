package com.ido.life.bean;

/* JADX INFO: loaded from: classes2.dex */
public class SportRecordGroup {
    private String mMonth;
    private String mYear;

    public SportRecordGroup() {
    }

    public SportRecordGroup(String str, String str2) {
        this.mYear = str;
        this.mMonth = str2;
    }

    public String getYear() {
        return this.mYear;
    }

    public void setYear(String str) {
        this.mYear = str;
    }

    public String getMonth() {
        return this.mMonth;
    }

    public void setMonth(String str) {
        this.mMonth = str;
    }
}