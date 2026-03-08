package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportGps {
    private int interval;
    private String items;

    public SportGps() {
    }

    public SportGps(int i, String str) {
        this.interval = i;
        this.items = str;
    }

    public int getInterval() {
        return this.interval;
    }

    public void setInterval(int i) {
        this.interval = i;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public String toString() {
        return "Gps{interval=" + this.interval + ", items='" + this.items + "'}";
    }
}