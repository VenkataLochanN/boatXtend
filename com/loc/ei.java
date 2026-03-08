package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import com.ido.life.util.DateUtil;
import com.loc.l;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthUtil.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ei {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static volatile boolean f5157h = false;
    private static boolean i = true;
    private static int j = 1000;
    private static int k = 200;
    private static boolean l = false;
    private static int m = 20;
    private static int n = 0;
    private static volatile int o = 0;
    private static boolean p = true;
    private static boolean q = true;
    private static int r = -1;
    private static long s;
    private static ArrayList<String> t = new ArrayList<>();
    private static ArrayList<String> u = new ArrayList<>();
    private static volatile boolean v = false;
    private static boolean w = true;
    private static long x = 300000;
    private static boolean y = false;
    private static double z = 0.618d;
    private static boolean A = true;
    private static int B = 80;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static long f5150a = DateUtil.HOUR;
    private static boolean C = false;
    private static boolean D = true;
    private static boolean E = false;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile long f5151b = 0;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static boolean f5152c = true;
    private static boolean F = true;
    private static long G = -1;
    private static boolean H = true;
    private static int I = 1;
    private static boolean J = true;
    private static int K = 5;
    private static boolean L = false;
    private static String M = "CMjAzLjEwNy4xLjEvMTU0MDgxL2Q";
    private static long N = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static boolean f5153d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static boolean f5154e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static int f5155f = 20480;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static int f5156g = 10800000;

    public static void a(final Context context) {
        if (f5157h) {
            return;
        }
        f5157h = true;
        l.a(context, ej.c(), ej.d(), new l.a() { // from class: com.loc.ei.1
            @Override // com.loc.l.a
            public final void a(l.b bVar) {
                ei.a(context, bVar);
            }
        });
    }

    public static boolean a() {
        return i;
    }

    public static boolean a(long j2) {
        if (!w) {
            return false;
        }
        long jA = ep.a() - j2;
        long j3 = x;
        return j3 < 0 || jA < j3;
    }

    static boolean a(Context context, l.b bVar) {
        SharedPreferences.Editor editorA;
        int i2;
        JSONArray jSONArrayOptJSONArray;
        try {
            editorA = eo.a(context, "pref");
            try {
                try {
                    l.b.a aVar = bVar.f5262g;
                    if (aVar != null) {
                        i = aVar.f5264a;
                        eo.a(editorA, "exception", i);
                        JSONObject jSONObject = aVar.f5266c;
                        if (jSONObject != null) {
                            j = jSONObject.optInt("fn", j);
                            int iOptInt = jSONObject.optInt("mpn", k);
                            k = iOptInt;
                            if (iOptInt > 500) {
                                k = 500;
                            }
                            if (k < 30) {
                                k = 30;
                            }
                            l = l.a(jSONObject.optString("igu"), l);
                            m = jSONObject.optInt("ms", m);
                            o = jSONObject.optInt("rot", 0);
                            n = jSONObject.optInt("pms", 0);
                        }
                        ba.a(j, l, m, n);
                        bc.a(l, n);
                        eo.a(editorA, "fn", j);
                        eo.a(editorA, "mpn", k);
                        eo.a(editorA, "igu", l);
                        eo.a(editorA, "ms", m);
                        eo.a(editorA, "rot", o);
                        eo.a(editorA, "pms", n);
                    }
                } catch (Throwable unused) {
                    if (editorA != null) {
                        try {
                            eo.a(editorA);
                        } catch (Throwable unused2) {
                        }
                    }
                    return false;
                }
            } catch (Throwable th) {
                ej.a(th, "AuthUtil", "loadConfigDataUploadException");
            }
            c(context);
            JSONObject jSONObject2 = bVar.f5261f;
            if (jSONObject2 == null) {
                if (editorA != null) {
                    try {
                        eo.a(editorA);
                    } catch (Throwable unused3) {
                    }
                }
                return true;
            }
            try {
                JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("13S");
                if (jSONObjectOptJSONObject != null) {
                    try {
                        f5150a = jSONObjectOptJSONObject.optInt("at", 123) * 60 * 1000;
                        eo.a(editorA, "13S_at", f5150a);
                    } catch (Throwable th2) {
                        ej.a(th2, "AuthUtil", "requestSdkAuthInterval");
                    }
                    if (jSONObjectOptJSONObject != null) {
                        try {
                            f5152c = l.a(jSONObjectOptJSONObject.optString("re"), f5152c);
                            eo.a(editorA, "fr", f5152c);
                        } catch (Throwable th3) {
                            ej.a(th3, "AuthUtil", "checkReLocationAble");
                        }
                    }
                    try {
                        D = l.a(jSONObjectOptJSONObject.optString("nla"), D);
                        eo.a(editorA, "13S_nla", D);
                    } catch (Throwable unused4) {
                    }
                    try {
                        F = l.a(jSONObjectOptJSONObject.optString("asw"), F);
                        eo.a(editorA, "asw", F);
                    } catch (Throwable unused5) {
                    }
                    try {
                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("mlpl");
                        if (jSONArrayOptJSONArray2 == null || jSONArrayOptJSONArray2.length() <= 0 || context == null) {
                            E = false;
                            eo.a(editorA, "13S_mlpl");
                        } else {
                            eo.a(editorA, "13S_mlpl", u.b(jSONArrayOptJSONArray2.toString()));
                            E = a(context, jSONArrayOptJSONArray2);
                        }
                    } catch (Throwable unused6) {
                    }
                }
            } catch (Throwable th4) {
                ej.a(th4, "AuthUtil", "loadConfigAbleStatus");
            }
            try {
                JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject("11G");
                if (jSONObjectOptJSONObject2 != null) {
                    boolean zA = l.a(jSONObjectOptJSONObject2.optString("able"), w);
                    w = zA;
                    if (zA) {
                        x = jSONObjectOptJSONObject2.optInt("c", 300) * 1000;
                    }
                    y = l.a(jSONObjectOptJSONObject2.optString("fa"), y);
                    z = Math.min(1.0d, Math.max(0.2d, jSONObjectOptJSONObject2.optDouble("ms")));
                    eo.a(editorA, "ca", w);
                    eo.a(editorA, "ct", x);
                    eo.a(editorA, "11G_fa", y);
                    eo.a(editorA, "11G_ms", String.valueOf(z));
                }
            } catch (Throwable th5) {
                ej.a(th5, "AuthUtil", "loadConfigDataCacheAble");
            }
            try {
                JSONObject jSONObjectOptJSONObject3 = jSONObject2.optJSONObject("13J");
                if (jSONObjectOptJSONObject3 != null) {
                    boolean zA2 = l.a(jSONObjectOptJSONObject3.optString("able"), A);
                    A = zA2;
                    if (zA2) {
                        B = jSONObjectOptJSONObject3.optInt("c", B);
                    }
                    eo.a(editorA, "13J_able", A);
                    eo.a(editorA, "13J_c", B);
                }
            } catch (Throwable th6) {
                ej.a(th6, "AuthUtil", "loadConfigDataGpsGeoAble");
            }
            try {
                JSONObject jSONObjectOptJSONObject4 = jSONObject2.optJSONObject("15O");
                if (jSONObjectOptJSONObject4 != null) {
                    if (l.a(jSONObjectOptJSONObject4.optString("able"), false) && ((jSONArrayOptJSONArray = jSONObjectOptJSONObject4.optJSONArray("fl")) == null || jSONArrayOptJSONArray.length() <= 0 || jSONArrayOptJSONArray.toString().contains(Build.MANUFACTURER))) {
                        G = jSONObjectOptJSONObject4.optInt("iv", 30) * 1000;
                    } else {
                        G = -1L;
                    }
                    eo.a(editorA, "awsi", G);
                }
            } catch (Throwable unused7) {
            }
            try {
                JSONObject jSONObjectOptJSONObject5 = jSONObject2.optJSONObject("15U");
                if (jSONObjectOptJSONObject5 != null) {
                    boolean zA3 = l.a(jSONObjectOptJSONObject5.optString("able"), H);
                    int iOptInt2 = jSONObjectOptJSONObject5.optInt("yn", I);
                    N = jSONObjectOptJSONObject5.optLong("sysTime", N);
                    eo.a(editorA, "15ua", zA3);
                    eo.a(editorA, "15un", iOptInt2);
                    eo.a(editorA, "15ust", N);
                }
            } catch (Throwable unused8) {
            }
            if (jSONObject2 != null) {
                try {
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("17Y");
                    if (jSONObject3 != null) {
                        f5153d = l.a(jSONObject3.optString("able"), f5153d);
                        eo.a(editorA, "17ya", f5153d);
                        f5154e = l.a(jSONObject3.optString("mup"), f5154e);
                        eo.a(editorA, "17ym", f5154e);
                        int iOptInt3 = jSONObject3.optInt("max", 20);
                        if (iOptInt3 > 0) {
                            eo.a(editorA, "17yx", iOptInt3);
                            f5155f = iOptInt3 * 1024;
                        }
                        int iOptInt4 = jSONObject3.optInt("inv", 3);
                        if (iOptInt4 > 0) {
                            eo.a(editorA, "17yi", iOptInt4);
                            f5156g = iOptInt4 * 60 * 60 * 1000;
                        }
                    }
                } catch (Throwable unused9) {
                }
            }
            if (jSONObject2 != null) {
                try {
                    JSONObject jSONObjectOptJSONObject6 = jSONObject2.optJSONObject("17J");
                    if (jSONObjectOptJSONObject6 != null) {
                        boolean zA4 = l.a(jSONObjectOptJSONObject6.optString("able"), false);
                        J = zA4;
                        eo.a(editorA, "ok9", zA4);
                        if (zA4) {
                            String strOptString = jSONObjectOptJSONObject6.optString("auth");
                            M = jSONObjectOptJSONObject6.optString("ht");
                            eo.a(editorA, "ok11", M);
                            l.a(strOptString, false);
                            L = l.a(jSONObjectOptJSONObject6.optString("nr"), false);
                            String strOptString2 = jSONObjectOptJSONObject6.optString("tm");
                            if (!TextUtils.isEmpty(strOptString2) && (i2 = Integer.parseInt(strOptString2)) > 0 && i2 < 20) {
                                K = i2;
                                eo.a(editorA, "ok10", K);
                            }
                        }
                    }
                } catch (Throwable unused10) {
                }
            }
            if (editorA != null) {
                try {
                    eo.a(editorA);
                } catch (Throwable unused11) {
                }
            }
            return true;
        } catch (Throwable unused12) {
            editorA = null;
        }
    }

    private static boolean a(Context context, JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0 && context != null) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        if (ep.b(context, jSONArray.getString(i2))) {
                            return true;
                        }
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static int b() {
        return k;
    }

    public static void b(Context context) {
        if (v) {
            return;
        }
        v = true;
        try {
            i = eo.a(context, "pref", "exception", i);
            c(context);
        } catch (Throwable th) {
            ej.a(th, "AuthUtil", "loadLastAbleState p1");
        }
        try {
            j = eo.a(context, "pref", "fn", j);
            k = eo.a(context, "pref", "mpn", k);
            l = eo.a(context, "pref", "igu", l);
            m = eo.a(context, "pref", "ms", m);
            o = eo.a(context, "pref", "rot", 0);
            n = eo.a(context, "pref", "pms", 0);
            ba.a(j, l, m, n);
            bc.a(l, n);
        } catch (Throwable unused) {
        }
        try {
            w = eo.a(context, "pref", "ca", w);
            x = eo.a(context, "pref", "ct", x);
            y = eo.a(context, "pref", "11G_fa", y);
            z = Double.valueOf(eo.a(context, "pref", "11G_ms", String.valueOf(z))).doubleValue();
            z = Math.max(0.2d, z);
        } catch (Throwable unused2) {
        }
        try {
            f5152c = eo.a(context, "pref", "fr", f5152c);
        } catch (Throwable unused3) {
        }
        try {
            F = eo.a(context, "pref", "asw", F);
        } catch (Throwable unused4) {
        }
        try {
            G = eo.a(context, "pref", "awsi", G);
        } catch (Throwable unused5) {
        }
        try {
            H = eo.a(context, "pref", "15ua", H);
            I = eo.a(context, "pref", "15un", I);
            N = eo.a(context, "pref", "15ust", N);
        } catch (Throwable unused6) {
        }
        try {
            J = eo.a(context, "pref", "ok9", J);
            K = eo.a(context, "pref", "ok10", K);
            M = eo.a(context, "pref", "ok11", M);
        } catch (Throwable unused7) {
        }
        try {
            f5153d = eo.a(context, "pref", "17ya", false);
            f5154e = eo.a(context, "pref", "17ym", false);
            f5156g = eo.a(context, "pref", "17yi", 2) * 60 * 60 * 1000;
            f5155f = eo.a(context, "pref", "17yx", 100) * 1024;
        } catch (Throwable unused8) {
        }
        try {
            f5151b = ep.b();
            f5150a = eo.a(context, "pref", "13S_at", f5150a);
            D = eo.a(context, "pref", "13S_nla", D);
            A = eo.a(context, "pref", "13J_able", A);
            B = eo.a(context, "pref", "13J_c", B);
        } catch (Throwable unused9) {
        }
        l.b(context);
        try {
            String strA = eo.a(context, "pref", "13S_mlpl", (String) null);
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            E = a(context, new JSONArray(u.c(strA)));
        } catch (Throwable unused10) {
        }
    }

    public static int c() {
        if (o < 0) {
            o = 0;
        }
        return o;
    }

    public static void c(Context context) {
        try {
            t tVarC = ej.c();
            tVarC.a(i);
            ab.a(context, tVarC);
        } catch (Throwable unused) {
        }
    }

    public static long d() {
        return x;
    }

    public static boolean e() {
        return w;
    }

    public static boolean f() {
        return y;
    }

    public static double g() {
        return z;
    }

    public static boolean h() {
        return A;
    }

    public static int i() {
        return B;
    }

    public static boolean j() {
        return D;
    }

    public static boolean k() {
        return E;
    }

    public static boolean l() {
        return f5152c;
    }

    public static boolean m() {
        return F;
    }

    public static long n() {
        return G;
    }

    public static boolean o() {
        return L;
    }

    public static boolean p() {
        return J;
    }

    public static int q() {
        return K;
    }

    public static String r() {
        return u.c(M);
    }

    public static boolean s() {
        return H && I > 0;
    }

    public static int t() {
        return I;
    }

    public static long u() {
        return N;
    }
}