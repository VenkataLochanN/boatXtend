package com.tencent.bugly;

import android.content.Context;
import android.util.Log;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.y;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static boolean f5371a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static List<a> f5372b = new ArrayList();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static boolean f5373c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static o f5374d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f5375e;

    private static boolean a(com.tencent.bugly.crashreport.common.info.a aVar) {
        List<String> list = aVar.n;
        aVar.getClass();
        return list != null && list.contains("bugly");
    }

    public static synchronized void a(Context context) {
        a(context, null);
    }

    public static synchronized void a(Context context, BuglyStrategy buglyStrategy) {
        if (f5375e) {
            y.d("[init] initial Multi-times, ignore this.", new Object[0]);
            return;
        }
        if (context == null) {
            Log.w(y.f5777a, "[init] context of init() is null, check it.");
            return;
        }
        com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(context);
        if (a(aVarA)) {
            f5371a = false;
            return;
        }
        String strF = aVarA.f();
        if (strF == null) {
            Log.e(y.f5777a, "[init] meta data of BUGLY_APPID in AndroidManifest.xml should be set.");
        } else {
            a(context, strF, aVarA.t, buglyStrategy);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x0222 A[Catch: all -> 0x0239, TryCatch #1 {, blocks: (B:4:0x0009, B:6:0x000e, B:10:0x0019, B:14:0x0024, B:18:0x002e, B:20:0x0032, B:21:0x006e, B:23:0x00af, B:26:0x00b3, B:28:0x00c1, B:30:0x00cf, B:32:0x00d5, B:33:0x00eb, B:34:0x00fa, B:36:0x0100, B:38:0x010a, B:40:0x0110, B:41:0x0126, B:47:0x0156, B:53:0x016a, B:55:0x0174, B:57:0x017a, B:58:0x0190, B:59:0x019f, B:61:0x01a5, B:63:0x01ab, B:64:0x01c1, B:65:0x01cd, B:67:0x01d3, B:68:0x01df, B:42:0x013a, B:44:0x0145, B:46:0x014f, B:50:0x0163, B:52:0x0167, B:70:0x01ec, B:80:0x021a, B:81:0x021d, B:83:0x0222, B:85:0x0229, B:77:0x0211, B:79:0x0217, B:72:0x01f4, B:74:0x0204), top: B:93:0x0009, inners: #0, #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01f4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void a(android.content.Context r19, java.lang.String r20, boolean r21, com.tencent.bugly.BuglyStrategy r22) {
        /*
            Method dump skipped, instruction units count: 572
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.b.a(android.content.Context, java.lang.String, boolean, com.tencent.bugly.BuglyStrategy):void");
    }

    public static synchronized void a(a aVar) {
        if (!f5372b.contains(aVar)) {
            f5372b.add(aVar);
        }
    }
}