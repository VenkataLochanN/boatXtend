package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: CollectionManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class lg {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static long f1659c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    lj f1660a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    kz f1661b;

    public final void a() {
        try {
            if (this.f1661b == null || this.f1660a == null) {
                return;
            }
            kz.a(this.f1660a.a());
        } catch (Throwable th) {
            kg.a(th, "cl", "upc");
        }
    }
}