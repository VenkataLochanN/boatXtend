package com.amap.api.trace;

/* JADX INFO: loaded from: classes.dex */
public class TraceLocation {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f1954a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f1955b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private float f1956c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1957d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private long f1958e;

    public TraceLocation(double d2, double d3, float f2, float f3, long j) {
        this.f1954a = a(d2);
        this.f1955b = a(d3);
        this.f1956c = (int) ((f2 * 3600.0f) / 1000.0f);
        this.f1957d = (int) f3;
        this.f1958e = j;
    }

    public TraceLocation() {
    }

    public double getLatitude() {
        return this.f1954a;
    }

    public void setLatitude(double d2) {
        this.f1954a = a(d2);
    }

    public double getLongitude() {
        return this.f1955b;
    }

    public void setLongitude(double d2) {
        this.f1955b = a(d2);
    }

    public float getSpeed() {
        return this.f1956c;
    }

    public void setSpeed(float f2) {
        this.f1956c = (int) ((f2 * 3600.0f) / 1000.0f);
    }

    public float getBearing() {
        return this.f1957d;
    }

    public void setBearing(float f2) {
        this.f1957d = (int) f2;
    }

    public long getTime() {
        return this.f1958e;
    }

    public void setTime(long j) {
        this.f1958e = j;
    }

    private static double a(double d2) {
        return Math.round(d2 * 1000000.0d) / 1000000.0d;
    }

    public TraceLocation copy() {
        TraceLocation traceLocation = new TraceLocation();
        traceLocation.f1957d = this.f1957d;
        traceLocation.f1954a = this.f1954a;
        traceLocation.f1955b = this.f1955b;
        traceLocation.f1956c = this.f1956c;
        traceLocation.f1958e = this.f1958e;
        return traceLocation;
    }

    public String toString() {
        return this.f1954a + ",longtitude " + this.f1955b + ",speed " + this.f1956c + ",bearing " + this.f1957d + ",time " + this.f1958e;
    }
}