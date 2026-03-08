package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportItem {
    private int interval;
    private String items;

    public SportItem() {
    }

    public SportItem(String str) {
        this.items = str;
    }

    public String getItmes() {
        return this.items;
    }

    public void setItmes(String str) {
        this.items = str;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public String toString() {
        return "SportItem{interval=" + this.interval + ", items='" + this.items + "'}";
    }
}