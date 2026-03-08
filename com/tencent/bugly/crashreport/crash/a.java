package com.tencent.bugly.crashreport.crash;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements Comparable<a> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f5457a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f5458b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f5459c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5460d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f5461e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f5462f = 0;

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(a aVar) {
        a aVar2 = aVar;
        if (aVar2 == null) {
            return 1;
        }
        long j = this.f5458b - aVar2.f5458b;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }
}