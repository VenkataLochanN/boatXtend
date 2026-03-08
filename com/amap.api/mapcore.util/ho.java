package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* JADX INFO: compiled from: ErrorLogManager.java */
/* JADX INFO: loaded from: classes.dex */
public class ho {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static WeakReference<iv> f1262a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static boolean f1263b = true;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static WeakReference<jp> f1264c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static WeakReference<jp> f1265d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String[] f1266e = new String[10];

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f1267f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static boolean f1268g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static int f1269h;
    private static gs i;

    /* JADX INFO: compiled from: ErrorLogManager.java */
    public interface a {
        void a(int i);
    }

    private static boolean a(gs gsVar) {
        return gsVar != null && gsVar.f();
    }

    private static void a(Context context, gs gsVar, int i2, String str, String str2) {
        String str3;
        String strA = jc.a();
        String strA2 = jc.a(gi.a(context), jc.a(context, gsVar), strA, i2, str, str2);
        if (strA2 == null || "".equals(strA2)) {
            return;
        }
        String strC = gq.c(str2);
        if (i2 == 1) {
            str3 = hl.f1242b;
        } else if (i2 == 2) {
            str3 = hl.f1244d;
        } else if (i2 != 0) {
            return;
        } else {
            str3 = hl.f1243c;
        }
        a(context, strC, str3, strA2);
    }

    static void a(Context context) {
        String strA;
        gs gsVar;
        List<gs> listB = hl.b(context);
        if (listB == null || listB.size() == 0 || (strA = a(listB)) == null || "".equals(strA) || (gsVar = i) == null) {
            return;
        }
        a(context, gsVar, 2, "ANR", strA);
    }

    public static void a(Context context, gs gsVar, String str, int i2, String str2, String str3) {
        if (str2 == null || "".equals(str2)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (str2 != null) {
            sb.append("class:");
            sb.append(str2);
        }
        if (str3 != null) {
            sb.append(" method:");
            sb.append(str3);
            sb.append("$");
            sb.append("<br/>");
        }
        sb.append(str);
        a(context, gsVar, i2, str2, sb.toString());
    }

    public static void a(Context context, Throwable th, int i2, String str, String str2) {
        String strA = gt.a(th);
        gs gsVarA = a(context, strA);
        if (a(gsVarA)) {
            String strReplaceAll = strA.replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "<br/>");
            String strA2 = a(th);
            if (strA2 == null || "".equals(strA2)) {
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
                sb.append("$");
                sb.append("<br/>");
            }
            sb.append(strReplaceAll);
            a(context, gsVarA, i2, strA2, sb.toString());
        }
    }

    static void a(gs gsVar, Context context, String str, String str2) {
        if (!a(gsVar) || str == null || "".equals(str)) {
            return;
        }
        a(context, gsVar, 1, str, str2);
    }

