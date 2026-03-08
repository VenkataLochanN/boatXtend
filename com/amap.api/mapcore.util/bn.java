package com.amap.api.mapcore.util;

import com.ido.life.dialog.CommonDialog;

/* JADX INFO: compiled from: DTInfo.java */
/* JADX INFO: loaded from: classes.dex */
@hs(a = "update_item")
public class bn {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    @ht(a = "localPath", b = 6)
    protected String f286h;

    @ht(a = "mCompleteCode", b = 2)
    protected int j;

    @ht(a = "mState", b = 2)
    public int l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ht(a = CommonDialog.EXTRA_TITLE, b = 6)
    protected String f279a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ht(a = "url", b = 6)
    protected String f280b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @ht(a = "mAdcode", b = 6)
    protected String f281c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @ht(a = "fileName", b = 6)
    protected String f282d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @ht(a = "version", b = 6)
    protected String f283e = "";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @ht(a = "lLocalLength", b = 5)
    protected long f284f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    @ht(a = "lRemoteLength", b = 5)
    protected long f285g = 0;

    @ht(a = "isProvince", b = 2)
    protected int i = 0;

    @ht(a = "mCityCode", b = 6)
    protected String k = "";

    @ht(a = "mPinyin", b = 6)
    public String m = "";

    public String d() {
        return this.f279a;
    }

    public String e() {
        return this.f283e;
    }

    public String f() {
        return this.f281c;
    }

    public void c(String str) {
        this.f281c = str;
    }

    public String g() {
        return this.f280b;
    }

    public int h() {
        return this.j;
    }

    public void d(String str) {
        this.k = str;
    }

    public String i() {
        return this.m;
    }

    public static String e(String str) {
        return "mAdcode='" + str + "'";
    }

    public static String f(String str) {
        return "mPinyin='" + str + "'";
    }
}