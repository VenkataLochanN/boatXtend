package com.ido.alexa.data;

/* JADX INFO: loaded from: classes2.dex */
public class AvsItem {
    String token;

    public AvsItem(String str) {
        this.token = str;
    }

    public String getToken() {
        return this.token;
    }

    public AvsItem() {
    }

    public String toString() {
        return "AvsItem{token='" + this.token + "'}";
    }
}