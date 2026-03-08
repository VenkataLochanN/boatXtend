package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.DPoint;

/* JADX INFO: compiled from: Bounds.java */
/* JADX INFO: loaded from: classes.dex */
public class du {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double f677a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f678b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f679c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f680d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final double f681e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final double f682f;

    public du(double d2, double d3, double d4, double d5) {
        this.f677a = d2;
        this.f678b = d4;
        this.f679c = d3;
        this.f680d = d5;
        this.f681e = (d2 + d3) / 2.0d;
        this.f682f = (d4 + d5) / 2.0d;
    }

    public boolean a(double d2, double d3) {
        return this.f677a <= d2 && d2 <= this.f679c && this.f678b <= d3 && d3 <= this.f680d;
    }

    public boolean a(DPoint dPoint) {
        return a(dPoint.x, dPoint.y);
    }

    public boolean a(double d2, double d3, double d4, double d5) {
        return d2 < this.f679c && this.f677a < d3 && d4 < this.f680d && this.f678b < d5;
    }

    public boolean a(du duVar) {
        return a(duVar.f677a, duVar.f679c, duVar.f678b, duVar.f680d);
    }

    public boolean b(du duVar) {
        return duVar.f677a >= this.f677a && duVar.f679c <= this.f679c && duVar.f678b >= this.f678b && duVar.f680d <= this.f680d;
    }
}