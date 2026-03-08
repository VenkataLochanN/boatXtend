package com.amap.api.mapcore.util;

import java.util.Locale;

/* JADX INFO: compiled from: Cgi.java */
/* JADX INFO: loaded from: classes.dex */
public final class li {
    public int k;
    public boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1664a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1665b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1666c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1667d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1668e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1669f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1670g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f1671h = 0;
    public int i = 0;
    public int j = -113;
    public short l = 0;
    public long m = 0;
    public int o = 32767;
    public boolean p = true;

    public li(int i, boolean z) {
        this.k = 0;
        this.n = false;
        this.k = i;
        this.n = z;
    }

    public final int a() {
        return this.f1666c;
    }

    public final int b() {
        return this.f1667d;
    }

    public final int c() {
        return this.f1671h;
    }

    public final int d() {
        return this.i;
    }

    public final int e() {
        return this.j;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof li)) {
            li liVar = (li) obj;
            int i = liVar.k;
            if (i != 1) {
                return i != 2 ? i != 3 ? i == 4 && this.k == 4 && liVar.f1666c == this.f1666c && liVar.f1667d == this.f1667d && liVar.f1665b == this.f1665b : this.k == 3 && liVar.f1666c == this.f1666c && liVar.f1667d == this.f1667d && liVar.f1665b == this.f1665b : this.k == 2 && liVar.i == this.i && liVar.f1671h == this.f1671h && liVar.f1670g == this.f1670g;
            }
            if (this.k == 1 && liVar.f1666c == this.f1666c && liVar.f1667d == this.f1667d && liVar.f1665b == this.f1665b) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode;
        int i;
        int iHashCode2 = String.valueOf(this.k).hashCode();
        if (this.k == 2) {
            iHashCode = String.valueOf(this.i).hashCode() + String.valueOf(this.f1671h).hashCode();
            i = this.f1670g;
        } else {
            iHashCode = String.valueOf(this.f1666c).hashCode() + String.valueOf(this.f1667d).hashCode();
            i = this.f1665b;
        }
        return iHashCode2 + iHashCode + String.valueOf(i).hashCode();
    }

    public final String toString() {
        int i = this.k;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "unknown" : String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.f1666c), Integer.valueOf(this.f1667d), Integer.valueOf(this.f1665b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.f1666c), Integer.valueOf(this.f1667d), Integer.valueOf(this.f1665b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.i), Integer.valueOf(this.f1671h), Integer.valueOf(this.f1670g), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)) : String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f1666c), Integer.valueOf(this.f1667d), Integer.valueOf(this.f1665b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
    }
}