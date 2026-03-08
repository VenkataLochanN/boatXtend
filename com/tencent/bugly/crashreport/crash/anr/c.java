package com.tencent.bugly.crashreport.crash.anr;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f5494a = "";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f5495b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f5496c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f5497d;

    public c(String str, long j) {
        this.f5496c = "";
        this.f5497d = false;
        this.f5496c = str == null ? "" : str;
        this.f5495b = j;
        this.f5497d = false;
    }

    public final String a() {
        return this.f5496c;
    }

    public final void a(String str) {
        if (str == null) {
            str = "";
        }
        this.f5494a = str;
    }

    public final String b() {
        return this.f5494a;
    }

    public final long c() {
        return this.f5495b;
    }

    public final boolean d() {
        return this.f5497d;
    }

    public final void a(boolean z) {
        this.f5497d = z;
    }
}