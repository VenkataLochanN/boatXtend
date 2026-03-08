package com.ido.ble.gps.database;

/* JADX INFO: loaded from: classes2.dex */
public class HealthGpsItem {
    private long dId;
    private Long date;
    private Long healthGpsItemId;
    private Double latitude;
    private Double longitude;

    public HealthGpsItem() {
    }

    public HealthGpsItem(Long l, long j, Double d2, Double d3, Long l2) {
        this.healthGpsItemId = l;
        this.dId = j;
        this.longitude = d2;
        this.latitude = d3;
        this.date = l2;
    }

    public long getDId() {
        return this.dId;
    }

    public Long getDate() {
        return this.date;
    }

    public Long getHealthGpsItemId() {
        return this.healthGpsItemId;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setDId(long j) {
        this.dId = j;
    }

    public void setDate(Long l) {
        this.date = l;
    }

    public void setHealthGpsItemId(Long l) {
        this.healthGpsItemId = l;
    }

    public void setLatitude(Double d2) {
        this.latitude = d2;
    }

    public void setLongitude(Double d2) {
        this.longitude = d2;
    }
}