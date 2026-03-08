package com.ido.life.module.sport.bean;

/* JADX INFO: loaded from: classes2.dex */
public class GpsData {
    public float accuracy;
    public double altitude;
    public float bearing;
    private long currentTimeMillis;
    private String dateTime;
    public int gpsAccuracyStatus;
    public double latitude;
    public double longitude;
    public float speed;

    public float getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(float f2) {
        this.accuracy = f2;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float f2) {
        this.speed = f2;
    }

    public float getBearing() {
        return this.bearing;
    }

    public void setBearing(float f2) {
        this.bearing = f2;
    }

    public int getGpsAccuracyStatus() {
        return this.gpsAccuracyStatus;
    }

    public void setGpsAccuracyStatus(int i) {
        this.gpsAccuracyStatus = i;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d2) {
        this.latitude = d2;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d2) {
        this.longitude = d2;
    }

    public long getCurrentTimeMillis() {
        return this.currentTimeMillis;
    }

    public void setCurrentTimeMillis(long j) {
        this.currentTimeMillis = j;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d2) {
        this.altitude = d2;
    }

    public String toString() {
        return "{accuracy=" + this.accuracy + ", altitude=" + this.altitude + ", speed=" + this.speed + ", bearing=" + this.bearing + ", gpsAccuracyStatus=" + this.gpsAccuracyStatus + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", currentTimeMillis=" + this.currentTimeMillis + ", dateTime='" + this.dateTime + "'}";
    }
}