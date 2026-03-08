package com.loc;

import com.amap.api.location.AMapLocation;

/* JADX INFO: compiled from: LastLocationInfo.java */
/* JADX INFO: loaded from: classes3.dex */
@af(a = "c")
public class ea {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ag(a = "a2", b = 6)
    private String f5112a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ag(a = "a3", b = 5)
    private long f5113b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @ag(a = "a4", b = 6)
    private String f5114c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private AMapLocation f5115d;

    public final AMapLocation a() {
        return this.f5115d;
    }

    public final void a(long j) {
        this.f5113b = j;
    }

    public final void a(AMapLocation aMapLocation) {
        this.f5115d = aMapLocation;
    }

    public final void a(String str) {
        this.f5114c = str;
    }

    public final String b() {
        return this.f5114c;
    }

    public final void b(String str) {
        this.f5112a = str;
    }

    public final String c() {
        return this.f5112a;
    }

    public final long d() {
        return this.f5113b;
    }
}