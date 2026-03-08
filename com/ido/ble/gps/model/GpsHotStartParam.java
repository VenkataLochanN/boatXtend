package com.ido.ble.gps.model;

/* JADX INFO: loaded from: classes2.dex */
public class GpsHotStartParam {
    private int tcxo_offset = 200;
    private int longitude = 0;
    private int latitude = 0;
    private int altitude = 0;

    public int getAltitude() {
        return this.altitude;
    }

    public int getLatitude() {
        return this.latitude;
    }

    public int getLongitude() {
        return this.longitude;
    }

    public int getTcxo_offset() {
        return this.tcxo_offset;
    }

    public void setAltitude(double d2) {
        this.altitude = (int) (d2 * 10.0d);
    }

    public void setLatitude(double d2) {
        this.latitude = (int) (d2 * Math.pow(10.0d, 6.0d));
    }

    public void setLongitude(double d2) {
        this.longitude = (int) (d2 * Math.pow(10.0d, 6.0d));
    }

    public void setTcxo_offset(int i) {
        if (i >= 0) {
            this.tcxo_offset = i;
        }
    }

    public String toString() {
        return "GpsHotStartParam{tcxo_offset=" + this.tcxo_offset + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + '}';
    }
}