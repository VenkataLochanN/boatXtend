package com.baidu.mapapi.map;

import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final double f3040a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final double f3041b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f3042c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final double f3043d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final double f3044e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final double f3045f;

    public h(double d2, double d3, double d4, double d5) {
        this.f3040a = d2;
        this.f3041b = d4;
        this.f3042c = d3;
        this.f3043d = d5;
        this.f3044e = (d2 + d3) / 2.0d;
        this.f3045f = (d4 + d5) / 2.0d;
    }

    public boolean a(double d2, double d3) {
        return this.f3040a <= d2 && d2 <= this.f3042c && this.f3041b <= d3 && d3 <= this.f3043d;
    }

    public boolean a(double d2, double d3, double d4, double d5) {
        return d2 < this.f3042c && this.f3040a < d3 && d4 < this.f3043d && this.f3041b < d5;
    }

    public boolean a(Point point) {
        return a(point.x, point.y);
    }

    public boolean a(h hVar) {
        return a(hVar.f3040a, hVar.f3042c, hVar.f3041b, hVar.f3043d);
    }

    public boolean b(h hVar) {
        return hVar.f3040a >= this.f3040a && hVar.f3042c <= this.f3042c && hVar.f3041b >= this.f3041b && hVar.f3043d <= this.f3043d;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("minX: " + this.f3040a);
        sb.append(" minY: " + this.f3041b);
        sb.append(" maxX: " + this.f3042c);
        sb.append(" maxY: " + this.f3043d);
        sb.append(" midX: " + this.f3044e);
        sb.append(" midY: " + this.f3045f);
        return sb.toString();
    }
}