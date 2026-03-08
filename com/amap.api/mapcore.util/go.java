package com.amap.api.mapcore.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: compiled from: HttpsDecisionUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class go {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile b f1117a = new b();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private hp f1118b = new hp("HttpsDecisionUtil");

    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static go f1119a = new go();
    }

    public static go a() {
        return a.f1119a;
    }

    public void a(Context context) {
        if (this.f1117a == null) {
            this.f1117a = new b();
        }
        this.f1117a.a(c(context));
        this.f1117a.a(context);
    }

    public void b(Context context) {
        d(context);
    }

    public void a(boolean z) {
        if (this.f1117a == null) {
            this.f1117a = new b();
        }
        this.f1117a.b(z);
    }

    void a(Context context, boolean z) {
        if (this.f1117a == null) {
            this.f1117a = new b();
        }
        b(context, z);
        this.f1117a.a(z);
    }

    public boolean b() {
        if (this.f1117a == null) {
            this.f1117a = new b();
        }
        return this.f1117a.a();
    }

    private void b(Context context, boolean z) {
        SharedPreferences.Editor editorB = hp.b(context, "open_common");
        hp.a(editorB, "a3", z);
        hp.a(editorB);
    }

    private boolean c(Context context) {
        return hp.a(context, "open_common", "a3", true);
    }

    private void d(Context context) {
        b(context, true);
    }

    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected boolean f1120a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f1121b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f1122c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f1123d;

        private b() {
            this.f1121b = 0;
            this.f1120a = true;
            this.f1122c = true;
            this.f1123d = false;
        }

        public void a(Context context) {
            if (context != null && this.f1121b <= 0 && Build.VERSION.SDK_INT >= 4) {
                this.f1121b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public void a(boolean z) {
            this.f1120a = z;
        }

        public void b(boolean z) {
            this.f1123d = z;
        }

        private int b() {
            int i = this.f1121b;
            if (i <= 0) {
                return 28;
            }
            return i;
        }

        private boolean c() {
            return b() >= 28;
        }

        private boolean d() {
            return Build.VERSION.SDK_INT >= 28;
        }

        private boolean e() {
            return d() && (!this.f1120a || c());
        }

        public boolean a() {
            return this.f1123d || e();
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("https")) {
            return str;
        }
        try {
            Uri.Builder builderBuildUpon = Uri.parse(str).buildUpon();
            builderBuildUpon.scheme("https");
            return builderBuildUpon.build().toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT == 19;
    }

    public boolean b(boolean z) {
        if (c()) {
            return false;
        }
        return z || b();
    }
}