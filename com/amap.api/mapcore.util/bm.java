package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: DTFileInfo.java */
/* JADX INFO: loaded from: classes.dex */
@hs(a = "update_item_file")
class bm {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ht(a = "mAdcode", b = 6)
    private String f277a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ht(a = "file", b = 6)
    private String f278b;

    public bm() {
        this.f277a = "";
        this.f278b = "";
    }

    public bm(String str, String str2) {
        this.f277a = "";
        this.f278b = "";
        this.f277a = str;
        this.f278b = str2;
    }

    public String a() {
        return this.f278b;
    }

    public static String a(String str) {
        return "mAdcode='" + str + "'";
    }
}