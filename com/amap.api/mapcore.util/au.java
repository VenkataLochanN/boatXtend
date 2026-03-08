package com.amap.api.mapcore.util;

import com.autonavi.amap.mapcore.IPoint;

/* JADX INFO: compiled from: IBounds.java */
/* JADX INFO: loaded from: classes.dex */
public class au {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f191a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f192b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f193c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f194d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f195e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f196f;

    public au(int i, int i2, int i3, int i4) {
        a(i, i2, i3, i4);
    }

    public void a(int i, int i2, int i3, int i4) {
        this.f191a = i;
        this.f192b = i3;
        this.f193c = i2;
        this.f194d = i4;
        this.f195e = (i + i2) / 2;
        this.f196f = (i3 + i4) / 2;
    }

    public boolean a(int i, int i2) {
        return this.f191a <= i && i <= this.f193c && this.f192b <= i2 && i2 <= this.f194d;
    }

    public boolean a(IPoint iPoint) {
        if (iPoint == null) {
            return false;
        }
        return a(iPoint.x, iPoint.y);
    }

    public boolean b(int i, int i2, int i3, int i4) {
        return i < this.f193c && this.f191a < i2 && i3 < this.f194d && this.f192b < i4;
    }

    public boolean a(au auVar) {
        if (auVar == null) {
            return false;
        }
        return b(auVar.f191a, auVar.f193c, auVar.f192b, auVar.f194d);
    }
}