package com.ido.alexa.data;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ApiResponse {
    private List<AvsItem> avsItems;
    private String message;
    private int responseCode;

    public List<AvsItem> getAvsItems() {
        return this.avsItems;
    }

    public void setAvsItems(List<AvsItem> list) {
        this.avsItems = list;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public void setResponseCode(int i) {
        this.responseCode = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}