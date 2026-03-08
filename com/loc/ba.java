package com.loc;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: OfflineLocManager.java */
/* JADX INFO: loaded from: classes3.dex */
public class ba {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static int f4826a = 1000;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static boolean f4827b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static int f4828c = 20;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    static int f4829d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static WeakReference<ax> f4830e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f4831f = 10;

    /* JADX INFO: compiled from: OfflineLocManager.java */
    static class a implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f4832a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private Context f4833b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private az f4834c;

        a(Context context, int i) {
            this.f4833b = context;
            this.f4832a = i;
        }

        a(Context context, az azVar) {
            this(context, 1);
            this.f4834c = azVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            int i = this.f4832a;
            if (i == 1) {
                try {
                    synchronized (ba.class) {
                        String string = Long.toString(System.currentTimeMillis());
                        ax axVarA = bd.a(ba.f4830e);
                        bd.a(this.f4833b, axVarA, z.i, ba.f4826a, 2097152, "6");
                        if (axVarA.f4818e == null) {
                            axVarA.f4818e = new aj(new al(new an(new al())));
                        }
                        ay.a(string, this.f4834c.a(), axVarA);
                    }
                    return;
                } catch (Throwable th) {
                    ab.b(th, "ofm", "aple");
                    return;
                }
            }
            if (i == 2) {
                try {
                    ax axVarA2 = bd.a(ba.f4830e);
                    bd.a(this.f4833b, axVarA2, z.i, ba.f4826a, 2097152, "6");
                    axVarA2.f4821h = 14400000;
                    if (axVarA2.f4820g == null) {
                        axVarA2.f4820g = new bh(new bg(this.f4833b, new bl(), new aj(new al(new an())), new String(v.a(10)), k.f(this.f4833b), n.x(this.f4833b), n.m(this.f4833b), n.h(this.f4833b), n.a(), Build.MANUFACTURER, Build.DEVICE, n.A(this.f4833b), k.c(this.f4833b), Build.MODEL, k.d(this.f4833b), k.b(this.f4833b)));
                    }
                    if (TextUtils.isEmpty(axVarA2.i)) {
                        axVarA2.i = "fKey";
                    }
                    axVarA2.f4819f = new bp(this.f4833b, axVarA2.f4821h, axVarA2.i, new bn(this.f4833b, ba.f4827b, ba.f4831f * 1024, ba.f4828c * 1024, "offLocKey", ba.f4829d * 1024));
                    ay.a(axVarA2);
                } catch (Throwable th2) {
                    ab.b(th2, "ofm", "uold");
                }
            }
        }
    }

    public static synchronized void a(int i, boolean z, int i2, int i3) {
        f4826a = i;
        f4827b = z;
        if (i2 < 10 || i2 > 100) {
            i2 = 20;
        }
        f4828c = i2;
        if (i2 / 5 > f4831f) {
            f4831f = f4828c / 5;
        }
        f4829d = i3;
    }

    public static void a(Context context) {
        ab.d().submit(new a(context, 2));
    }

    public static synchronized void a(az azVar, Context context) {
        ab.d().submit(new a(context, azVar));
    }
}