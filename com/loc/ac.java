package com.loc;

import android.content.Context;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: ErrorLogManager.java */
/* JADX INFO: loaded from: classes3.dex */
public class ac {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static WeakReference<ax> f4718a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f4719b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static WeakReference<bq> f4720c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static WeakReference<bq> f4721d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String[] f4722e = new String[10];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f4723f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f4724g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f4725h;
    private static t i;

    private static t a(String str) {
        List<t> listA = z.a();
        if (listA == null) {
            listA = new ArrayList();
        }
        if (str != null && !"".equals(str)) {
            for (t tVar : listA) {
                if (z.a(tVar.f(), str)) {
                    return tVar;
                }
            }
            if (str.contains("com.amap.api.col")) {
                try {
                    return u.a();
                } catch (j e2) {
                    e2.printStackTrace();
                }
            }
            if (str.contains("com.amap.co") || str.contains("com.amap.opensdk.co") || str.contains("com.amap.location")) {
                try {
                    t tVarB = u.b();
                    tVarB.a(true);
                    return tVarB;
                } catch (j e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:90:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0102 A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x00f6 -> B:107:0x00f9). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String a(java.util.List<com.loc.t> r11) {
        /*
            Method dump skipped, instruction units count: 259
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ac.a(java.util.List):java.lang.String");
    }

    static void a(Context context) {
        String strA;
        t tVar;
        List<t> listA = z.a();
        if (listA == null || listA.size() == 0 || (strA = a(listA)) == null || "".equals(strA) || (tVar = i) == null) {
            return;
        }
        a(context, tVar, 2, "ANR", strA);
    }

    private static void a(final Context context, final bq bqVar, final String str) {
        ab.d().submit(new Runnable() { // from class: com.loc.ac.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    synchronized (ac.class) {
                        ax axVarA = bd.a(ac.f4718a);
                        bd.a(context, axVarA, str, 1000, 4096000, "1");
                        axVarA.f4819f = bqVar;
                        if (axVarA.f4820g == null) {
                            axVarA.f4820g = new bh(new bg(context, new bl(), new aj(new al(new an())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", k.f(context), n.x(context), n.w(context), k.c(context), Build.MODEL, k.b(context), k.d(context), Build.VERSION.RELEASE));
                        }
                        axVarA.f4821h = 3600000;
                        ay.a(axVarA);
                    }
                } catch (Throwable th) {
                    ab.b(th, "lg", "pul");
                }
            }
        });
    }

    private static void a(Context context, t tVar, int i2, String str, String str2) {
        String str3;
        String strA = u.a(System.currentTimeMillis());
        String strA2 = bd.a(context, tVar);
        k.a(context);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(strA2);
        stringBuffer.append(",\"timestamp\":\"");
        stringBuffer.append(strA);
        stringBuffer.append("\",\"et\":\"");
        stringBuffer.append(i2);
        stringBuffer.append("\",\"classname\":\"");
        stringBuffer.append(str);
        stringBuffer.append("\",");
        stringBuffer.append("\"detail\":\"");
        stringBuffer.append(str2);
        stringBuffer.append("\"");
        String string = stringBuffer.toString();
        if (string == null || "".equals(string)) {
            return;
        }
        String strB = r.b(str2);
        if (i2 == 1) {
            str3 = z.f5344b;
        } else if (i2 == 2) {
            str3 = z.f5346d;
        } else if (i2 != 0) {
            return;
        } else {
            str3 = z.f5345c;
        }
        String str4 = str3;
        ax axVarA = bd.a(f4718a);
        bd.a(context, axVarA, str4, 1000, 4096000, "1");
        if (axVarA.f4818e == null) {
            axVarA.f4818e = new ai(new aj(new al(new an())));
        }
        try {
            ay.a(strB, u.a(string.replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "<br/>")), axVarA);
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String strA = u.a(th);
        t tVarA = a(strA);
        if (a(tVarA)) {
            String strReplaceAll = strA.replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "<br/>");
            String string = th.toString();
            if (string == null || "".equals(string)) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (str != null) {
                sb.append("class:");
                sb.append(str);
            }
            if (str2 != null) {
                sb.append(" method:");
                sb.append(str2);
                sb.append("$<br/>");
            }
            sb.append(strReplaceAll);
            a(context, tVarA, i2, string, sb.toString());
        }
    }

    static void a(t tVar, Context context, String str, String str2) {
        if (!a(tVar) || str == null || "".equals(str)) {
            return;
        }
        a(context, tVar, 1, str, str2);
    }

    private static boolean a(t tVar) {
        return tVar != null && tVar.e();
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i2 = f4723f; i2 < 10 && i2 <= 9; i2++) {
                sb.append(f4722e[i2]);
            }
            for (int i3 = 0; i3 < f4723f; i3++) {
                sb.append(f4722e[i3]);
            }
        } catch (Throwable th) {
            ab.b(th, "alg", "gLI");
        }
        return sb.toString();
    }

    static void b(Context context) {
        bo boVar = new bo(f4719b);
        f4719b = false;
        a(context, boVar, z.f5345c);
    }

    static void c(Context context) {
        WeakReference<bq> weakReference = f4720c;
        if (weakReference == null || weakReference.get() == null) {
            f4720c = new WeakReference<>(new bp(context, 3600000, "hKey", new br(context)));
        }
        a(context, f4720c.get(), z.f5346d);
    }

    static void d(Context context) {
        WeakReference<bq> weakReference = f4721d;
        if (weakReference == null || weakReference.get() == null) {
            f4721d = new WeakReference<>(new bp(context, 3600000, "gKey", new br(context)));
        }
        a(context, f4721d.get(), z.f5344b);
    }
}