package com.tencent.bugly.crashreport.crash.anr;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.info.AppInfo;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.anr.TraceFileHelper;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.ad;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import com.tencent.bugly.proguard.z;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class b {
    private static b m;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final Context f5480b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final ActivityManager f5481c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.info.a f5482d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final x f5483e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.crash.b f5484f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f5486h;
    private FileObserver i;
    private ad k;
    private int l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AtomicBoolean f5479a = new AtomicBoolean(false);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final Object f5485g = new Object();
    private boolean j = true;
    private long n = 0;

    static /* synthetic */ void a(b bVar, String str) {
        if (bVar.b(true)) {
            try {
                y.c("read trace first dump for create time!", new Object[0]);
                TraceFileHelper.a firstDumpInfo = TraceFileHelper.readFirstDumpInfo(str, false);
                long jCurrentTimeMillis = firstDumpInfo != null ? firstDumpInfo.f5470c : -1L;
                if (jCurrentTimeMillis == -1) {
                    y.d("trace dump fail could not get time!", new Object[0]);
                    jCurrentTimeMillis = System.currentTimeMillis();
                }
                if (bVar.a(jCurrentTimeMillis)) {
                    return;
                }
                bVar.a(jCurrentTimeMillis, str);
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                y.e("handle anr error %s", th.getClass().toString());
            }
        }
    }

    static /* synthetic */ boolean a(b bVar, boolean z) {
        return bVar.b(true);
    }

    static /* synthetic */ boolean a(String str) {
        return str.startsWith("manual_bugly_trace_") && str.endsWith(".txt");
    }

    static /* synthetic */ void b(b bVar) {
        long jCurrentTimeMillis = (com.tencent.bugly.crashreport.crash.c.f5517g + System.currentTimeMillis()) - ab.b();
        z.a(bVar.f5486h, "bugly_trace_", ".txt", jCurrentTimeMillis);
        z.a(bVar.f5486h, "manual_bugly_trace_", ".txt", jCurrentTimeMillis);
        z.a(bVar.f5486h, "main_stack_record_", ".txt", jCurrentTimeMillis);
        z.a(bVar.f5486h, "main_stack_record_", ".txt.merged", jCurrentTimeMillis);
    }

    public static b a(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, com.tencent.bugly.crashreport.common.info.a aVar2, x xVar, o oVar, com.tencent.bugly.crashreport.crash.b bVar, BuglyStrategy.a aVar3) {
        if (m == null) {
            m = new b(context, aVar, aVar2, xVar, bVar);
        }
        return m;
    }

    private b(Context context, com.tencent.bugly.crashreport.common.strategy.a aVar, com.tencent.bugly.crashreport.common.info.a aVar2, x xVar, com.tencent.bugly.crashreport.crash.b bVar) {
        this.f5480b = ab.a(context);
        this.f5481c = (ActivityManager) this.f5480b.getSystemService("activity");
        this.f5486h = context.getDir("bugly", 0).getAbsolutePath();
        this.f5482d = aVar2;
        this.f5483e = xVar;
        this.f5484f = bVar;
    }

    private CrashDetailBean a(a aVar) {
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        try {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.l();
            crashDetailBean.F = this.f5482d.o();
            crashDetailBean.G = this.f5482d.n();
            crashDetailBean.H = this.f5482d.p();
            crashDetailBean.I = com.tencent.bugly.crashreport.common.info.b.f();
            crashDetailBean.J = com.tencent.bugly.crashreport.common.info.b.g();
            crashDetailBean.K = com.tencent.bugly.crashreport.common.info.b.h();
            Context context = this.f5480b;
            if (!com.tencent.bugly.crashreport.common.info.b.o()) {
                Context context2 = this.f5480b;
                crashDetailBean.w = ab.a(com.tencent.bugly.crashreport.crash.c.f5515e, (String) null);
            }
            crashDetailBean.f5450b = 3;
            crashDetailBean.f5453e = this.f5482d.k();
            crashDetailBean.f5454f = this.f5482d.i;
            crashDetailBean.f5455g = this.f5482d.u();
            crashDetailBean.m = this.f5482d.g();
            crashDetailBean.n = "ANR_EXCEPTION";
            crashDetailBean.o = aVar.f5477f;
            crashDetailBean.q = aVar.f5478g;
            crashDetailBean.S = new HashMap();
            crashDetailBean.S.put("BUGLY_CR_01", aVar.f5476e);
            int iIndexOf = crashDetailBean.q != null ? crashDetailBean.q.indexOf(IOUtils.LINE_SEPARATOR_UNIX) : -1;
            crashDetailBean.p = iIndexOf > 0 ? crashDetailBean.q.substring(0, iIndexOf) : "GET_FAIL";
            crashDetailBean.r = aVar.f5474c;
            if (crashDetailBean.q != null) {
                crashDetailBean.u = ab.a(crashDetailBean.q.getBytes());
            }
            crashDetailBean.z = aVar.f5473b;
            crashDetailBean.A = aVar.f5472a;
            crashDetailBean.B = "main(1)";
            crashDetailBean.L = this.f5482d.w();
            crashDetailBean.f5456h = this.f5482d.t();
            crashDetailBean.i = this.f5482d.F();
            crashDetailBean.v = aVar.f5475d;
            crashDetailBean.O = this.f5482d.m;
            crashDetailBean.P = this.f5482d.f5414a;
            crashDetailBean.Q = this.f5482d.a();
            Context context3 = this.f5480b;
            if (!com.tencent.bugly.crashreport.common.info.b.o()) {
                this.f5484f.d(crashDetailBean);
            }
            crashDetailBean.T = this.f5482d.D();
            crashDetailBean.U = this.f5482d.E();
            crashDetailBean.V = this.f5482d.x();
            crashDetailBean.W = this.f5482d.C();
            crashDetailBean.y = aa.a();
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
        }
        return crashDetailBean;
    }

    private static boolean a(String str, String str2, String str3) throws Throwable {
        TraceFileHelper.a targetDumpInfo = TraceFileHelper.readTargetDumpInfo(str3, str, true);
        if (targetDumpInfo == null || targetDumpInfo.f5471d == null || targetDumpInfo.f5471d.isEmpty()) {
            y.e("not found trace dump for %s", str3);
            return false;
        }
        StringBuilder sb = new StringBuilder(1024);
        String[] strArr = targetDumpInfo.f5471d.get("main");
        if (strArr != null && strArr.length >= 3) {
            sb.append("\"main\" tid=");
            sb.append(strArr[2]);
            sb.append(" :\n");
            sb.append(strArr[0]);
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            sb.append(strArr[1]);
            sb.append("\n\n");
        }
        for (Map.Entry<String, String[]> entry : targetDumpInfo.f5471d.entrySet()) {
            if (!entry.getKey().equals("main") && entry.getValue() != null && entry.getValue().length >= 3) {
                sb.append("\"");
                sb.append(entry.getKey());
                sb.append("\" tid=");
                sb.append(entry.getValue()[2]);
                sb.append(" :\n");
                sb.append(entry.getValue()[0]);
                sb.append(IOUtils.LINE_SEPARATOR_UNIX);
                sb.append(entry.getValue()[1]);
                sb.append("\n\n");
            }
        }
        return z.a(str2, sb.toString(), sb.length() * 2);
    }

    public final boolean a() {
        return this.f5479a.get();
    }

    private static String a(List<c> list, long j) {
        if (list == null || list.isEmpty()) {
            return "main thread stack not enable";
        }
        StringBuilder sb = new StringBuilder(4096);
        sb.append("\n>>>>> 以下为anr过程中主线程堆栈记录，可根据堆栈出现次数推测在该堆栈阻塞的时间，出现次数越多对anr贡献越大，越可能是造成anr的原因 >>>>>\n");
        sb.append("\n>>>>> Thread Stack Traces Records Start >>>>>\n");
        for (int i = 0; i < list.size(); i++) {
            c cVar = list.get(i);
            sb.append("Thread name:");
            sb.append(cVar.b());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            long jC = cVar.c() - j;
            String str = jC <= 0 ? "before " : "after ";
            sb.append("Got ");
            sb.append(str);
            sb.append("anr:");
            sb.append(Math.abs(jC));
            sb.append("ms\n");
            sb.append(cVar.a());
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
            if ((sb.length() << 1) >= 101376) {
                break;
            }
        }
        sb.append("\n<<<<< Thread Stack Traces Records End <<<<<\n");
        return sb.toString();
    }

    private boolean b(boolean z) {
        boolean zCompareAndSet = this.f5479a.compareAndSet(!z, z);
        y.c("tryChangeAnrState to %s, success:%s", Boolean.valueOf(z), Boolean.valueOf(zCompareAndSet));
        return zCompareAndSet;
    }

    private synchronized void c() {
        if (e()) {
            y.d("start when started!", new Object[0]);
            return;
        }
        this.i = new FileObserver("/data/anr/", 8) { // from class: com.tencent.bugly.crashreport.crash.anr.b.1
            @Override // android.os.FileObserver
            public final void onEvent(int i, String str) {
                if (str == null) {
                    return;
                }
                final String str2 = "/data/anr/" + str;
                y.d("watching file %s", str2);
                if (str2.contains("trace")) {
                    b.this.f5483e.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.1.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            b.a(b.this, str2);
                        }
                    });
                } else {
                    y.d("not anr file %s", str2);
                }
            }
        };
        try {
            this.i.startWatching();
            y.a("start anr monitor!", new Object[0]);
            this.f5483e.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.2
                @Override // java.lang.Runnable
                public final void run() {
                    b.b(b.this);
                }
            });
        } catch (Throwable th) {
            this.i = null;
            y.d("start anr monitor failed!", new Object[0]);
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized void d() {
        if (!e()) {
            y.d("close when closed!", new Object[0]);
            return;
        }
        try {
            this.i.stopWatching();
            this.i = null;
            y.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            y.d("stop anr monitor failed!", new Object[0]);
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    private synchronized boolean e() {
        return this.i != null;
    }

    private synchronized void c(boolean z) {
        if (Build.VERSION.SDK_INT <= 19) {
            if (z) {
                c();
                return;
            } else {
                d();
                return;
            }
        }
        if (z) {
            g();
        } else {
            h();
        }
    }

    private synchronized boolean f() {
        return this.j;
    }

    private synchronized void d(boolean z) {
        if (this.j != z) {
            y.a("user change anr %b", Boolean.valueOf(z));
            this.j = z;
        }
    }

    public final void a(boolean z) {
        d(z);
        boolean zF = f();
        com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (aVarA != null) {
            zF = zF && aVarA.c().f5429e;
        }
        if (zF != e()) {
            y.a("anr changed to %b", Boolean.valueOf(zF));
            c(zF);
        }
    }

    public final synchronized void b() {
        y.d("customer decides whether to open or close.", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j, String str) {
        ActivityManager.ProcessErrorStateInfo processErrorStateInfoA;
        List<c> listC;
        try {
            y.c("anr time:%s", Long.valueOf(j));
            synchronized (this.f5485g) {
                if (this.k != null) {
                    y.c("Disable record main stack trace.", new Object[0]);
                    this.k.d();
                }
            }
            String strA = ab.a(Looper.getMainLooper().getThread());
            Map<String, String> mapA = ab.a(this.f5482d.i(), com.tencent.bugly.crashreport.crash.c.f5516f, false);
            Context context = this.f5480b;
            boolean z = com.tencent.bugly.crashreport.common.info.b.d(context) || com.tencent.bugly.crashreport.common.info.b.e(context);
            y.c("isAnrCrashDevice:%s", Boolean.valueOf(z));
            if (z) {
                processErrorStateInfoA = com.tencent.bugly.proguard.a.a(this.f5481c, 0L);
            } else {
                processErrorStateInfoA = com.tencent.bugly.proguard.a.a(this.f5481c, 21000L);
            }
            if (processErrorStateInfoA == null) {
                y.c("proc state is invisible or not my proc!", new Object[0]);
                return;
            }
            a aVar = new a();
            aVar.f5474c = j;
            aVar.f5472a = processErrorStateInfoA != null ? processErrorStateInfoA.processName : AppInfo.a(Process.myPid());
            aVar.f5477f = processErrorStateInfoA != null ? processErrorStateInfoA.shortMsg : "";
            aVar.f5476e = processErrorStateInfoA != null ? processErrorStateInfoA.longMsg : "";
            aVar.f5473b = mapA;
            aVar.f5478g = strA;
            if (TextUtils.isEmpty(aVar.f5478g)) {
                aVar.f5478g = "main stack is null , some error may be encountered.";
            }
            Object[] objArr = new Object[7];
            objArr[0] = Long.valueOf(aVar.f5474c);
            objArr[1] = aVar.f5475d;
            objArr[2] = aVar.f5472a;
            objArr[3] = aVar.f5478g;
            objArr[4] = aVar.f5477f;
            objArr[5] = aVar.f5476e;
            objArr[6] = Integer.valueOf(aVar.f5473b == null ? 0 : aVar.f5473b.size());
            y.c("anr tm:%d\ntr:%s\nproc:%s\nmain stack:%s\nsMsg:%s\n lMsg:%s\n threads:%d", objArr);
            y.a("found visible anr , start to upload!", new Object[0]);
            y.c("trace file:%s", str);
            if (TextUtils.isEmpty(str) || !new File(str).exists()) {
                y.c("trace file is null or not exists, just ignore", new Object[0]);
            } else {
                File file = new File(this.f5486h, "bugly_trace_" + j + ".txt");
                y.c("trace file exists", new Object[0]);
                if (str.startsWith("/data/anr/")) {
                    y.a("backup trace isOK:%s", Boolean.valueOf(a(str, file.getAbsolutePath(), aVar.f5472a)));
                } else {
                    y.a("trace file rename :%s", Boolean.valueOf(new File(str).renameTo(file)));
                }
                synchronized (this.f5485g) {
                    listC = this.k != null ? this.k.c() : null;
                }
                if (listC != null) {
                    String strA2 = a(listC, j);
                    y.c("save main stack trace", new Object[0]);
                    z.a(file, strA2, 2147483647L, true);
                }
                aVar.f5475d = file.getAbsolutePath();
            }
            CrashDetailBean crashDetailBeanA = a(aVar);
            if (crashDetailBeanA == null) {
                y.e("pack anr fail!", new Object[0]);
            } else {
                com.tencent.bugly.crashreport.crash.c.a().a(crashDetailBeanA);
                if (crashDetailBeanA.f5449a >= 0) {
                    y.a("backup anr record success!", new Object[0]);
                } else {
                    y.d("backup anr record fail!", new Object[0]);
                }
                com.tencent.bugly.crashreport.crash.b.a("ANR", ab.a(j), aVar.f5472a, "main", aVar.f5478g, crashDetailBeanA);
                if (!this.f5484f.a(crashDetailBeanA)) {
                    this.f5484f.a(crashDetailBeanA, 3000L, true);
                }
                this.f5484f.c(crashDetailBeanA);
            }
            synchronized (this.f5485g) {
                if (this.k != null) {
                    y.c("Finish anr process.", new Object[0]);
                    this.k.b(true);
                }
            }
        } catch (Throwable th) {
            try {
                y.b(th);
            } finally {
                b(false);
            }
        }
    }

    private synchronized void g() {
        if (e()) {
            y.d("start when started!", new Object[0]);
            return;
        }
        if (TextUtils.isEmpty(this.f5486h)) {
            return;
        }
        synchronized (this.f5485g) {
            if (this.k == null || !this.k.isAlive()) {
                this.k = new ad();
                this.k.a(this.f5482d.j());
                this.k.a(new ad.a() { // from class: com.tencent.bugly.crashreport.crash.anr.b.3
                    @Override // com.tencent.bugly.proguard.ad.a
                    public final void a(boolean z, long j) {
                        if (z) {
                            if (!b.this.a()) {
                                y.c("main thread blocked overdue, blockTime:%s", Long.valueOf(j));
                                if (!com.tencent.bugly.proguard.a.a(b.this.f5481c)) {
                                    y.c("proc is not in anr, wait next check", new Object[0]);
                                    return;
                                }
                                long jCurrentTimeMillis = System.currentTimeMillis();
                                if (!b.this.a(jCurrentTimeMillis) && b.a(b.this, true)) {
                                    y.c("found anr", new Object[0]);
                                    NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
                                    if (nativeCrashHandler != null && nativeCrashHandler.isEnableCatchAnrTrace()) {
                                        y.c("anr trace enable, do dump trace", new Object[0]);
                                        nativeCrashHandler.dumpAnrNativeStack();
                                        return;
                                    }
                                    y.c("anr trace not enable", new Object[0]);
                                    File file = new File(b.this.f5486h, "manual_bugly_trace_" + jCurrentTimeMillis + ".txt");
                                    y.a("create new trace file:%s", file.getAbsoluteFile());
                                    z.a(file, "android trace not enable\n", 101376L, true);
                                    return;
                                }
                                return;
                            }
                            y.c("anr is processing, return", new Object[0]);
                        }
                    }
                });
                ad adVar = this.k;
                StringBuilder sb = new StringBuilder("Bugly-ThreadMonitor");
                int i = this.l;
                this.l = i + 1;
                sb.append(i);
                adVar.setName(sb.toString());
                this.k.b();
            }
        }
        this.i = new FileObserver(this.f5486h, 8) { // from class: com.tencent.bugly.crashreport.crash.anr.b.4
            @Override // android.os.FileObserver
            public final void onEvent(int i2, String str) {
                if (str == null) {
                    return;
                }
                y.d("observe file, dir:%s fileName:%s", b.this.f5486h, str);
                if (!b.a(str)) {
                    y.c("not manual trace file, ignore.", new Object[0]);
                    return;
                }
                if (!b.this.a()) {
                    y.c("proc is not in anr, just ignore", new Object[0]);
                    return;
                }
                long jA = z.a(str, "manual_bugly_trace_", ".txt");
                b.this.a(jA, b.this.f5486h + "/" + str);
                y.c("Finish handling one anr.", new Object[0]);
            }
        };
        try {
            this.i.startWatching();
            y.a("startWatchingPrivateAnrDir! dumFilePath is %s", this.f5486h);
            this.f5483e.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.anr.b.5
                @Override // java.lang.Runnable
                public final void run() {
                    b.b(b.this);
                }
            });
        } catch (Throwable th) {
            this.i = null;
            y.d("startWatchingPrivateAnrDir failed!", new Object[0]);
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(long j) {
        if (Math.abs(j - this.n) < DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT) {
            y.d("should not process ANR too Fre in %dms", 10000);
            return true;
        }
        this.n = j;
        return false;
    }

    private synchronized void h() {
        if (!e()) {
            y.d("close when closed!", new Object[0]);
            return;
        }
        synchronized (this.f5485g) {
            if (this.k != null) {
                this.k.a();
                this.k = null;
            }
        }
        y.a("stopWatchingPrivateAnrDir", new Object[0]);
        try {
            this.i.stopWatching();
            this.i = null;
            y.d("close anr monitor!", new Object[0]);
        } catch (Throwable th) {
            y.d("stop anr monitor failed!", new Object[0]);
            if (y.a(th)) {
                return;
            }
            th.printStackTrace();
        }
    }
}