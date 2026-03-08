package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class ThirdLogin {
    private String accountType;
    private long id;
    private String updateAt;

    public ThirdLogin() {
    }

    public ThirdLogin(long j, String str, String str2) {
        this.id = j;
        this.accountType = str;
        this.updateAt = str2;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String str) {
        this.accountType = str;
    }

    public String getUpdateAt() {
        return this.updateAt;
    }

    public void setUpdateAt(String str) {
        this.updateAt = str;
    }

    public String toString() {
        return "ThirdLogin{id=" + this.id + ", accountType='" + this.accountType + "', updateAt='" + this.updateAt + "'}";
    }
}