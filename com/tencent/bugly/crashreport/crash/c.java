package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.n;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.q;
import com.tencent.bugly.proguard.t;
import com.tencent.bugly.proguard.v;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5511a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static boolean f5512b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f5513c = 2;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5514d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f5515e = 20480;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f5516f = 20480;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static long f5517g = 604800000;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static String f5518h = null;
    public static boolean i = false;
    public static String j = null;
    public static int k = 5000;
    public static boolean l = true;
    public static boolean m = false;
    public static String n;
    public static String o;
    private static c r;
    public final b p;
    private final Context q;
    private final e s;
    private final NativeCrashHandler t;
    private com.tencent.bugly.crashreport.common.strategy.a u;
    private x v;
    private final com.tencent.bugly.crashreport.crash.anr.b w;
    private Boolean x;
    private int y = 31;
    private boolean z = false;

    private c(int i2, Context context, x xVar, boolean z, BuglyStrategy.a aVar, n nVar, String str) {
        f5511a = i2;
        Context contextA = ab.a(context);
        this.q = contextA;
        this.u = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.v = xVar;
        v vVarA = v.a();
        o oVarA = o.a();
        this.p = new b(i2, contextA, vVarA, oVarA, this.u, aVar, nVar);
        com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(contextA);
        this.s = new e(contextA, this.p, this.u, aVarA);
        this.t = NativeCrashHandler.getInstance(contextA, aVarA, this.p, this.u, xVar, z, str);
        aVarA.D = this.t;
        this.w = com.tencent.bugly.crashreport.crash.anr.b.a(contextA, this.u, aVarA, xVar, oVarA, this.p, aVar);
    }

    public static synchronized c a(int i2, Context context, boolean z, BuglyStrategy.a aVar, n nVar, String str) {
        if (r == null) {
            r = new c(1004, context, x.a(), z, aVar, null, null);
        }
        return r;
    }

    public static synchronized c a() {
        return r;
    }

    public final void a(StrategyBean strategyBean) {
        this.s.a(strategyBean);
        this.t.onStrategyChanged(strategyBean);
        this.w.b();
    }

    public final boolean b() {
        Boolean bool = this.x;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.crashreport.common.info.a.b().f5417d;
        List<q> listA = o.a().a(1);
        ArrayList arrayList = new ArrayList();
        if (listA != null && listA.size() > 0) {
            for (q qVar : listA) {
                if (str.equals(qVar.f5731c)) {
                    this.x = true;
                    arrayList.add(qVar);
                }
            }
            if (arrayList.size() > 0) {
                o.a().a(arrayList);
            }
            return true;
        }
        this.x = false;
        return false;
    }

    public final synchronized void c() {
        this.s.a();
        this.t.setUserOpened(true);
        this.w.a(true);
    }

    public final synchronized void d() {
        this.s.b();
        this.t.setUserOpened(false);
        this.w.a(false);
    }

    public final void e() {
        this.s.b();
    }

    public final void f() {
        this.s.a();
    }

    public final void g() {
        this.t.setUserOpened(false);
    }

    public final void h() {
        this.t.setUserOpened(true);
    }

    public final void i() {
        this.w.a(true);
    }

    public final void j() {
        this.w.a(false);
    }

    public final void k() {
        this.t.enableCatchAnrTrace();
    }

    public final synchronized void a(boolean z, boolean z2, boolean z3) {
        this.t.testNativeCrash(z, z2, z3);
    }

    public final synchronized void l() {
        com.tencent.bugly.crashreport.crash.anr.b bVar = this.w;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 < 30) {
                try {
                    y.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i3));
                    ab.b(BootloaderScanner.TIMEOUT);
                    i2 = i3;
                } catch (Throwable th) {
                    if (y.a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            }
        }
    }

    public final boolean m() {
        return this.w.a();
    }

    public final void a(final Thread thread, final Throwable th, boolean z, String str, byte[] bArr, final boolean z2, boolean z3) {
        final boolean z4 = false;
        final String str2 = null;
        final byte[] bArr2 = null;
        final boolean z5 = true;
        this.v.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    y.c("post a throwable %b", Boolean.valueOf(z4));
                    c.this.s.a(thread, th, false, str2, bArr2, z5);
                    if (z2) {
                        y.a("clear user datas", new Object[0]);
                        com.tencent.bugly.crashreport.common.info.a.a(c.this.q).y();
                    }
                } catch (Throwable th2) {
                    if (!y.b(th2)) {
                        th2.printStackTrace();
                    }
                    y.e("java catch error: %s", th.toString());
                }
            }
        });
    }

    public final void a(CrashDetailBean crashDetailBean) {
        this.p.e(crashDetailBean);
    }

    public final void a(long j2) {
        x.a().a(new Thread() { // from class: com.tencent.bugly.crashreport.crash.c.2
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                List<CrashDetailBean> list;
                if (!ab.a(c.this.q, "local_crash_lock", DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT)) {
                    y.c("Failed to lock file for uploading local crash.", new Object[0]);
                    return;
                }
                t.a().b();
                List<CrashDetailBean> listA = c.this.p.a();
                if (listA != null && listA.size() > 0) {
                    y.c("Size of crash list: %s", Integer.valueOf(listA.size()));
                    int size = listA.size();
                    if (size > 20) {
                        ArrayList arrayList = new ArrayList();
                        Collections.sort(listA);
                        for (int i2 = 0; i2 < 20; i2++) {
                            arrayList.add(listA.get((size - 1) - i2));
                        }
                        list = arrayList;
                    } else {
                        list = listA;
                    }
                    c.this.p.a(list, 0L, false, false, false);
                } else {
                    y.c("no crash need to be uploaded at this start", new Object[0]);
                }
                ab.b(c.this.q, "local_crash_lock");
            }
        }, j2);
    }

    public final void n() {
        this.t.checkUploadRecordCrash();
    }

    public final void o() {
        if (com.tencent.bugly.crashreport.common.info.a.b().f5417d.equals(AppInfo.a(this.q))) {
            this.t.removeEmptyNativeRecordFiles();
        }
    }

    public final void a(int i2) {
        this.y = i2;
    }

    public final void a(boolean z) {
        this.z = z;
    }

    public final boolean p() {
        return this.z;
    }

    public final boolean q() {
        return (this.y & 16) > 0;
    }

    public final boolean r() {
        return (this.y & 8) > 0;
    }

    public final boolean s() {
        return (this.y & 4) > 0;
    }

    public final boolean t() {
        return (this.y & 2) > 0;
    }

    public final boolean u() {
        return (this.y & 1) > 0;
    }
}