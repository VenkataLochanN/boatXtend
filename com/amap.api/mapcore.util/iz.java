package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: OfflineLocManager.java */
/* JADX INFO: loaded from: classes.dex */
public class iz {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f1423a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static boolean f1424b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static int f1425c = 20;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static int f1426d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static WeakReference<iv> f1427e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f1428f = 10;

    @Deprecated
    public static synchronized void a(int i, boolean z) {
        f1423a = i;
        f1424b = z;
    }

    /* JADX INFO: compiled from: OfflineLocManager.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f1429a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f1430b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private iy f1431c;

        a(Context context, int i) {
            this.f1430b = context;
            this.f1429a = i;
        }

        a(Context context, int i, iy iyVar) {
            this(context, i);
            this.f1431c = iyVar;
        }

        private void a() {
            try {
                synchronized (iz.class) {
                    String string = Long.toString(System.currentTimeMillis());
                    iv ivVarA = jc.a(iz.f1427e);
                    jc.a(this.f1430b, ivVarA, hl.i, iz.f1423a, 2097152, "6");
                    if (ivVarA.f1413e == null) {
                        ivVarA.f1413e = new ic(new ie(new Cif(new ie())));
                    }
                    iw.a(string, this.f1431c.a(), ivVarA);
                }
            } catch (Throwable th) {
                hn.c(th, "ofm", "aple");
            }
        }

        private void b() {
            try {
                iv ivVarA = jc.a(iz.f1427e);
                jc.a(this.f1430b, ivVarA, hl.i, iz.f1423a, 2097152, "6");
                ivVarA.f1416h = 14400000;
                if (ivVarA.f1415g == null) {
                    ivVarA.f1415g = new jg(new jf(this.f1430b, new jk(), new ic(new ie(new Cif())), new String(hj.a(10)), gi.f(this.f1430b), gm.y(this.f1430b), gm.n(this.f1430b), gm.i(this.f1430b), gm.a(), Build.MANUFACTURER, Build.DEVICE, gm.A(this.f1430b), gi.c(this.f1430b), Build.MODEL, gi.d(this.f1430b), gi.b(this.f1430b)));
                }
                if (TextUtils.isEmpty(ivVarA.i)) {
                    ivVarA.i = "fKey";
                }
                ivVarA.f1414f = new jo(this.f1430b, ivVarA.f1416h, ivVarA.i, new jm(this.f1430b, iz.f1424b, iz.f1428f * 1024, iz.f1425c * 1024, "offLocKey", iz.f1426d * 1024));
                iw.a(ivVarA);
            } catch (Throwable th) {
                hn.c(th, "ofm", "uold");
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            int i = this.f1429a;
            if (i == 1) {
                a();
            } else if (i == 2) {
                b();
            }
        }
    }

    public static synchronized void a(iy iyVar, Context context) {
        hn.d().submit(new a(context, 1, iyVar));
    }

    public static void a(Context context) {
        hn.d().submit(new a(context, 2));
    }
}