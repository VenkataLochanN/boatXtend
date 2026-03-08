package com.ido.life.database.model;

/* JADX INFO: loaded from: classes2.dex */
public class SportItemPace {
    private String britishItems;
    private String metricItems;

    public SportItemPace() {
    }

    public SportItemPace(String str, String str2) {
        this.metricItems = str;
        this.britishItems = str2;
    }

    public String getMetricItems() {
        return this.metricItems;
    }

    public void setMetricItems(String str) {
        this.metricItems = str;
    }

    public String getBritishItems() {
        return this.britishItems;
    }

    public void setBritishItems(String str) {
        this.britishItems = str;
    }

    public String toString() {
        return "SportItemPace{metricItems='" + this.metricItems + "', britishItems='" + this.britishItems + "'}";
    }
}