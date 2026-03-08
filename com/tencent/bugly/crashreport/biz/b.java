package com.tencent.bugly.crashreport.biz;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.biz.a.AnonymousClass2;
import com.tencent.bugly.crashreport.biz.a.RunnableC0135a;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static com.tencent.bugly.crashreport.biz.a f5400a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f5401b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static int f5402c = 10;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static long f5403d = 300000;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static long f5404e = 30000;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static long f5405f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f5406g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static long f5407h = 0;
    private static long i = 0;
    private static long j = 0;
    private static Application.ActivityLifecycleCallbacks k = null;
    private static Class<?> l = null;
    private static boolean m = true;

    static /* synthetic */ String a(String str, String str2) {
        return ab.a() + "  " + str + "  " + str2 + IOUtils.LINE_SEPARATOR_UNIX;
    }

    static /* synthetic */ int g() {
        int i2 = f5406g;
        f5406g = i2 + 1;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void c(android.content.Context r11, com.tencent.bugly.BuglyStrategy r12) {
        /*
            Method dump skipped, instruction units count: 219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.biz.b.c(android.content.Context, com.tencent.bugly.BuglyStrategy):void");
    }

    private static boolean b(Context context) {
        try {
            int iMyPid = Process.myPid();
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid && runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
        } catch (Throwable th) {
            y.b(th);
        }
        return false;
    }

    public static void a(final Context context, final BuglyStrategy buglyStrategy) {
        long appReportDelay;
        if (f5401b) {
            return;
        }
        m = com.tencent.bugly.crashreport.common.info.a.a(context).f5418e;
        f5400a = new com.tencent.bugly.crashreport.biz.a(context, m);
        f5401b = true;
        if (buglyStrategy != null) {
            l = buglyStrategy.getUserInfoActivity();
            appReportDelay = buglyStrategy.getAppReportDelay();
        } else {
            appReportDelay = 0;
        }
        if (appReportDelay <= 0) {
            c(context, buglyStrategy);
        } else {
            x.a().a(new Runnable() { // from class: com.tencent.bugly.crashreport.biz.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    b.c(context, buglyStrategy);
                }
            }, appReportDelay);
        }
    }

    public static void a(long j2) {
        if (j2 < 0) {
            j2 = com.tencent.bugly.crashreport.common.strategy.a.a().c().o;
        }
        f5405f = j2;
    }

    public static void a(StrategyBean strategyBean, boolean z) {
        x xVarA;
        com.tencent.bugly.crashreport.biz.a aVar = f5400a;
        if (aVar != null && !z && (xVarA = x.a()) != null) {
            xVarA.a(aVar.new AnonymousClass2());
        }
        if (strategyBean == null) {
            return;
        }
        if (strategyBean.o > 0) {
            f5404e = strategyBean.o;
        }
        if (strategyBean.t > 0) {
            f5402c = strategyBean.t;
        }
        if (strategyBean.u > 0) {
            f5403d = strategyBean.u;
        }
    }

    public static void a() {
        com.tencent.bugly.crashreport.biz.a aVar = f5400a;
        if (aVar != null) {
            aVar.a(2, false, 0L);
        }
    }

    public static void a(Context context) {
        if (!f5401b || context == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 14) {
            Application application = context.getApplicationContext() instanceof Application ? (Application) context.getApplicationContext() : null;
            if (application != null) {
                try {
                    if (k != null) {
                        application.unregisterActivityLifecycleCallbacks(k);
                    }
                } catch (Exception e2) {
                    if (!y.a(e2)) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        f5401b = false;
    }

    /* JADX INFO: compiled from: BUGLY */
    static class a implements Application.ActivityLifecycleCallbacks {
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            y.c(">>> %s onStop <<<", activity.getClass().getName());
            com.tencent.bugly.crashreport.common.info.a.b().a(activity.hashCode(), false);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            y.c(">>> %s onStart <<<", activity.getClass().getName());
            com.tencent.bugly.crashreport.common.info.a.b().a(activity.hashCode(), true);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            String name = activity.getClass().getName();
            if (b.l == null || b.l.getName().equals(name)) {
                y.c(">>> %s onResumed <<<", name);
                com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                if (aVarB == null) {
                    return;
                }
                aVarB.B.add(b.a(name, "onResumed"));
                aVarB.o = name;
                aVarB.p = System.currentTimeMillis();
                aVarB.s = aVarB.p - b.i;
                long j = aVarB.p - b.f5407h;
                if (j > (b.f5405f > 0 ? b.f5405f : b.f5404e)) {
                    aVarB.d();
                    b.g();
                    y.a("[session] launch app one times (app in background %d seconds and over %d seconds)", Long.valueOf(j / 1000), Long.valueOf(b.f5404e / 1000));
                    if (b.f5406g % b.f5402c == 0) {
                        b.f5400a.a(4, b.m, 0L);
                        return;
                    }
                    b.f5400a.a(4, false, 0L);
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - b.j > b.f5403d) {
                        long unused = b.j = jCurrentTimeMillis;
                        y.a("add a timer to upload hot start user info", new Object[0]);
                        if (b.m) {
                            x.a().a(b.f5400a.new RunnableC0135a(null, true), b.f5403d);
                        }
                    }
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            String name = activity.getClass().getName();
            if (b.l == null || b.l.getName().equals(name)) {
                y.c(">>> %s onPaused <<<", name);
                com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                if (aVarB == null) {
                    return;
                }
                aVarB.B.add(b.a(name, "onPaused"));
                aVarB.q = System.currentTimeMillis();
                aVarB.r = aVarB.q - aVarB.p;
                long unused = b.f5407h = aVarB.q;
                if (aVarB.r < 0) {
                    aVarB.r = 0L;
                }
                aVarB.o = "background";
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            String name = activity.getClass().getName();
            if (b.l == null || b.l.getName().equals(name)) {
                y.c(">>> %s onDestroyed <<<", name);
                com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                if (aVarB != null) {
                    aVarB.B.add(b.a(name, "onDestroyed"));
                }
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            String name = activity.getClass().getName();
            if (b.l == null || b.l.getName().equals(name)) {
                y.c(">>> %s onCreated <<<", name);
                com.tencent.bugly.crashreport.common.info.a aVarB = com.tencent.bugly.crashreport.common.info.a.b();
                if (aVarB != null) {
                    aVarB.B.add(b.a(name, "onCreated"));
                }
            }
        }
    }
}