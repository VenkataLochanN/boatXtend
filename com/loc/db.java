package com.loc;

/* JADX INFO: compiled from: AmapLocation.java */
/* JADX INFO: loaded from: classes3.dex */
public class db {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4960a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4961b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public long f4962c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f4963d = 0.0d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f4964e = 0.0d;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public double f4965f = 0.0d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public float f4966g = 0.0f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public float f4967h = 0.0f;
    public float i = 0.0f;
    public boolean j = false;

    public db(String str) {
        this.f4960a = str;
    }

    public final double a(db dbVar) {
        if (dbVar == null) {
            return 0.0d;
        }
        double d2 = this.f4964e;
        double d3 = this.f4963d;
        double d4 = dbVar.f4964e;
        double d5 = dbVar.f4963d;
        double d6 = (((90.0d - d3) * 21412.0d) / 90.0d) + 6356725.0d;
        double dCos = ((d4 * 0.01745329238474369d) - (d2 * 0.01745329238474369d)) * Math.cos((3.1415927410125732d * d3) / 180.0d) * d6;
        double d7 = ((d5 * 0.01745329238474369d) - (d3 * 0.01745329238474369d)) * d6;
        return Math.pow((dCos * dCos) + (d7 * d7), 0.5d);
    }
}