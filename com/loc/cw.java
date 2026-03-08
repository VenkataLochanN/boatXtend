package com.loc;

/* JADX INFO: compiled from: AmapCell.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class cw {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f4939a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f4940b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4941c = 99;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4942d = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f4943e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f4944f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4945g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4946h;
    public boolean i;

    public cw(boolean z, boolean z2) {
        this.i = true;
        this.f4946h = z;
        this.i = z2;
    }

    private static int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e2) {
            dg.a(e2);
            return 0;
        }
    }

    @Override // 
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract cw clone();

    public final void a(cw cwVar) {
        if (cwVar != null) {
            this.f4939a = cwVar.f4939a;
            this.f4940b = cwVar.f4940b;
            this.f4941c = cwVar.f4941c;
            this.f4942d = cwVar.f4942d;
            this.f4943e = cwVar.f4943e;
            this.f4944f = cwVar.f4944f;
            this.f4945g = cwVar.f4945g;
            this.f4946h = cwVar.f4946h;
            this.i = cwVar.i;
        }
    }

    public final int b() {
        return a(this.f4939a);
    }

    public final int c() {
        return a(this.f4940b);
    }

    public String toString() {
        return "AmapCell{mcc=" + this.f4939a + ", mnc=" + this.f4940b + ", signalStrength=" + this.f4941c + ", asulevel=" + this.f4942d + ", lastUpdateSystemMills=" + this.f4943e + ", lastUpdateUtcMills=" + this.f4944f + ", age=" + this.f4945g + ", main=" + this.f4946h + ", newapi=" + this.i + '}';
    }
}