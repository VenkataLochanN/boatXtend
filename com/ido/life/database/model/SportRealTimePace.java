package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportRealTimePace {
    private String realTimeSpace;

    public SportRealTimePace() {
    }

    public SportRealTimePace(String str) {
        this.realTimeSpace = str;
    }

    public String getRealTimeSpace() {
        return this.realTimeSpace;
    }

    public void setRealTimeSpace(String str) {
        this.realTimeSpace = str;
    }

    public String toString() {
        return "SportRealTimePace{realTimeSpace='" + this.realTimeSpace + "'}";
    }
}