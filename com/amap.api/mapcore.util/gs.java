package com.amap.api.mapcore.util;

import android.text.TextUtils;

/* JADX INFO: compiled from: SDKInfo.java */
/* JADX INFO: loaded from: classes.dex */
@hs(a = "a")
public class gs {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @ht(a = "a1", b = 6)
    private String f1124a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @ht(a = "a2", b = 6)
    private String f1125b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @ht(a = "a6", b = 2)
    private int f1126c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @ht(a = "a3", b = 6)
    private String f1127d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @ht(a = "a4", b = 6)
    private String f1128e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @ht(a = "a5", b = 6)
    private String f1129f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f1130g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f1131h;
    private String i;
    private String j;
    private String k;
    private String[] l;

    private gs() {
        this.f1126c = 1;
        this.l = null;
    }

    private gs(a aVar) {
        this.f1126c = 1;
        this.l = null;
        this.f1130g = aVar.f1132a;
        this.f1131h = aVar.f1133b;
        this.j = aVar.f1134c;
        this.i = aVar.f1135d;
        this.f1126c = aVar.f1136e ? 1 : 0;
        this.k = aVar.f1137f;
        this.l = aVar.f1138g;
        this.f1125b = gt.b(this.f1131h);
        this.f1124a = gt.b(this.j);
        this.f1127d = gt.b(this.i);
        this.f1128e = gt.b(a(this.l));
        this.f1129f = gt.b(this.k);
    }

    /* JADX INFO: compiled from: SDKInfo.java */
    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f1132a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f1133b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f1134c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f1135d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f1136e = true;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f1137f = "standard";

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String[] f1138g = null;

        public a(String str, String str2, String str3) {
            this.f1132a = str2;
            this.f1133b = str2;
            this.f1135d = str3;
            this.f1134c = str;
        }

        public a a(String[] strArr) {
            if (strArr != null) {
                this.f1138g = (String[]) strArr.clone();
            }
            return this;
        }

        public a a(String str) {
            this.f1133b = str;
            return this;
        }

        public gs a() throws gh {
            if (this.f1138g == null) {
                throw new gh("sdk packages is null");
            }
            return new gs(this);
        }
    }

    public void a(boolean z) {
        this.f1126c = z ? 1 : 0;
    }

    public String a() {
        if (TextUtils.isEmpty(this.j) && !TextUtils.isEmpty(this.f1124a)) {
            this.j = gt.c(this.f1124a);
        }
        return this.j;
    }

    public String b() {
        return this.f1130g;
    }

    public String c() {
        if (TextUtils.isEmpty(this.f1131h) && !TextUtils.isEmpty(this.f1125b)) {
            this.f1131h = gt.c(this.f1125b);
        }
        return this.f1131h;
    }

    public String d() {
        if (TextUtils.isEmpty(this.i) && !TextUtils.isEmpty(this.f1127d)) {
            this.i = gt.c(this.f1127d);
        }
        return this.i;
    }

    public String e() {
        if (TextUtils.isEmpty(this.k) && !TextUtils.isEmpty(this.f1129f)) {
            this.k = gt.c(this.f1129f);
        }
        if (TextUtils.isEmpty(this.k)) {
            this.k = "standard";
        }
        return this.k;
    }

    public boolean f() {
        return this.f1126c == 1;
    }

    public String[] g() {
        String[] strArr = this.l;
        if ((strArr == null || strArr.length == 0) && !TextUtils.isEmpty(this.f1128e)) {
            this.l = a(gt.c(this.f1128e));
        }
        return (String[]) this.l.clone();
    }

    private String[] a(String str) {
        try {
            return str.split(";");
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private String a(String[] strArr) {
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

    public boolean equals(Object obj) {
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
            if (this.j.equals(((gs) obj).j) && this.f1130g.equals(((gs) obj).f1130g)) {
                return this.f1131h.equals(((gs) obj).f1131h);
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }
}