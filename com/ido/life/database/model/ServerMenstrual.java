package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class ServerMenstrual {
    private String date;
    private String deviceName;
    private String items;
    private String latestDate;
    private int mensesCycle;
    private int mensesDays;
    private String sourceMac;
    private long timestamp;
    private boolean uploaded;

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getLatestDate() {
        return this.latestDate;
    }

    public void setLatestDate(String str) {
        this.latestDate = str;
    }

    public int getMensesCycle() {
        return this.mensesCycle;
    }

    public void setMensesCycle(int i) {
        this.mensesCycle = i;
    }

    public int getMensesDays() {
        return this.mensesDays;
    }

    public void setMensesDays(int i) {
        this.mensesDays = i;
    }

    public String getItems() {
        return this.items;
    }

    public void setItems(String str) {
        this.items = str;
    }

    public String getSourceMac() {
        return this.sourceMac;
    }

    public void setSourceMac(String str) {
        this.sourceMac = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public boolean isUploaded() {
        return this.uploaded;
    }

    public void setUploaded(boolean z) {
        this.uploaded = z;
    }

    public String toString() {
        return "ServerMenstrual{date='" + this.date + "', latestDate='" + this.latestDate + "', mensesCycle=" + this.mensesCycle + ", mensesDays=" + this.mensesDays + ", items='" + this.items + "', sourceMac='" + this.sourceMac + "', deviceName='" + this.deviceName + "', timestamp=" + this.timestamp + ", uploaded=" + this.uploaded + '}';
    }
}