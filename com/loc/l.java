package com.loc;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.loc.p;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import kotlinx.coroutines.DebugKt;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: AuthConfigManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f5244a = -1;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static String f5245b = "";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static Context f5246c = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static volatile boolean f5248e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Vector<e> f5249f = new Vector<>();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static Map<String, Integer> f5250g = new HashMap();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f5251h = null;
    private static long i = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static volatile boolean f5247d = false;
    private static volatile ConcurrentHashMap<String, Long> j = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, Long> k = new ConcurrentHashMap<>(8);
    private static volatile ConcurrentHashMap<String, d> l = new ConcurrentHashMap<>(8);

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public interface a {
        void a(b bVar);
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Deprecated
        public JSONObject f5256a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        @Deprecated
        public JSONObject f5257b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public String f5258c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5259d = -1;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public long f5260e = 0;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public JSONObject f5261f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public a f5262g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public C0134b f5263h;
        private boolean i;

        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class a {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f5264a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            public boolean f5265b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            public JSONObject f5266c;
        }

        /* JADX INFO: renamed from: com.loc.l$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: AuthConfigManager.java */
        public static class C0134b {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public boolean f5267a;
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    static class c extends ar {

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f5268f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private Map<String, String> f5269g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private boolean f5270h;
        private String i;
        private String j;
        private String k;

        c(Context context, t tVar, String str, String str2, String str3, String str4) {
            super(context, tVar);
            this.f5268f = str;
            this.f5269g = null;
            this.f5270h = Build.VERSION.SDK_INT != 19;
            this.i = str2;
            this.j = str3;
            this.k = str4;
        }

        public final boolean a() {
            return this.f5270h;
        }

        @Override // com.loc.ar
        public final byte[] a_() {
            return null;
        }

        @Override // com.loc.av
        public final Map<String, String> b() {
            if (TextUtils.isEmpty(this.k)) {
                return null;
            }
            HashMap map = new HashMap();
            map.put("host", this.k);
            return map;
        }

        @Override // com.loc.av
        public final String c() {
            String str = this.f5270h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
            try {
                return !TextUtils.isEmpty(this.i) ? str.replace("restsdk.amap.com", this.i) : str;
            } catch (Throwable unused) {
                return str;
            }
        }

        @Override // com.loc.q, com.loc.av
        public final String d() {
            try {
                String str = this.f5270h ? "https://restsdk.amap.com/v3/iasdkauth" : "http://restsdk.amap.com/v3/iasdkauth";
                try {
                    if (!TextUtils.isEmpty(this.j)) {
                        return str.replace("restsdk.amap.com", this.j);
                    }
                } catch (Throwable unused) {
                }
                Uri uri = Uri.parse(str);
                return uri.buildUpon().authority("dualstack-" + uri.getAuthority()).build().toString();
            } catch (Throwable unused2) {
                return null;
            }
        }

        @Override // com.loc.ar
        public final byte[] f() {
            String strW = n.w(this.f4774a);
            if (!TextUtils.isEmpty(strW)) {
                strW = r.a(new StringBuilder(strW).reverse().toString());
            }
            HashMap map = new HashMap();
            map.put("authkey", TextUtils.isEmpty(this.f5268f) ? "" : this.f5268f);
            map.put("plattype", "android");
            map.put("product", this.f4775b.a());
            map.put("version", this.f4775b.b());
            map.put("output", "json");
            StringBuilder sb = new StringBuilder();
            sb.append(Build.VERSION.SDK_INT);
            map.put("androidversion", sb.toString());
            map.put("deviceId", strW);
            map.put("manufacture", Build.MANUFACTURER);
            Map<String, String> map2 = this.f5269g;
            if (map2 != null && !map2.isEmpty()) {
                map.putAll(this.f5269g);
            }
            map.put("abitype", u.a(this.f4774a));
            map.put("ext", this.f4775b.d());
            return u.a(u.a(map));
        }

        @Override // com.loc.ar
        protected final String g() {
            return "3.0";
        }

        @Override // com.loc.av
        protected final String h() {
            return !TextUtils.isEmpty(this.k) ? this.k : super.h();
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    private static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        t f5271a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f5272b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a f5273c;

        private d() {
        }

        /* synthetic */ d(byte b2) {
            this();
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    private static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f5274a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f5275b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private AtomicInteger f5276c;

        public e(String str, String str2, int i) {
            this.f5274a = str;
            this.f5275b = str2;
            this.f5276c = new AtomicInteger(i);
        }

        public static e b(String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject(str);
                return new e(jSONObject.optString("a"), jSONObject.optString("f"), jSONObject.optInt("h"));
            } catch (Throwable unused) {
                return null;
            }
        }

        public final int a() {
            AtomicInteger atomicInteger = this.f5276c;
            if (atomicInteger == null) {
                return 0;
            }
            return atomicInteger.get();
        }

        public final void a(String str) {
            this.f5275b = str;
        }

        public final String b() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("a", this.f5274a);
                jSONObject.put("f", this.f5275b);
                jSONObject.put("h", this.f5276c.get());
                return jSONObject.toString();
            } catch (Throwable unused) {
                return "";
            }
        }
    }

    /* JADX INFO: compiled from: AuthConfigManager.java */
    public static class f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static boolean f5277a = true;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static boolean f5278b = false;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static boolean f5279c = true;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static int f5280d = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static boolean f5281e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static int f5282f;
    }

    public static b a(Context context, t tVar, String str, String str2, String str3, String str4) {
        return b(context, tVar, str, str2, str3, str4);
    }

    public static void a(int i2) {
        if (i2 != 2) {
            return;
        }
        try {
            e eVarB = b(f5246c, "IPV6_CONFIG_NAME");
            String strA = u.a(System.currentTimeMillis(), "yyyyMMdd");
            if (!strA.equals(eVarB.f5275b)) {
                eVarB.a(strA);
                eVarB.f5276c.set(0);
            }
            eVarB.f5276c.incrementAndGet();
            Context context = f5246c;
            if (eVarB != null && !TextUtils.isEmpty(eVarB.f5274a)) {
                String strB = eVarB.b();
                if (TextUtils.isEmpty(strB) || context == null) {
                    return;
                }
                new x("IPV6_CONFIG_NAME").a(context, "i", strB);
            }
        } catch (Throwable unused) {
        }
    }

    public static void a(Context context) {
        if (context != null) {
            f5246c = context.getApplicationContext();
        }
    }

    private static void a(Context context, t tVar, String str) {
        HashMap map = new HashMap();
        map.put("amap_sdk_auth_fail", "1");
        map.put("amap_sdk_auth_fail_type", str);
        map.put("amap_sdk_name", tVar.a());
        map.put("amap_sdk_version", tVar.c());
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            bb bbVar = new bb(context, "core", "1.0", "O001");
            bbVar.a(string);
            bc.a(bbVar, context);
        } catch (j unused) {
        }
    }

    public static synchronized void a(Context context, t tVar, String str, a aVar) {
        if (context == null || tVar == null) {
            return;
        }
        try {
            if (f5246c == null) {
                f5246c = context.getApplicationContext();
            }
            String strA = tVar.a();
            if (TextUtils.isEmpty(strA)) {
                return;
            }
            a(tVar);
            if (l == null) {
                l = new ConcurrentHashMap<>(8);
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            if (!l.containsKey(strA)) {
                d dVar = new d((byte) 0);
                dVar.f5271a = tVar;
                dVar.f5272b = str;
                dVar.f5273c = aVar;
                l.put(strA, dVar);
                j.put(strA, Long.valueOf(x.c(f5246c, "open_common", strA)));
            }
        } catch (Throwable th) {
            y.a(th, "at", "rglc");
        }
    }

    private static void a(Context context, t tVar, String str, b bVar, JSONObject jSONObject) throws JSONException {
        boolean zA;
        b.a aVar = new b.a();
        aVar.f5264a = false;
        aVar.f5265b = false;
        bVar.f5262g = aVar;
        try {
            String[] strArrSplit = str.split(";");
            if (strArrSplit != null && strArrSplit.length > 0) {
                for (String str2 : strArrSplit) {
                    if (jSONObject.has(str2)) {
                        bVar.f5261f.putOpt(str2, jSONObject.get(str2));
                    }
                }
            }
        } catch (Throwable th) {
            y.a(th, "at", "co");
        }
        if (u.a(jSONObject, "16H")) {
            try {
                bVar.i = a(jSONObject.getJSONObject("16H").optString("able"), false);
            } catch (Throwable th2) {
                y.a(th2, "AuthConfigManager", "load 16H");
            }
        }
        if (u.a(jSONObject, "11K")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("11K");
                aVar.f5264a = a(jSONObject2.getString("able"), false);
                if (jSONObject2.has(DebugKt.DEBUG_PROPERTY_VALUE_OFF)) {
                    aVar.f5266c = jSONObject2.getJSONObject(DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                }
            } catch (Throwable th3) {
                y.a(th3, "AuthConfigManager", "load 11K");
            }
        }
        if (u.a(jSONObject, "145")) {
            try {
                bVar.f5256a = jSONObject.getJSONObject("145");
            } catch (Throwable th4) {
                y.a(th4, "AuthConfigManager", "load 145");
            }
        }
        if (u.a(jSONObject, "14D")) {
            try {
                bVar.f5257b = jSONObject.getJSONObject("14D");
            } catch (Throwable th5) {
                y.a(th5, "AuthConfigManager", "load 14D");
            }
        }
        if (u.a(jSONObject, "151")) {
            try {
                JSONObject jSONObject3 = jSONObject.getJSONObject("151");
                b.C0134b c0134b = new b.C0134b();
                if (jSONObject3 != null) {
                    c0134b.f5267a = a(jSONObject3.optString("able"), false);
                }
                bVar.f5263h = c0134b;
            } catch (Throwable th6) {
                y.a(th6, "AuthConfigManager", "load 151");
            }
        }
        if (u.a(jSONObject, "17S")) {
            try {
                JSONObject jSONObject4 = jSONObject.getJSONObject("17S");
                if (jSONObject4 != null && (zA = a(jSONObject4.optString("able"), false)) != f5248e) {
                    f5248e = zA;
                    if (context != null) {
                        SharedPreferences.Editor editorB = x.b(context, "open_common");
                        x.a(editorB, "a2", zA);
                        x.a(editorB);
                    }
                }
            } catch (Throwable th7) {
                y.a(th7, "AuthConfigManager", "load 17S");
            }
        }
        if (u.a(jSONObject, "15K")) {
            try {
                JSONObject jSONObject5 = jSONObject.getJSONObject("15K");
                if (jSONObject5 != null) {
                    boolean zA2 = a(jSONObject5.optString("ucf"), f.f5277a);
                    boolean zA3 = a(jSONObject5.optString("fsv2"), f.f5278b);
                    boolean zA4 = a(jSONObject5.optString("usc"), f.f5279c);
                    int iOptInt = jSONObject5.optInt("umv", f.f5280d);
                    boolean zA5 = a(jSONObject5.optString("ust"), f.f5281e);
                    int iOptInt2 = jSONObject5.optInt("ustv", f.f5282f);
                    if (zA2 != f.f5277a || zA3 != f.f5278b || zA4 != f.f5279c || iOptInt != f.f5280d || zA5 != f.f5281e || iOptInt2 != f.f5280d) {
                        f.f5277a = zA2;
                        f.f5278b = zA3;
                        f.f5279c = zA4;
                        f.f5280d = iOptInt;
                        f.f5281e = zA5;
                        f.f5282f = iOptInt2;
                        try {
                            SharedPreferences.Editor editorB2 = x.b(context, "open_common");
                            x.a(editorB2, "ucf", f.f5277a);
                            x.a(editorB2, "fsv2", f.f5278b);
                            x.a(editorB2, "usc", f.f5279c);
                            x.a(editorB2, "umv", f.f5280d);
                            x.a(editorB2, "ust", f.f5281e);
                            x.a(editorB2, "ustv", f.f5282f);
                            x.a(editorB2);
                        } catch (Throwable unused) {
                        }
                    }
                }
            } catch (Throwable th8) {
                y.a(th8, "AuthConfigManager", "load 15K");
            }
        }
        if (u.a(jSONObject, "183")) {
            try {
                as.a(tVar, jSONObject.getJSONObject("183"));
            } catch (Throwable th9) {
                y.a(th9, "AuthConfigManager", "load 183");
            }
        }
    }

    private static void a(Context context, t tVar, Throwable th) {
        a(context, tVar, th.getMessage());
    }

    public static void a(Context context, String str) {
        k.a(context, str);
    }

    private static void a(t tVar) {
        if (tVar != null) {
            try {
                if (TextUtils.isEmpty(tVar.a())) {
                    return;
                }
                String strC = tVar.c();
                if (TextUtils.isEmpty(strC)) {
                    strC = tVar.b();
                }
                if (TextUtils.isEmpty(strC)) {
                    return;
                }
                v.a(tVar.a(), strC);
            } catch (Throwable unused) {
            }
        }
    }

    public static synchronized void a(final String str, boolean z, final String str2, final String str3, final String str4) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
            if (l == null) {
                return;
            }
            if (l.containsKey(str)) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                if (z) {
                    as.a(true, str);
                }
                ab.d().submit(new Runnable() { // from class: com.loc.l.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        d dVar = (d) l.l.get(str);
                        if (dVar == null) {
                            return;
                        }
                        a aVar = dVar.f5273c;
                        b bVarA = l.a(l.f5246c, dVar.f5271a, dVar.f5272b, str2, str3, str4);
                        if (bVarA == null || aVar == null) {
                            return;
                        }
                        aVar.a(bVarA);
                    }
                });
            }
        } catch (Throwable th) {
            y.a(th, "at", "lca");
        }
    }

    public static void a(String str, boolean z, boolean z2, boolean z3, long j2) {
        if (TextUtils.isEmpty(str) || f5246c == null) {
            return;
        }
        HashMap map = new HashMap();
        map.put("url", str);
        map.put("downLevel", String.valueOf(z2));
        int iQ = n.q(f5246c);
        String str2 = AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE;
        map.put("ant", iQ == 0 ? AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE : "1");
        map.put("type", z ? "6" : AlexaCustomSkillConstant.EVENT_START_SPORT);
        if (!z3) {
            str2 = "1";
        }
        map.put(NotificationCompat.CATEGORY_STATUS, str2);
        map.put(com.ido.ble.event.stat.one.d.C, String.valueOf(j2));
        String string = new JSONObject(map).toString();
        if (TextUtils.isEmpty(string)) {
            return;
        }
        try {
            bb bbVar = new bb(f5246c, "core", "1.0", "O002");
            bbVar.a(string);
            bc.a(bbVar, f5246c);
        } catch (j unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0067 A[Catch: all -> 0x00ed, TryCatch #0 {all -> 0x00ed, blocks: (B:5:0x0008, B:7:0x0014, B:9:0x001a, B:11:0x0022, B:14:0x0032, B:16:0x0038, B:20:0x004a, B:21:0x0061, B:23:0x0067, B:25:0x0077, B:26:0x0084, B:28:0x008a, B:30:0x0098, B:32:0x00a0, B:33:0x00a3, B:35:0x00a7, B:37:0x00af, B:39:0x00bf, B:42:0x00c6, B:44:0x00ce, B:45:0x00d6, B:47:0x00dc, B:49:0x00e4, B:17:0x003b), top: B:69:0x0006 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00fb A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a() {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.a():boolean");
    }

    public static synchronized boolean a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (l == null) {
                return false;
            }
            if (k == null) {
                k = new ConcurrentHashMap<>(8);
            }
            if (l.containsKey(str) && !k.containsKey(str)) {
                k.put(str, Long.valueOf(SystemClock.elapsedRealtime()));
                return true;
            }
        } finally {
        }
        return false;
    }

    public static synchronized boolean a(String str, long j2) {
        boolean z = false;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            if (j2 > c(str)) {
                long jLongValue = 0;
                if (k != null && k.containsKey(str)) {
                    jLongValue = k.get(str).longValue();
                }
                if (SystemClock.elapsedRealtime() - jLongValue > 30000) {
                    z = true;
                }
            }
        } catch (Throwable unused) {
        }
        return z;
    }

    public static boolean a(String str, boolean z) {
        try {
            if (TextUtils.isEmpty(str)) {
                return z;
            }
            String[] strArrSplit = URLDecoder.decode(str).split("/");
            return strArrSplit[strArrSplit.length - 1].charAt(4) % 2 == 1;
        } catch (Throwable unused) {
            return z;
        }
    }

    private static boolean a(InetAddress inetAddress) {
        return inetAddress.isLoopbackAddress() || inetAddress.isLinkLocalAddress() || inetAddress.isAnyLocalAddress();
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static com.loc.l.b b(android.content.Context r22, com.loc.t r23, java.lang.String r24, java.lang.String r25, java.lang.String r26, java.lang.String r27) {
        /*
            Method dump skipped, instruction units count: 696
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.b(android.content.Context, com.loc.t, java.lang.String, java.lang.String, java.lang.String, java.lang.String):com.loc.l$b");
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x002f A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static synchronized com.loc.l.e b(android.content.Context r6, java.lang.String r7) {
        /*
            java.lang.Class<com.loc.l> r0 = com.loc.l.class
            monitor-enter(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L72
            r2 = 0
            r3 = 0
            if (r1 != 0) goto L2c
            r1 = r3
        Lc:
            java.util.Vector<com.loc.l$e> r4 = com.loc.l.f5249f     // Catch: java.lang.Throwable -> L72
            int r4 = r4.size()     // Catch: java.lang.Throwable -> L72
            if (r1 >= r4) goto L2c
            java.util.Vector<com.loc.l$e> r4 = com.loc.l.f5249f     // Catch: java.lang.Throwable -> L72
            java.lang.Object r4 = r4.get(r1)     // Catch: java.lang.Throwable -> L72
            com.loc.l$e r4 = (com.loc.l.e) r4     // Catch: java.lang.Throwable -> L72
            if (r4 == 0) goto L29
            java.lang.String r5 = com.loc.l.e.c(r4)     // Catch: java.lang.Throwable -> L72
            boolean r5 = r7.equals(r5)     // Catch: java.lang.Throwable -> L72
            if (r5 == 0) goto L29
            goto L2d
        L29:
            int r1 = r1 + 1
            goto Lc
        L2c:
            r4 = r2
        L2d:
            if (r4 == 0) goto L31
            monitor-exit(r0)
            return r4
        L31:
            if (r6 != 0) goto L35
            monitor-exit(r0)
            return r2
        L35:
            com.loc.x r1 = new com.loc.x     // Catch: java.lang.Throwable -> L72
            r1.<init>(r7)     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = "i"
            java.lang.String r6 = r1.a(r6, r7)     // Catch: java.lang.Throwable -> L72
            com.loc.l$e r6 = com.loc.l.e.b(r6)     // Catch: java.lang.Throwable -> L72
            long r1 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L72
            java.lang.String r7 = "yyyyMMdd"
            java.lang.String r7 = com.loc.u.a(r1, r7)     // Catch: java.lang.Throwable -> L72
            if (r6 != 0) goto L57
            com.loc.l$e r6 = new com.loc.l$e     // Catch: java.lang.Throwable -> L72
            java.lang.String r1 = "IPV6_CONFIG_NAME"
            r6.<init>(r1, r7, r3)     // Catch: java.lang.Throwable -> L72
        L57:
            java.lang.String r1 = com.loc.l.e.a(r6)     // Catch: java.lang.Throwable -> L72
            boolean r1 = r7.equals(r1)     // Catch: java.lang.Throwable -> L72
            if (r1 != 0) goto L6b
            r6.a(r7)     // Catch: java.lang.Throwable -> L72
            java.util.concurrent.atomic.AtomicInteger r7 = com.loc.l.e.b(r6)     // Catch: java.lang.Throwable -> L72
            r7.set(r3)     // Catch: java.lang.Throwable -> L72
        L6b:
            java.util.Vector<com.loc.l$e> r7 = com.loc.l.f5249f     // Catch: java.lang.Throwable -> L72
            r7.add(r6)     // Catch: java.lang.Throwable -> L72
            monitor-exit(r0)
            return r6
        L72:
            r6 = move-exception
            monitor-exit(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.l.b(android.content.Context, java.lang.String):com.loc.l$e");
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        f5248e = x.a(context, "open_common", "a2", true);
    }

    public static synchronized void b(String str) {
        if (k == null) {
            return;
        }
        if (k.containsKey(str)) {
            k.remove(str);
        }
    }

    private static synchronized void b(String str, long j2) {
        try {
            if (l != null && l.containsKey(str)) {
                if (j == null) {
                    j = new ConcurrentHashMap<>(8);
                }
                j.put(str, Long.valueOf(j2));
                if (f5246c != null) {
                    SharedPreferences.Editor editorB = x.b(f5246c, "open_common");
                    x.a(editorB, str, j2);
                    x.a(editorB);
                }
            }
        } catch (Throwable th) {
            y.a(th, "at", "ucut");
        }
    }

    public static synchronized void b(String str, boolean z) {
        a(str, z, (String) null, (String) null, (String) null);
    }

    public static boolean b() {
        Integer num;
        Context context = f5246c;
        if (context == null) {
            return false;
        }
        String strV = n.v(context);
        return (TextUtils.isEmpty(strV) || (num = f5250g.get(strV.toUpperCase())) == null || num.intValue() != 2) ? false : true;
    }

    public static synchronized long c(String str) {
        try {
            if (j == null) {
                j = new ConcurrentHashMap<>(8);
            }
            if (j.containsKey(str)) {
                return j.get(str).longValue();
            }
        } finally {
        }
        return 0L;
    }

    public static boolean c() {
        Integer num;
        Context context = f5246c;
        if (context == null) {
            return false;
        }
        String strV = n.v(context);
        return (TextUtils.isEmpty(strV) || (num = f5250g.get(strV.toUpperCase())) == null || num.intValue() < 2) ? false : true;
    }

    public static void d() {
        if (f5247d) {
            return;
        }
        try {
            f5247d = true;
            Context context = f5246c;
            if (context == null) {
                return;
            }
            p.a.f5305a.a(f5246c);
            b(f5246c);
            f.f5277a = x.a(context, "open_common", "ucf", f.f5277a);
            f.f5278b = x.a(context, "open_common", "fsv2", f.f5278b);
            f.f5279c = x.a(context, "open_common", "usc", f.f5279c);
            f.f5280d = x.a(context, "open_common", "umv", f.f5280d);
            f.f5281e = x.a(context, "open_common", "ust", f.f5281e);
            f.f5282f = x.a(context, "open_common", "ustv", f.f5282f);
        } catch (Throwable unused) {
        }
    }
}