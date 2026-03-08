package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.aa;
import com.tencent.bugly.proguard.ab;
import com.tencent.bugly.proguard.y;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class a implements NativeExceptionHandler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Context f5572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.crash.b f5573b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.info.a f5574c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final com.tencent.bugly.crashreport.common.strategy.a f5575d;

    public a(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2) {
        this.f5572a = context;
        this.f5573b = bVar;
        this.f5574c = aVar;
        this.f5575d = aVar2;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final CrashDetailBean packageCrashDatas(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, byte[] bArr, Map<String, String> map, boolean z, boolean z2) {
        int i;
        String str12;
        int iIndexOf;
        boolean zM = c.a().m();
        if (zM) {
            y.e("This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful!", new Object[0]);
        }
        CrashDetailBean crashDetailBean = new CrashDetailBean();
        crashDetailBean.f5450b = 1;
        crashDetailBean.f5453e = this.f5574c.k();
        crashDetailBean.f5454f = this.f5574c.i;
        crashDetailBean.f5455g = this.f5574c.u();
        crashDetailBean.m = this.f5574c.g();
        crashDetailBean.n = str3;
        crashDetailBean.o = zM ? " This Crash Caused By ANR , PLS To Fix ANR , This Trace May Be Not Useful![Bugly]" : "";
        crashDetailBean.p = str4;
        crashDetailBean.q = str5 != null ? str5 : "";
        crashDetailBean.r = j;
        crashDetailBean.u = ab.a(crashDetailBean.q.getBytes());
        crashDetailBean.A = str;
        crashDetailBean.B = str2;
        crashDetailBean.L = this.f5574c.w();
        crashDetailBean.f5456h = this.f5574c.t();
        crashDetailBean.i = this.f5574c.F();
        crashDetailBean.v = str8;
        NativeCrashHandler nativeCrashHandler = NativeCrashHandler.getInstance();
        String dumpFilePath = nativeCrashHandler != null ? nativeCrashHandler.getDumpFilePath() : null;
        String strA = b.a(dumpFilePath, str8);
        if (!ab.a(strA)) {
            crashDetailBean.Y = strA;
        }
        crashDetailBean.Z = b.b(dumpFilePath);
        crashDetailBean.w = b.a(str9, c.f5515e, null, false);
        crashDetailBean.x = b.a(str10, c.f5515e, null, true);
        crashDetailBean.M = str7;
        crashDetailBean.N = str6;
        crashDetailBean.O = str11;
        crashDetailBean.F = this.f5574c.o();
        crashDetailBean.G = this.f5574c.n();
        crashDetailBean.H = this.f5574c.p();
        crashDetailBean.I = com.tencent.bugly.crashreport.common.info.b.f();
        crashDetailBean.J = com.tencent.bugly.crashreport.common.info.b.g();
        crashDetailBean.K = com.tencent.bugly.crashreport.common.info.b.h();
        if (z) {
            crashDetailBean.C = com.tencent.bugly.crashreport.common.info.b.j();
            crashDetailBean.D = com.tencent.bugly.crashreport.common.info.b.e();
            crashDetailBean.E = com.tencent.bugly.crashreport.common.info.b.l();
            if (crashDetailBean.w == null) {
                Context context = this.f5572a;
                crashDetailBean.w = ab.a(c.f5515e, (String) null);
            }
            crashDetailBean.y = aa.a();
            crashDetailBean.P = this.f5574c.f5414a;
            crashDetailBean.Q = this.f5574c.a();
            crashDetailBean.z = ab.a(this.f5574c.h(), c.f5516f, false);
            int iIndexOf2 = crashDetailBean.q.indexOf("java:\n");
            if (iIndexOf2 > 0 && (i = iIndexOf2 + 6) < crashDetailBean.q.length()) {
                String strSubstring = crashDetailBean.q.substring(i, crashDetailBean.q.length() - 1);
                if (strSubstring.length() > 0 && crashDetailBean.z.containsKey(crashDetailBean.B) && (iIndexOf = (str12 = crashDetailBean.z.get(crashDetailBean.B)).indexOf(strSubstring)) > 0) {
                    String strSubstring2 = str12.substring(iIndexOf);
                    crashDetailBean.z.put(crashDetailBean.B, strSubstring2);
                    crashDetailBean.q = crashDetailBean.q.substring(0, i);
                    crashDetailBean.q += strSubstring2;
                }
            }
            if (str == null) {
                crashDetailBean.A = this.f5574c.f5417d;
            }
            this.f5573b.d(crashDetailBean);
            crashDetailBean.T = this.f5574c.D();
            crashDetailBean.U = this.f5574c.E();
            crashDetailBean.V = this.f5574c.x();
            crashDetailBean.W = this.f5574c.C();
        } else {
            crashDetailBean.C = -1L;
            crashDetailBean.D = -1L;
            crashDetailBean.E = -1L;
            if (crashDetailBean.w == null) {
                crashDetailBean.w = "This crash occurred at last process! Log is miss, when get an terrible ABRT Native Exception etc.";
            }
            crashDetailBean.P = -1L;
            crashDetailBean.T = -1;
            crashDetailBean.U = -1;
            crashDetailBean.V = map;
            crashDetailBean.W = this.f5574c.C();
            crashDetailBean.z = null;
            if (str == null) {
                crashDetailBean.A = "unknown(record)";
            }
            if (bArr != null) {
                crashDetailBean.y = bArr;
            }
        }
        return crashDetailBean;
    }

    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    public final void handleNativeException(int i, int i2, long j, long j2, String str, String str2, String str3, String str4, int i3, String str5, int i4, int i5, int i6, String str6, String str7) {
        y.a("Native Crash Happen v1", new Object[0]);
        handleNativeException2(i, i2, j, j2, str, str2, str3, str4, i3, str5, i4, i5, i6, str6, str7, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x017a A[PHI: r4
  0x017a: PHI (r4v10 java.lang.String) = (r4v9 java.lang.String), (r4v15 java.lang.String) binds: [B:38:0x0150, B:42:0x0160] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01a5 A[Catch: all -> 0x0222, TryCatch #1 {all -> 0x0222, blocks: (B:47:0x019f, B:49:0x01a5, B:51:0x01ae), top: B:78:0x019f }] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01ae A[Catch: all -> 0x0222, TRY_LEAVE, TryCatch #1 {all -> 0x0222, blocks: (B:47:0x019f, B:49:0x01a5, B:51:0x01ae), top: B:78:0x019f }] */
    @Override // com.tencent.bugly.crashreport.crash.jni.NativeExceptionHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleNativeException2(int r28, int r29, long r30, long r32, java.lang.String r34, java.lang.String r35, java.lang.String r36, java.lang.String r37, int r38, java.lang.String r39, int r40, int r41, int r42, java.lang.String r43, java.lang.String r44, java.lang.String[] r45) {
        /*
            Method dump skipped, instruction units count: 562
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.a.handleNativeException2(int, int, long, long, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String[]):void");
    }

    private static Map<String, String> a(String[] strArr) {
        HashMap map = new HashMap(strArr == null ? 1 : strArr.length);
        if (strArr != null) {
            for (int i = 0; i < strArr.length; i++) {
                String str = strArr[i];
                if (str != null) {
                    y.a("Extra message[%d]: %s", Integer.valueOf(i), str);
                    String[] strArrSplit = str.split("=");
                    if (strArrSplit.length == 2) {
                        map.put(strArrSplit[0], strArrSplit[1]);
                    } else {
                        y.d("bad extraMsg %s", str);
                    }
                }
            }
        } else {
            y.c("not found extraMsg", new Object[0]);
        }
        return map;
    }
}