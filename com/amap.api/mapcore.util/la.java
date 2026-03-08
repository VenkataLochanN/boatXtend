package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: AmapCell.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class la {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f1643a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1644b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1645c = 99;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1646d = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f1647e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f1648f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1649g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1650h;
    public boolean i;

    public la(boolean z, boolean z2) {
        this.i = true;
        this.f1650h = z;
        this.i = z2;
    }

    @Override // 
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract la clone();

    public final void a(la laVar) {
        if (laVar != null) {
            this.f1643a = laVar.f1643a;
            this.f1644b = laVar.f1644b;
            this.f1645c = laVar.f1645c;
            this.f1646d = laVar.f1646d;
            this.f1647e = laVar.f1647e;
            this.f1648f = laVar.f1648f;
            this.f1649g = laVar.f1649g;
            this.f1650h = laVar.f1650h;
            this.i = laVar.i;
        }
    }

    public String toString() {
        return "AmapCell{mcc=" + this.f1643a + ", mnc=" + this.f1644b + ", signalStrength=" + this.f1645c + ", asulevel=" + this.f1646d + ", lastUpdateSystemMills=" + this.f1647e + ", lastUpdateUtcMills=" + this.f1648f + ", age=" + this.f1649g + ", main=" + this.f1650h + ", newapi=" + this.i + '}';
    }
}