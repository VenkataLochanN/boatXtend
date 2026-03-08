package com.baidu.mapsdkplatform.comapi.synchronization.data;

/* JADX INFO: loaded from: classes.dex */
public final class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3733a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3734b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f3735c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f3736d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3737e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f3738f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private a f3739g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3740h;
    private String i;
    private int j;

    public enum a {
        GPS,
        COMMON,
        BD09LL,
        BD09MC
    }

    public enum b {
        DRIVING,
        RIDING
    }

    public f() {
        this.f3736d = b.DRIVING;
        this.f3738f = 15;
        this.f3739g = a.BD09LL;
        this.f3736d = b.DRIVING;
        this.f3739g = a.BD09LL;
        this.f3738f = 15;
    }

    public String a() {
        return this.f3733a;
    }

    public void a(int i) {
        this.f3737e = i;
    }

    public void a(String str) {
        this.f3733a = str;
    }

    public String b() {
        return this.f3734b;
    }

    public void b(String str) {
        this.f3734b = str;
    }

    public String c() {
        return this.f3735c;
    }

    public void c(String str) {
        this.f3735c = str;
    }

    public String d() {
        return this.f3740h;
    }

    public void d(String str) {
        this.f3740h = str;
    }

    public String e() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }

    public a f() {
        return this.f3739g;
    }

    public b g() {
        return this.f3736d;
    }

    public int h() {
        return this.f3737e;
    }

    public int i() {
        return this.f3738f;
    }

    public int j() {
        return this.j;
    }
}