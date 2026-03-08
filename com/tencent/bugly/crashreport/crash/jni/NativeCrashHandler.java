package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.os.Build;
import com.autonavi.base.amap.mapcore.tools.GLMapStaticValue;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.y;
import java.io.File;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class NativeCrashHandler implements com.tencent.bugly.crashreport.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static NativeCrashHandler f5563a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static int f5564b = 1;
    private static boolean m = false;
    private static boolean n = false;
    private static boolean p = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f5565c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.info.a f5566d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final x f5567e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private NativeExceptionHandler f5568f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private String f5569g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final boolean f5570h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private boolean l = false;
    private com.tencent.bugly.crashreport.crash.b o;

    private native String getSoCpuAbi();

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    static /* synthetic */ boolean a(NativeCrashHandler nativeCrashHandler, int i, String str) {
        return nativeCrashHandler.a(GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, str);
    }

    private NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, x xVar, boolean z, String str) {
        this.f5565c = ab.a(context);
        try {
            if (ab.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + com.tencent.bugly.crashreport.common.info.a.a(context).f5416c + "/app_bugly";
        }
        this.o = bVar;
        this.f5569g = str;
        this.f5566d = aVar;
        this.f5567e = xVar;
        this.f5570h = z;
        this.f5568f = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, x xVar, boolean z, String str) {
        if (f5563a == null) {
            f5563a = new NativeCrashHandler(context, aVar, bVar, xVar, z, str);
        }
        return f5563a;
    }

    public static synchronized NativeCrashHandler getInstance() {
        return f5563a;
    }

    public synchronized String getDumpFilePath() {
        return this.f5569g;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f5569g = str;
    }

    public static void setShouldHandleInJava(boolean z) {
        p = z;
        NativeCrashHandler nativeCrashHandler = f5563a;
        if (nativeCrashHandler != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(z);
            nativeCrashHandler.a(GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, sb.toString());
        }
    }

    public static boolean isShouldHandleInJava() {
        return p;
    }

    public String getRunningCpuAbi() {
        try {
            return getSoCpuAbi();
        } catch (Throwable unused) {
            y.d("get so cpu abi failed，please upgrade bugly so version", new Object[0]);
            return "";
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(18:13|(1:15)(17:17|(1:19)|78|21|(1:23)|24|(1:26)|27|(1:29)(1:30)|31|(1:33)(1:34)|35|(1:37)|38|(1:40)|41|42)|16|78|21|(0)|24|(0)|27|(0)(0)|31|(0)(0)|35|(0)|38|(0)|41|42) */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007e A[Catch: all -> 0x008c, TryCatch #3 {all -> 0x008c, blocks: (B:21:0x0074, B:23:0x007e, B:24:0x0080, B:26:0x008a), top: B:78:0x0074 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008a A[Catch: all -> 0x008c, TRY_LEAVE, TryCatch #3 {all -> 0x008c, blocks: (B:21:0x0074, B:23:0x007e, B:24:0x0080, B:26:0x008a), top: B:78:0x0074 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0090 A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0098 A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a3 A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ab A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00ca A[Catch: all -> 0x0100, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f9 A[Catch: all -> 0x0100, TRY_LEAVE, TryCatch #0 {all -> 0x0100, blocks: (B:11:0x0015, B:13:0x001f, B:15:0x0051, B:16:0x005b, B:27:0x008c, B:29:0x0090, B:31:0x009f, B:33:0x00a3, B:35:0x00b2, B:37:0x00ca, B:38:0x00e0, B:40:0x00f9, B:34:0x00ab, B:30:0x0098, B:17:0x0063, B:19:0x0069), top: B:73:0x0015, outer: #2 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private synchronized void a(boolean r11) {
        /*
            Method dump skipped, instruction units count: 492
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.a(boolean):void");
    }

    public synchronized void startNativeMonitor() {
        if (!this.j && !this.i) {
            boolean z = !ab.a(this.f5566d.l);
            String str = "Bugly-ext";
            String str2 = this.f5566d.l;
            if (z) {
                str = str2;
            } else {
                this.f5566d.getClass();
            }
            this.j = a(str, z);
            if (this.j || this.i) {
                a(this.f5570h);
                if (m) {
                    setNativeAppVersion(this.f5566d.i);
                    setNativeAppChannel(this.f5566d.k);
                    setNativeAppPackage(this.f5566d.f5416c);
                    setNativeUserId(this.f5566d.g());
                    setNativeIsAppForeground(this.f5566d.a());
                    setNativeLaunchTime(this.f5566d.f5414a);
                }
                return;
            }
            return;
        }
        a(this.f5570h);
    }

    public void checkUploadRecordCrash() {
        this.f5567e.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.1
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                if (ab.a(NativeCrashHandler.this.f5565c, "native_record_lock", DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT)) {
                    if (!NativeCrashHandler.p) {
                        NativeCrashHandler.a(NativeCrashHandler.this, GLMapStaticValue.MAPRENDER_CAN_STOP_AND_FULLSCREEN_RENDEROVER, Bugly.SDK_IS_DEV);
                    }
                    CrashDetailBean crashDetailBeanA = b.a(NativeCrashHandler.this.f5565c, NativeCrashHandler.this.f5569g, NativeCrashHandler.this.f5568f);
                    if (crashDetailBeanA != null) {
                        y.a("[Native] Get crash from native record.", new Object[0]);
                        if (!NativeCrashHandler.this.o.a(crashDetailBeanA)) {
                            NativeCrashHandler.this.o.a(crashDetailBeanA, 3000L, false);
                        }
                        b.a(false, NativeCrashHandler.this.f5569g);
                    }
                    NativeCrashHandler.this.a();
                    ab.b(NativeCrashHandler.this.f5565c, "native_record_lock");
                    return;
                }
                y.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
            }
        });
    }

    private static boolean a(String str, boolean z) {
        boolean z2;
        try {
            y.a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                y.a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
                y.d(th.getMessage(), new Object[0]);
                y.d("[Native] Failed to load so: %s", str);
                return z2;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    private synchronized void c() {
        if (!this.k) {
            y.d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
        } catch (Throwable unused) {
            y.c("[Native] Failed to close native crash report.", new Object[0]);
        }
        if (unregist() != null) {
            y.a("[Native] Successfully closed native crash report.", new Object[0]);
            this.k = false;
            return;
        }
        try {
            ab.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.k = false;
            y.a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            y.c("[Native] Failed to close native crash report.", new Object[0]);
            this.j = false;
            this.i = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.j) {
            y.d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public void testNativeCrash(boolean z, boolean z2, boolean z3) {
        StringBuilder sb = new StringBuilder();
        sb.append(z);
        a(16, sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append(z2);
        a(17, sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append(z3);
        a(18, sb3.toString());
        testNativeCrash();
    }

    public void dumpAnrNativeStack() {
        a(19, "1");
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.f5568f;
    }

    protected final void a() {
        long jB = ab.b() - c.f5517g;
        long jB2 = ab.b() + DateUtil.DAY;
        File file = new File(this.f5569g);
        if (file.exists() && file.isDirectory()) {
            try {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                    int i = 0;
                    int i2 = 0;
                    for (File file2 : fileArrListFiles) {
                        long jLastModified = file2.lastModified();
                        if (jLastModified < jB || jLastModified >= jB2) {
                            y.a("[Native] Delete record file: %s", file2.getAbsolutePath());
                            i++;
                            if (file2.delete()) {
                                i2++;
                            }
                        }
                    }
                    y.c("[Native] Number of record files overdue: %d, has deleted: %d", Integer.valueOf(i), Integer.valueOf(i2));
                }
            } catch (Throwable th) {
                y.a(th);
            }
        }
    }

    public void removeEmptyNativeRecordFiles() {
        b.c(this.f5569g);
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            c();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.l;
    }

    private synchronized void c(boolean z) {
        if (this.l != z) {
            y.a("user change native %b", Boolean.valueOf(z));
            this.l = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        c(z);
        boolean zIsUserOpened = isUserOpened();
        com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (aVarA != null) {
            zIsUserOpened = zIsUserOpened && aVarA.c().f5429e;
        }
        if (zIsUserOpened != this.k) {
            y.a("native changed to %b", Boolean.valueOf(zIsUserOpened));
            b(zIsUserOpened);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0031 A[Catch: all -> 0x0043, TRY_LEAVE, TryCatch #0 {, blocks: (B:5:0x0005, B:7:0x000b, B:8:0x001a, B:10:0x0026, B:14:0x002d, B:16:0x0031), top: B:22:0x0005 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public synchronized void onStrategyChanged(com.tencent.bugly.crashreport.common.strategy.StrategyBean r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 1
            r1 = 0
            if (r5 == 0) goto L1a
            boolean r2 = r5.f5429e     // Catch: java.lang.Throwable -> L43
            boolean r3 = r4.k     // Catch: java.lang.Throwable -> L43
            if (r2 == r3) goto L1a
            java.lang.String r2 = "server native changed to %b"
            java.lang.Object[] r3 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L43
            boolean r5 = r5.f5429e     // Catch: java.lang.Throwable -> L43
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L43
            r3[r1] = r5     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.proguard.y.d(r2, r3)     // Catch: java.lang.Throwable -> L43
        L1a:
            com.tencent.bugly.crashreport.common.strategy.a r5 = com.tencent.bugly.crashreport.common.strategy.a.a()     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.crashreport.common.strategy.StrategyBean r5 = r5.c()     // Catch: java.lang.Throwable -> L43
            boolean r5 = r5.f5429e     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L2c
            boolean r5 = r4.l     // Catch: java.lang.Throwable -> L43
            if (r5 == 0) goto L2c
            r5 = r0
            goto L2d
        L2c:
            r5 = r1
        L2d:
            boolean r2 = r4.k     // Catch: java.lang.Throwable -> L43
            if (r5 == r2) goto L41
            java.lang.String r2 = "native changed to %b"
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L43
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r5)     // Catch: java.lang.Throwable -> L43
            r0[r1] = r3     // Catch: java.lang.Throwable -> L43
            com.tencent.bugly.proguard.y.a(r2, r0)     // Catch: java.lang.Throwable -> L43
            r4.b(r5)     // Catch: java.lang.Throwable -> L43
        L41:
            monitor-exit(r4)
            return
        L43:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.onStrategyChanged(com.tencent.bugly.crashreport.common.strategy.StrategyBean):void");
    }

    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((this.i || this.j) && m && str != null && str2 != null && str3 != null) {
            try {
                if (this.j) {
                    return appendNativeLog(str, str2, str3);
                }
                Boolean bool = (Boolean) ab.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                m = false;
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public String getLogFromNative() {
        if ((!this.i && !this.j) || !m) {
            return null;
        }
        try {
            if (this.j) {
                return getNativeLog();
            }
            return (String) ab.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "getNativeLog", null, null, null);
        } catch (UnsatisfiedLinkError unused) {
            m = false;
            return null;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((this.i || this.j) && m && str != null && str2 != null) {
            try {
                if (this.j) {
                    return putNativeKeyValue(str, str2);
                }
                Boolean bool = (Boolean) ab.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
                if (bool != null) {
                    return bool.booleanValue();
                }
                return false;
            } catch (UnsatisfiedLinkError unused) {
                m = false;
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    private boolean a(int i, String str) {
        if (this.j && n) {
            try {
                setNativeInfo(i, str);
                return true;
            } catch (UnsatisfiedLinkError unused) {
                n = false;
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                return false;
            }
        }
        return false;
    }

    public boolean filterSigabrtSysLog() {
        return a(998, "true");
    }

    public boolean setNativeAppVersion(String str) {
        return a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return a(11, str);
    }

    @Override // com.tencent.bugly.crashreport.a
    public boolean setNativeIsAppForeground(boolean z) {
        return a(14, z ? "true" : Bugly.SDK_IS_DEV);
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return a(15, String.valueOf(j));
        } catch (NumberFormatException e2) {
            if (y.a(e2)) {
                return false;
            }
            e2.printStackTrace();
            return false;
        }
    }

    public void enableCatchAnrTrace() {
        if (Build.VERSION.SDK_INT > 31 || Build.VERSION.SDK_INT <= 19) {
            return;
        }
        f5564b |= 2;
    }

    public boolean isEnableCatchAnrTrace() {
        return (f5564b & 2) == 2;
    }
}