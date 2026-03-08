package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: DownloadManager.java */
/* JADX INFO: loaded from: classes.dex */
public class il {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private in f1358a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private iq f1359b;

    /* JADX INFO: compiled from: DownloadManager.java */
    public interface a {
        void onDownload(byte[] bArr, long j);

        void onException(Throwable th);

        void onFinish();

        void onStop();
    }

    public il(iq iqVar) {
        this(iqVar, 0L, -1L);
    }

    public il(iq iqVar, long j, long j2) {
        this(iqVar, j, j2, false);
    }

    public il(iq iqVar, long j, long j2, boolean z) {
        this.f1359b = iqVar;
        this.f1358a = new in(this.f1359b.f1399a, this.f1359b.f1400b, iqVar.f1401c == null ? null : iqVar.f1401c, z);
        this.f1358a.b(j2);
        this.f1358a.a(j);
    }

    public void a(a aVar) {
        this.f1358a.a(this.f1359b.getURL(), this.f1359b.c(), this.f1359b.isIPRequest(), this.f1359b.getIPDNSName(), this.f1359b.getRequestHead(), this.f1359b.getParams(), this.f1359b.getEntityBytes(), aVar, in.a(2, this.f1359b));
    }

    public void a() {
        this.f1358a.a();
    }
}