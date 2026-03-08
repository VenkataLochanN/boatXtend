package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static d f5528a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.tencent.bugly.crashreport.common.strategy.a f5529b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private com.tencent.bugly.crashreport.common.info.a f5530c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private b f5531d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f5532e;

    static /* synthetic */ void a(d dVar) {
        y.c("[ExtraCrashManager] Trying to notify Bugly agents.", new Object[0]);
        try {
            Class<?> cls = Class.forName("com.tencent.bugly.agent.GameAgent");
            dVar.f5530c.getClass();
            ab.a(cls, "sdkPackageName", "com.tencent.bugly", null);
            y.c("[ExtraCrashManager] Bugly game agent has been notified.", new Object[0]);
        } catch (Throwable unused) {
            y.a("[ExtraCrashManager] no game agent", new Object[0]);
        }
    }

    static /* synthetic */ void a(d dVar, Thread thread, int i, String str, String str2, String str3, Map map) {
        String str4;
        String str5;
        String str6;
        Thread threadCurrentThread = thread == null ? Thread.currentThread() : thread;
        if (i == 4) {
            str4 = "Unity";
        } else if (i == 5 || i == 6) {
            str4 = "Cocos";
        } else {
            if (i != 8) {
                y.d("[ExtraCrashManager] Unknown extra crash type: %d", Integer.valueOf(i));
                return;
            }
            str4 = "H5";
        }
        y.e("[ExtraCrashManager] %s Crash Happen", str4);
        try {
            if (!dVar.f5529b.b()) {
                y.d("[ExtraCrashManager] There is no remote strategy, but still store it.", new Object[0]);
            }
            StrategyBean strategyBeanC = dVar.f5529b.c();
            if (!strategyBeanC.f5429e && dVar.f5529b.b()) {
                y.e("[ExtraCrashManager] Crash report was closed by remote. Will not upload to Bugly , print local for helpful!", new Object[0]);
                b.a(str4, ab.a(), dVar.f5530c.f5417d, threadCurrentThread.getName(), str + IOUtils.LINE_SEPARATOR_UNIX + str2 + IOUtils.LINE_SEPARATOR_UNIX + str3, (CrashDetailBean) null);
                y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            if (i == 5 || i == 6) {
                if (!strategyBeanC.j) {
                    y.e("[ExtraCrashManager] %s report is disabled.", str4);
                    y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                    return;
                }
            } else if (i == 8 && !strategyBeanC.k) {
                y.e("[ExtraCrashManager] %s report is disabled.", str4);
                y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                return;
            }
            int i2 = i != 8 ? i : 5;
            CrashDetailBean crashDetailBean = new CrashDetailBean();
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.l();
            crashDetailBean.F = dVar.f5530c.o();
            crashDetailBean.G = dVar.f5530c.n();
            crashDetailBean.H = dVar.f5530c.p();
            crashDetailBean.I = com.tencent.bugly.crashreport.common.info.b.f();
            crashDetailBean.J = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.K = com.tencent.bugly.crashreport.common.info.b.h();
            Context context = dVar.f5532e;
            crashDetailBean.w = ab.a(c.f5515e, (String) null);
            crashDetailBean.f5450b = i2;
            crashDetailBean.f5453e = dVar.f5530c.k();
            crashDetailBean.f5454f = dVar.f5530c.i;
            crashDetailBean.f5455g = dVar.f5530c.u();
            crashDetailBean.m = dVar.f5530c.g();
            crashDetailBean.n = str;
            crashDetailBean.o = str2;
            str5 = "";
            if (str3 != null) {
                String[] strArrSplit = str3.split(IOUtils.LINE_SEPARATOR_UNIX);
                str5 = strArrSplit.length > 0 ? strArrSplit[0] : "";
                str6 = str3;
            } else {
                str6 = "";
            }
            crashDetailBean.p = str5;
            crashDetailBean.q = str6;
            crashDetailBean.r = System.currentTimeMillis();
            crashDetailBean.u = ab.a(crashDetailBean.q.getBytes());
            crashDetailBean.z = ab.a(dVar.f5530c.h(), c.f5516f, false);
            crashDetailBean.A = dVar.f5530c.f5417d;
            crashDetailBean.B = threadCurrentThread.getName() + "(" + threadCurrentThread.getId() + ")";
            crashDetailBean.L = dVar.f5530c.w();
            crashDetailBean.f5456h = dVar.f5530c.t();
            crashDetailBean.P = dVar.f5530c.f5414a;
            crashDetailBean.Q = dVar.f5530c.a();
            if (!c.a().p()) {
                dVar.f5531d.d(crashDetailBean);
            }
            crashDetailBean.T = dVar.f5530c.D();
            crashDetailBean.U = dVar.f5530c.E();
            crashDetailBean.V = dVar.f5530c.x();
            crashDetailBean.W = dVar.f5530c.C();
            crashDetailBean.y = aa.a();
            if (crashDetailBean.R == null) {
                crashDetailBean.R = new LinkedHashMap();
            }
            if (map != null) {
                crashDetailBean.R.putAll(map);
            }
            b.a(str4, ab.a(), dVar.f5530c.f5417d, threadCurrentThread.getName(), str + IOUtils.LINE_SEPARATOR_UNIX + str2 + IOUtils.LINE_SEPARATOR_UNIX + str3, crashDetailBean);
            if (!dVar.f5531d.a(crashDetailBean)) {
                dVar.f5531d.a(crashDetailBean, 3000L, false);
            }
            y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
            } catch (Throwable th2) {
                y.e("[ExtraCrashManager] Successfully handled.", new Object[0]);
                throw th2;
            }
        }
    }

    private d(Context context) {
        c cVarA = c.a();
        if (cVarA == null) {
            return;
        }
        this.f5529b = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.f5530c = com.tencent.bugly.crashreport.common.info.a.a(context);
        this.f5531d = cVarA.p;
        this.f5532e = context;
        x.a().a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.1
            @Override // java.lang.Runnable
            public final void run() {
                d.a(d.this);
            }
        });
    }

    public static d a(Context context) {
        if (f5528a == null) {
            f5528a = new d(context);
        }
        return f5528a;
    }

    public static void a(final Thread thread, final int i, final String str, final String str2, final String str3, final Map<String, String> map) {
        x.a().a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.d.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    if (d.f5528a != null) {
                        d.a(d.f5528a, thread, i, str, str2, str3, map);
                    } else {
                        y.e("[ExtraCrashManager] Extra crash manager has not been initialized.", new Object[0]);
                    }
                } catch (Throwable th) {
                    if (!y.b(th)) {
                        th.printStackTrace();
                    }
                    y.e("[ExtraCrashManager] Crash error %s %s %s", str, str2, str3);
                }
            }
        });
    }
}