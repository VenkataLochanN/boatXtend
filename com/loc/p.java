package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: compiled from: HttpsDecisionUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private volatile b f5303a = new b(0);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private x f5304b = new x("HttpsDecisionUtil");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static p f5305a = new p();
    }

    /* JADX INFO: compiled from: HttpsDecisionUtil.java */
    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected boolean f5306a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f5307b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final boolean f5308c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f5309d;

        private b() {
            this.f5307b = 0;
            this.f5306a = true;
            this.f5308c = true;
            this.f5309d = false;
        }

        /* synthetic */ b(byte b2) {
            this();
        }

        public final void a(Context context) {
            if (context != null && this.f5307b <= 0 && Build.VERSION.SDK_INT >= 4) {
                this.f5307b = context.getApplicationContext().getApplicationInfo().targetSdkVersion;
            }
        }

        public final void a(boolean z) {
            this.f5306a = z;
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0022  */
        /* JADX WARN: Removed duplicated region for block: B:23:0x0029  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x002d A[RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean a() {
            /*
                r5 = this;
                boolean r0 = r5.f5309d
                r1 = 1
                if (r0 != 0) goto L2e
                int r0 = android.os.Build.VERSION.SDK_INT
                r2 = 28
                r3 = 0
                if (r0 < r2) goto Le
                r0 = r1
                goto Lf
            Le:
                r0 = r3
            Lf:
                boolean r4 = r5.f5306a
                if (r4 == 0) goto L22
                int r4 = r5.f5307b
                if (r4 > 0) goto L18
                r4 = r2
            L18:
                if (r4 < r2) goto L1c
                r2 = r1
                goto L1d
            L1c:
                r2 = r3
            L1d:
                if (r2 == 0) goto L20
                goto L22
            L20:
                r2 = r3
                goto L23
            L22:
                r2 = r1
            L23:
                if (r0 == 0) goto L29
                if (r2 == 0) goto L29
                r0 = r1
                goto L2a
            L29:
                r0 = r3
            L2a:
                if (r0 == 0) goto L2d
                goto L2e
            L2d:
                return r3
            L2e:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.p.b.a():boolean");
        }

        public final void b(boolean z) {
            this.f5309d = z;
        }
    }

    public static p a() {
        return a.f5305a;
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

    public static void b(Context context) {
        b(context, true);
    }

    private static void b(Context context, boolean z) {
        SharedPreferences.Editor editorB = x.b(context, "open_common");
        x.a(editorB, "a3", z);
        x.a(editorB);
    }

    public static boolean b() {
        return Build.VERSION.SDK_INT == 19;
    }

    public final void a(Context context) {
        if (this.f5303a == null) {
            this.f5303a = new b((byte) 0);
        }
        this.f5303a.a(x.a(context, "open_common", "a3", true));
        this.f5303a.a(context);
    }

    final void a(Context context, boolean z) {
        if (this.f5303a == null) {
            this.f5303a = new b((byte) 0);
        }
        b(context, z);
        this.f5303a.a(z);
    }

    public final void a(boolean z) {
        if (this.f5303a == null) {
            this.f5303a = new b((byte) 0);
        }
        this.f5303a.b(z);
    }

    public final boolean b(boolean z) {
        byte b2 = 0;
        if (b()) {
            return false;
        }
        if (z) {
            return true;
        }
        if (this.f5303a == null) {
            this.f5303a = new b(b2);
        }
        return this.f5303a.a();
    }
}