    private static void a(Context context, String str, String str2, String str3) {
        iv ivVarA = jc.a(f1262a);
        jc.a(context, ivVarA, str2, 1000, 4096000, "1");
        if (ivVarA.f1413e == null) {
            ivVarA.f1413e = new ib(new ic(new ie(new Cif())));
        }
        try {
            iw.a(str, gt.a(str3.replaceAll(IOUtils.LINE_SEPARATOR_UNIX, "<br/>")), ivVarA);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, gs gsVar, String str, String str2) {
        String strA = jc.a();
        return jc.b(gi.a(context), jc.a(context, gsVar), strA, 1, str, str2);
    }

    public static void a(final Context context, final gs gsVar, final String str, final iv ivVar, final String str2) {
        try {
            hn.d().execute(new Runnable() { // from class: com.amap.api.mapcore.util.ho.1
                @Override // java.lang.Runnable
                public void run() {
                    String strB = ho.b(context, gsVar, str, str2);
                    if (ivVar.f1413e == null) {
                        ivVar.f1413e = new ib(new ic(new ie(new Cif())));
                    }
                    try {
                        iw.a(gq.c(strB), gt.a(strB), ivVar);
                    } catch (Throwable unused) {
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    static void b(Context context) {
        jn jnVar = new jn(f1263b);
        f1263b = false;
        a(context, jnVar, hl.f1243c);
    }

    static void c(Context context) {
        WeakReference<jp> weakReference = f1264c;
        if (weakReference == null || weakReference.get() == null) {
            f1264c = new WeakReference<>(new jo(context, 3600000, "hKey", new jq(context, false)));
        }
        a(context, f1264c.get(), hl.f1244d);
    }

    static void d(Context context) {
        WeakReference<jp> weakReference = f1265d;
        if (weakReference == null || weakReference.get() == null) {
            f1265d = new WeakReference<>(new jo(context, 3600000, "gKey", new jq(context, false)));
        }
        a(context, f1265d.get(), hl.f1242b);
    }

    private static void a(final Context context, final jp jpVar, final String str) {
        hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.ho.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    synchronized (ho.class) {
                        iv ivVarA = jc.a(ho.f1262a);
                        jc.a(context, ivVarA, str, 1000, 4096000, "1");
                        ivVarA.f1414f = jpVar;
                        if (ivVarA.f1415g == null) {
                            ivVarA.f1415g = new jg(new jf(context, new jk(), new ic(new ie(new Cif())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", gi.f(context), gm.y(context), gm.w(context), gi.c(context), Build.MODEL, gi.b(context), gi.d(context), Build.VERSION.RELEASE));
                        }
                        ivVarA.f1416h = 3600000;
                        iw.a(ivVarA);
                    }
                } catch (Throwable th) {
                    hn.c(th, "lg", "pul");
                }
            }
        });
    }

    public static void a(final Context context, final iv ivVar, final a aVar) {
        try {
            hn.d().submit(new Runnable() { // from class: com.amap.api.mapcore.util.ho.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        synchronized (ho.class) {
                            if (ivVar.f1415g == null) {
                                ivVar.f1415g = new jg(new jf(context, new jk(), new ic(new ie(new Cif())), "QImtleSI6IiVzIiwicGxhdGZvcm0iOiJhbmRyb2lkIiwiZGl1IjoiJXMiLCJhZGl1IjoiJXMiLCJwa2ciOiIlcyIsIm1vZGVsIjoiJXMiLCJhcHBuYW1lIjoiJXMiLCJhcHB2ZXJzaW9uIjoiJXMiLCJzeXN2ZXJzaW9uIjoiJXMi", gi.f(context), gm.y(context), gm.w(context), gi.c(context), Build.MODEL, gi.b(context), gi.d(context), Build.VERSION.RELEASE));
                            }
                            int iA = iw.a(ivVar);
                            if (aVar != null) {
                                aVar.a(iA);
                            }
                        }
                    } catch (Throwable th) {
                        hn.c(th, "lg", "pul");
                    }
                }
            });
        } catch (Throwable unused) {
        }
    }

    static gs a(Context context, String str) {
        List<gs> listB = hl.b(context);
        if (listB == null) {
            listB = new ArrayList();
        }
        if (str != null && !"".equals(str)) {
            for (gs gsVar : listB) {
                if (hl.a(gsVar.g(), str)) {
                    return gsVar;
                }
            }
            if (str.contains("com.amap.api.col")) {
                try {
                    return gt.a();
                } catch (gh e2) {
                    e2.printStackTrace();
                }
            }
            if (str.contains("com.amap.co") || str.contains("com.amap.opensdk.co") || str.contains("com.amap.location")) {
                try {
                    gs gsVarB = gt.b();
                    gsVarB.a(true);
                    return gsVarB;
                } catch (gh e3) {
                    e3.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String a(Throwable th) {
        return th.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:83:0x00e7  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00ec A[RETURN] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:80:0x00e0 -> B:100:0x00e3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static java.lang.String a(java.util.List<com.amap.api.mapcore.util.gs> r10) {
        /*
            Method dump skipped, instruction units count: 237
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.api.mapcore.util.ho.a(java.util.List):java.lang.String");
    }

    private static void a(String str) {
        try {
            if (f1267f > 9) {
                f1267f = 0;
            }
            f1266e[f1267f] = str;
            f1267f++;
        } catch (Throwable th) {
            hn.c(th, "alg", "aDa");
        }
    }

    private static String b() {
        StringBuilder sb = new StringBuilder();
        try {
            for (int i2 = f1267f; i2 < 10 && i2 <= 9; i2++) {
                sb.append(f1266e[i2]);
            }
            for (int i3 = 0; i3 < f1267f; i3++) {
                sb.append(f1266e[i3]);
            }
        } catch (Throwable th) {
            hn.c(th, "alg", "gLI");
        }
        return sb.toString();
    }
}