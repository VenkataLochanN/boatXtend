package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: DTDownloadInfo.java */
/* JADX INFO: loaded from: classes.dex */
@hs(a = "update_item_download_info")
class bl {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ht(a = "mAdcode", b = 6)
    private String f272a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ht(a = "fileLength", b = 5)
    private long f273b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @ht(a = "splitter", b = 2)
    private int f274c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @ht(a = "startPos", b = 5)
    private long f275d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @ht(a = "endPos", b = 5)
    private long f276e;

    public bl() {
        this.f272a = "";
        this.f273b = 0L;
        this.f274c = 0;
        this.f275d = 0L;
        this.f276e = 0L;
    }

    public bl(String str, long j, int i, long j2, long j3) {
        this.f272a = "";
        this.f273b = 0L;
        this.f274c = 0;
        this.f275d = 0L;
        this.f276e = 0L;
        this.f272a = str;
        this.f273b = j;
        this.f274c = i;
        this.f275d = j2;
        this.f276e = j3;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}