package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: IDownloadListener.java */
/* JADX INFO: loaded from: classes.dex */
public interface ca {
    void a(long j, long j2);

    void a(a aVar);

    void n();

    void o();

    void p();

    /* JADX INFO: compiled from: IDownloadListener.java */
    public enum a {
        amap_exception(-1),
        network_exception(-1),
        file_io_exception(0),
        success_no_exception(1),
        cancel_no_exception(2);


        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f412f;

        a(int i) {
            this.f412f = i;
        }
    }
}