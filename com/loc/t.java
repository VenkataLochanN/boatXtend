package com.loc;

import android.text.TextUtils;

/* JADX INFO: compiled from: SDKInfo.java */
/* JADX INFO: loaded from: classes3.dex */
@af(a = "a")
public final class t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ag(a = "a1", b = 6)
    private String f5310a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ag(a = "a2", b = 6)
    private String f5311b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @ag(a = "a6", b = 2)
    private int f5312c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @ag(a = "a3", b = 6)
    private String f5313d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @ag(a = "a4", b = 6)
    private String f5314e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @ag(a = "a5", b = 6)
    private String f5315f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5316g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5317h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    /* JADX INFO: compiled from: SDKInfo.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f5318a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f5319b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f5320c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f5321d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f5322e = true;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f5323f = "standard";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String[] f5324g = null;

        public a(String str, String str2, String str3) {
            this.f5318a = str2;
            this.f5319b = str2;
            this.f5321d = str3;
            this.f5320c = str;
        }

        public final a a(String str) {
            this.f5319b = str;
            return this;
        }

        public final a a(String[] strArr) {
            if (strArr != null) {
                this.f5324g = (String[]) strArr.clone();
            }
            return this;
        }

        public final t a() throws j {
            if (this.f5324g != null) {
                return new t(this, (byte) 0);
            }
            throw new j("sdk packages is null");
        }
    }

    private t() {
        this.f5312c = 1;
        this.l = null;
    }

    private t(a aVar) {
        this.f5312c = 1;
        this.l = null;
        this.f5316g = aVar.f5318a;
        this.f5317h = aVar.f5319b;
        this.j = aVar.f5320c;
        this.i = aVar.f5321d;
        this.f5312c = aVar.f5322e ? 1 : 0;
        this.k = aVar.f5323f;
        this.l = aVar.f5324g;
        this.f5311b = u.b(this.f5317h);
        this.f5310a = u.b(this.j);
        this.f5313d = u.b(this.i);
        this.f5314e = u.b(a(this.l));
        this.f5315f = u.b(this.k);
    }

    /* synthetic */ t(a aVar, byte b2) {
        this(aVar);
    }

    private static String a(String[] strArr) {
        if (strArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                sb.append(str);
                sb.append(";");
            }
            return sb.toString();
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private static String[] a(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f5310a)) {
            this.j = u.c(this.f5310a);
        }
        return this.j;
    }

    public final void a(boolean z) {
        this.f5312c = z ? 1 : 0;
    }

    public final String b() {
        return this.f5316g;
    }

    public final String c() {
        if (TextUtils.isEmpty(this.f5317h) && !TextUtils.isEmpty(this.f5311b)) {
            this.f5317h = u.c(this.f5311b);
        }
        return this.f5317h;
    }

    public final String d() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f5315f)) {
            this.k = u.c(this.f5315f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public final boolean e() {
        return this.f5312c == 1;
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        try {
            if (this.j.equals(((t) obj).j) && this.f5316g.equals(((t) obj).f5316g)) {
                if (this.f5317h.equals(((t) obj).f5317h)) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public final String[] f() {
        String[] strArr = this.l;
        if ((strArr == null || strArr.length == 0) && !TextUtils.isEmpty(this.f5314e)) {
            this.l = a(u.c(this.f5314e));
        }
        return (String[]) this.l.clone();
    }
}