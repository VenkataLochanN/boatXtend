package com.tencent.bugly.crashreport.common.info;

import android.content.Context;
import android.os.Build;
import android.os.Debug;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.y;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ArrayList<a> f5422a = new ArrayList<a>() { // from class: com.tencent.bugly.crashreport.common.info.b.1
        {
            byte b2 = 0;
            add(new l(b2));
            add(new f(b2));
            add(new g(b2));
            add(new m(b2));
            add(new h(b2));
            add(new i(b2));
            add(new k(b2));
            add(new e(b2));
            add(new j(b2));
            add(new C0136b(b2));
            add(new d(b2));
            add(new c(b2));
        }
    };

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<Integer, String> f5423b = new HashMap<Integer, String>() { // from class: com.tencent.bugly.crashreport.common.info.b.2
        {
            put(1, "GPRS");
            put(2, "EDGE");
            put(3, "UMTS");
            put(8, "HSDPA");
            put(9, "HSUPA");
            put(10, "HSPA");
            put(4, "CDMA");
            put(5, "EVDO_0");
            put(6, "EVDO_A");
            put(7, "1xRTT");
            put(11, "iDen");
            put(12, "EVDO_B");
            put(13, "LTE");
            put(14, "eHRPD");
            put(15, "HSPA+");
        }
    };

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String[] f5424c = {"/su", "/su/bin/su", "/sbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/data/local/su", "/system/xbin/su", "/system/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/system/bin/cufsdosck", "/system/xbin/cufsdosck", "/system/bin/cufsmgr", "/system/xbin/cufsmgr", "/system/bin/cufaevdd", "/system/xbin/cufaevdd", "/system/bin/conbb", "/system/xbin/conbb"};

    public static String m() {
        return "";
    }

    public static String a() {
        try {
            return Build.MODEL;
        } catch (Throwable th) {
            if (y.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String b() {
        try {
            return Build.VERSION.RELEASE;
        } catch (Throwable th) {
            if (y.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static int c() {
        try {
            return Build.VERSION.SDK_INT;
        } catch (Throwable th) {
            if (y.a(th)) {
                return -1;
            }
            th.printStackTrace();
            return -1;
        }
    }

    private static boolean p() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Throwable th) {
            if (y.a(th)) {
                return false;
            }
            th.printStackTrace();
            return false;
        }
    }

    public static String a(Context context, boolean z) {
        try {
            return System.getProperty("os.arch");
        } catch (Throwable th) {
            if (y.a(th)) {
                return "fail";
            }
            th.printStackTrace();
            return "fail";
        }
    }

    public static String a(Context context) {
        if (context != null && context.getApplicationInfo() != null) {
            String str = context.getApplicationInfo().nativeLibraryDir;
            if (TextUtils.isEmpty(str)) {
                return "fail";
            }
            if (str.endsWith("arm")) {
                return "armeabi-v7a";
            }
            if (str.endsWith("arm64")) {
                return "arm64-v8a";
            }
            if (str.endsWith("x86")) {
                return "x86";
            }
            if (str.endsWith("x86_64")) {
                return "x86_64";
            }
        }
        return "fail";
    }

    public static long d() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public static long e() {
        try {
            StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return -1L;
        }
    }

    public static long f() {
        return Debug.getPss() << 10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        r0 = java.lang.Long.parseLong(r2.replaceAll("[^\\d]", ""));
     */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:14:0x0034 -> B:33:0x0046). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long g() {
        /*
            r0 = 0
            r2 = 0
            java.io.BufferedReader r3 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L3a
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Throwable -> L3a
            java.lang.String r5 = "/proc/self/status"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L3a
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L3a
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L38
        L13:
            if (r2 == 0) goto L2f
            java.lang.String r4 = "VmSize"
            boolean r4 = r2.startsWith(r4)     // Catch: java.lang.Throwable -> L38
            if (r4 == 0) goto L2a
            java.lang.String r4 = "[^\\d]"
            java.lang.String r5 = ""
            java.lang.String r2 = r2.replaceAll(r4, r5)     // Catch: java.lang.Throwable -> L38
            long r0 = java.lang.Long.parseLong(r2)     // Catch: java.lang.Throwable -> L38
            goto L2f
        L2a:
            java.lang.String r2 = r3.readLine()     // Catch: java.lang.Throwable -> L38
            goto L13
        L2f:
            r3.close()     // Catch: java.lang.Throwable -> L33
            goto L46
        L33:
            r2 = move-exception
            r2.printStackTrace()
            goto L46
        L38:
            r2 = move-exception
            goto L3e
        L3a:
            r3 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
        L3e:
            com.tencent.bugly.proguard.y.a(r2)     // Catch: java.lang.Throwable -> L4a
            if (r3 == 0) goto L46
            r3.close()     // Catch: java.lang.Throwable -> L33
        L46:
            r2 = 10
            long r0 = r0 << r2
            return r0
        L4a:
            r0 = move-exception
            if (r3 == 0) goto L55
            r3.close()     // Catch: java.lang.Throwable -> L51
            goto L55
        L51:
            r1 = move-exception
            r1.printStackTrace()
        L55:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.g():long");
    }

    public static long h() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public static long i() {
        FileReader fileReader;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
            }
        } catch (Throwable th3) {
            fileReader = null;
            th = th3;
            bufferedReader = null;
        }
        try {
            String line = bufferedReader.readLine();
            if (line != null) {
                long j2 = Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10;
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    if (!y.a(e2)) {
                        e2.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e3) {
                    if (!y.a(e3)) {
                        e3.printStackTrace();
                    }
                }
                return j2;
            }
            try {
                bufferedReader.close();
            } catch (IOException e4) {
                if (!y.a(e4)) {
                    e4.printStackTrace();
                }
            }
            try {
                fileReader.close();
                return -1L;
            } catch (IOException e5) {
                if (y.a(e5)) {
                    return -1L;
                }
                e5.printStackTrace();
                return -1L;
            }
        } catch (Throwable th4) {
            th = th4;
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e6) {
                        if (!y.a(e6)) {
                            e6.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return -2L;
                }
                try {
                    fileReader.close();
                    return -2L;
                } catch (IOException e7) {
                    if (y.a(e7)) {
                        return -2L;
                    }
                    e7.printStackTrace();
                    return -2L;
                }
            } catch (Throwable th5) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e8) {
                        if (!y.a(e8)) {
                            e8.printStackTrace();
                        }
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e9) {
                        if (!y.a(e9)) {
                            e9.printStackTrace();
                        }
                    }
                }
                throw th5;
            }
        }
    }

    public static long j() {
        BufferedReader bufferedReader;
        FileReader fileReader;
        try {
            fileReader = new FileReader("/proc/meminfo");
            try {
                bufferedReader = new BufferedReader(fileReader, 2048);
            } catch (Throwable th) {
                th = th;
                bufferedReader = null;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
            fileReader = null;
        }
        try {
            bufferedReader.readLine();
            String line = bufferedReader.readLine();
            if (line == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e2) {
                    if (!y.a(e2)) {
                        e2.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e3) {
                    if (!y.a(e3)) {
                        e3.printStackTrace();
                    }
                }
                return -1L;
            }
            long j2 = (Long.parseLong(line.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10) + 0;
            String line2 = bufferedReader.readLine();
            if (line2 == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e4) {
                    if (!y.a(e4)) {
                        e4.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e5) {
                    if (!y.a(e5)) {
                        e5.printStackTrace();
                    }
                }
                return -1L;
            }
            long j3 = j2 + (Long.parseLong(line2.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
            String line3 = bufferedReader.readLine();
            if (line3 == null) {
                try {
                    bufferedReader.close();
                } catch (IOException e6) {
                    if (!y.a(e6)) {
                        e6.printStackTrace();
                    }
                }
                try {
                    fileReader.close();
                } catch (IOException e7) {
                    if (!y.a(e7)) {
                        e7.printStackTrace();
                    }
                }
                return -1L;
            }
            long j4 = j3 + (Long.parseLong(line3.split(":\\s+", 2)[1].toLowerCase().replace("kb", "").trim()) << 10);
            try {
                bufferedReader.close();
            } catch (IOException e8) {
                if (!y.a(e8)) {
                    e8.printStackTrace();
                }
            }
            try {
                fileReader.close();
            } catch (IOException e9) {
                if (!y.a(e9)) {
                    e9.printStackTrace();
                }
            }
            return j4;
        } catch (Throwable th3) {
            th = th3;
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e10) {
                        if (!y.a(e10)) {
                            e10.printStackTrace();
                        }
                    }
                }
                if (fileReader == null) {
                    return -2L;
                }
                try {
                    fileReader.close();
                    return -2L;
                } catch (IOException e11) {
                    if (y.a(e11)) {
                        return -2L;
                    }
                    e11.printStackTrace();
                    return -2L;
                }
            } catch (Throwable th4) {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e12) {
                        if (!y.a(e12)) {
                            e12.printStackTrace();
                        }
                    }
                }
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e13) {
                        if (!y.a(e13)) {
                            e13.printStackTrace();
                        }
                    }
                }
                throw th4;
            }
        }
    }

    public static long k() {
        if (!p()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (y.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    public static long l() {
        if (!p()) {
            return 0L;
        }
        try {
            StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
            return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
        } catch (Throwable th) {
            if (y.a(th)) {
                return -2L;
            }
            th.printStackTrace();
            return -2L;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x005e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b(android.content.Context r4) {
        /*
            java.lang.String r0 = "unknown"
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L57
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch: java.lang.Exception -> L57
            android.net.NetworkInfo r1 = r1.getActiveNetworkInfo()     // Catch: java.lang.Exception -> L57
            if (r1 != 0) goto L12
            r4 = 0
            return r4
        L12:
            int r2 = r1.getType()     // Catch: java.lang.Exception -> L57
            r3 = 1
            if (r2 != r3) goto L1c
            java.lang.String r4 = "WIFI"
            goto L62
        L1c:
            int r1 = r1.getType()     // Catch: java.lang.Exception -> L57
            if (r1 != 0) goto L61
            java.lang.String r1 = "phone"
            java.lang.Object r4 = r4.getSystemService(r1)     // Catch: java.lang.Exception -> L57
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch: java.lang.Exception -> L57
            if (r4 == 0) goto L61
            int r4 = r4.getNetworkType()     // Catch: java.lang.Exception -> L57
            java.util.Map<java.lang.Integer, java.lang.String> r1 = com.tencent.bugly.crashreport.common.info.b.f5423b     // Catch: java.lang.Exception -> L57
            java.lang.Integer r2 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Exception -> L57
            java.lang.Object r1 = r1.get(r2)     // Catch: java.lang.Exception -> L57
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Exception -> L57
            if (r1 != 0) goto L55
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L52
            java.lang.String r2 = "MOBILE("
            r0.<init>(r2)     // Catch: java.lang.Exception -> L52
            r0.append(r4)     // Catch: java.lang.Exception -> L52
            java.lang.String r4 = ")"
            r0.append(r4)     // Catch: java.lang.Exception -> L52
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L52
            goto L61
        L52:
            r4 = move-exception
            r0 = r1
            goto L58
        L55:
            r4 = r1
            goto L62
        L57:
            r4 = move-exception
        L58:
            boolean r1 = com.tencent.bugly.proguard.y.a(r4)
            if (r1 != 0) goto L61
            r4.printStackTrace()
        L61:
            r4 = r0
        L62:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.common.info.b.b(android.content.Context):java.lang.String");
    }

    public static String c(Context context) {
        Iterator<a> it = f5422a.iterator();
        while (it.hasNext()) {
            String strA = it.next().a(context);
            if (!TextUtils.isEmpty(strA)) {
                return strA;
            }
        }
        return null;
    }

    public static boolean d(Context context) {
        return !TextUtils.isEmpty(new i((byte) 0).a(context));
    }

    public static boolean e(Context context) {
        return !TextUtils.isEmpty(new k((byte) 0).a(context));
    }

    public static boolean n() {
        boolean z;
        String[] strArr = f5424c;
        int length = strArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            }
            if (new File(strArr[i2]).exists()) {
                z = true;
                break;
            }
            i2++;
        }
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || z;
    }

    public static boolean o() {
        float fMaxMemory = (float) (Runtime.getRuntime().maxMemory() / 1048576.0d);
        float f2 = (float) (Runtime.getRuntime().totalMemory() / 1048576.0d);
        float f3 = fMaxMemory - f2;
        y.c("maxMemory : %f", Float.valueOf(fMaxMemory));
        y.c("totalMemory : %f", Float.valueOf(f2));
        y.c("freeMemory : %f", Float.valueOf(f3));
        return f3 < 10.0f;
    }

    /* JADX INFO: compiled from: BUGLY */
    static abstract class a {
        public abstract String a(Context context);

        private a() {
        }

        /* synthetic */ a(byte b2) {
            this();
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class l extends a {
        private l() {
            super((byte) 0);
        }

        /* synthetic */ l(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.miui.ui.version.name");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "XiaoMi/MIUI/" + strA;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class f extends a {
        private f() {
            super((byte) 0);
        }

        /* synthetic */ f(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.build.version.emui");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "HuaWei/EMOTION/" + strA;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class g extends a {
        private g() {
            super((byte) 0);
        }

        /* synthetic */ g(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.lenovo.series");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "Lenovo/VIBE/" + ab.a(context, "ro.build.version.incremental");
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class m extends a {
        private m() {
            super((byte) 0);
        }

        /* synthetic */ m(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.build.nubia.rom.name");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "Zte/NUBIA/" + strA + "_" + ab.a(context, "ro.build.nubia.rom.code");
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class h extends a {
        private h() {
            super((byte) 0);
        }

        /* synthetic */ h(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.meizu.product.model");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "Meizu/FLYME/" + ab.a(context, "ro.build.display.id");
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class i extends a {
        private i() {
            super((byte) 0);
        }

        /* synthetic */ i(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.build.version.opporom");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "Oppo/COLOROS/" + strA;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class k extends a {
        private k() {
            super((byte) 0);
        }

        /* synthetic */ k(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.vivo.os.build.display.id");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "vivo/FUNTOUCH/" + strA;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class e extends a {
        private e() {
            super((byte) 0);
        }

        /* synthetic */ e(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.aa.romver");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "htc/" + strA + "/" + ab.a(context, "ro.build.description");
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class j extends a {
        private j() {
            super((byte) 0);
        }

        /* synthetic */ j(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.lewa.version");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "tcl/" + strA + "/" + ab.a(context, "ro.build.display.id");
        }
    }

    /* JADX INFO: renamed from: com.tencent.bugly.crashreport.common.info.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: BUGLY */
    static class C0136b extends a {
        private C0136b() {
            super((byte) 0);
        }

        /* synthetic */ C0136b(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.gn.gnromvernumber");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "amigo/" + strA + "/" + ab.a(context, "ro.build.display.id");
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class d extends a {
        private d() {
            super((byte) 0);
        }

        /* synthetic */ d(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            String strA = ab.a(context, "ro.build.tyd.kbstyle_version");
            if (ab.a(strA) || strA.equals("fail")) {
                return null;
            }
            return "dido/" + strA;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    static class c extends a {
        private c() {
            super((byte) 0);
        }

        /* synthetic */ c(byte b2) {
            this();
        }

        @Override // com.tencent.bugly.crashreport.common.info.b.a
        public final String a(Context context) {
            return ab.a(context, "ro.build.fingerprint") + "/" + ab.a(context, "ro.build.rom.id");
        }
    }
}