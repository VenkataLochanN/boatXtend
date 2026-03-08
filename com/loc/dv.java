package com.loc;

import java.util.Locale;

/* JADX INFO: compiled from: Cgi.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dv {
    public int k;
    public boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f5060a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f5061b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5062c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5063d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5064e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5065f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f5066g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f5067h = 0;
    public int i = 0;
    public int j = -113;
    public short l = 0;
    public long m = 0;
    public int o = 32767;
    public boolean p = true;

    public dv(int i, boolean z) {
        this.k = 0;
        this.n = false;
        this.k = i;
        this.n = z;
    }

    public final boolean equals(Object obj) {
        if (obj != null && (obj instanceof dv)) {
            dv dvVar = (dv) obj;
            int i = dvVar.k;
            if (i != 1) {
                return i != 2 ? i != 3 ? i == 4 && this.k == 4 && dvVar.f5062c == this.f5062c && dvVar.f5063d == this.f5063d && dvVar.f5061b == this.f5061b : this.k == 3 && dvVar.f5062c == this.f5062c && dvVar.f5063d == this.f5063d && dvVar.f5061b == this.f5061b : this.k == 2 && dvVar.i == this.i && dvVar.f5067h == this.f5067h && dvVar.f5066g == this.f5066g;
            }
            if (this.k == 1 && dvVar.f5062c == this.f5062c && dvVar.f5063d == this.f5063d && dvVar.f5061b == this.f5061b) {
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
            iHashCode = String.valueOf(this.i).hashCode() + String.valueOf(this.f5067h).hashCode();
            i = this.f5066g;
        } else {
            iHashCode = String.valueOf(this.f5062c).hashCode() + String.valueOf(this.f5063d).hashCode();
            i = this.f5061b;
        }
        return iHashCode2 + iHashCode + String.valueOf(i).hashCode();
    }

    public final String toString() {
        int i = this.k;
        return i != 1 ? i != 2 ? i != 3 ? i != 4 ? "unknown" : String.format(Locale.CHINA, "WCDMA lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.f5062c), Integer.valueOf(this.f5063d), Integer.valueOf(this.f5061b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "LTE lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b, pci=%d", Integer.valueOf(this.f5062c), Integer.valueOf(this.f5063d), Integer.valueOf(this.f5061b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n), Integer.valueOf(this.o)) : String.format(Locale.CHINA, "CDMA bid=%d, nid=%d, sid=%d, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.i), Integer.valueOf(this.f5067h), Integer.valueOf(this.f5066g), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n)) : String.format(Locale.CHINA, "GSM lac=%d, cid=%d, mnc=%s, valid=%b, sig=%d, age=%d, reg=%b", Integer.valueOf(this.f5062c), Integer.valueOf(this.f5063d), Integer.valueOf(this.f5061b), Boolean.valueOf(this.p), Integer.valueOf(this.j), Short.valueOf(this.l), Boolean.valueOf(this.n));
    }
}