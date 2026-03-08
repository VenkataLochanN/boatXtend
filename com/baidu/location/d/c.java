package com.baidu.location.d;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.ido.alexa.AlexaCustomSkillConstant;
import com.ido.life.constants.Constants;
import com.tencent.bugly.BuglyStrategy;
import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.text.Typography;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f2285a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2286b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f2287c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private double f2288d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Long f2289e;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final SQLiteDatabase f2292h;
    private final SQLiteDatabase i;
    private boolean p = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final C0020c f2290f = new C0020c(this, true);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final C0020c f2291g = new C0020c(this, false);
    private StringBuffer o = new StringBuffer();
    private StringBuffer j = null;
    private StringBuffer k = null;
    private HashSet<Long> l = new HashSet<>();
    private ConcurrentHashMap<Long, Integer> m = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Long, String> n = new ConcurrentHashMap<>();

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        double f2293a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        double f2294b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        double f2295c;

        private a(double d2, double d3, double d4) {
            this.f2293a = d2;
            this.f2294b = d3;
            this.f2295c = d4;
        }

        /* synthetic */ a(double d2, double d3, double d4, d dVar) {
            this(d2, d3, d4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private String f2296a;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Long f2298c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private BDLocation f2299d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private BDLocation f2300e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private BDLocation f2301f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f2302g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private LinkedHashMap<String, Integer> f2303h;

        private b(String str, Long l, BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str2, LinkedHashMap<String, Integer> linkedHashMap) {
            this.f2296a = str;
            this.f2298c = l;
            this.f2299d = bDLocation;
            this.f2300e = bDLocation2;
            this.f2301f = bDLocation3;
            this.f2302g = str2;
            this.f2303h = linkedHashMap;
        }

        /* synthetic */ b(c cVar, String str, Long l, BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str2, LinkedHashMap linkedHashMap, d dVar) {
            this(str, l, bDLocation, bDLocation2, bDLocation3, str2, linkedHashMap);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                c.this.a(this.f2296a, this.f2298c, this.f2299d);
                c.this.j = null;
                c.this.k = null;
                c.this.a(this.f2303h);
                c.this.a(this.f2301f, this.f2299d, this.f2300e, this.f2296a, this.f2298c);
                if (this.f2302g != null) {
                    c.this.f2285a.j().a(this.f2302g);
                }
            } catch (Exception unused) {
            }
            this.f2303h = null;
            this.f2296a = null;
            this.f2302g = null;
            this.f2298c = null;
            this.f2299d = null;
            this.f2300e = null;
            this.f2301f = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.baidu.location.d.c$c, reason: collision with other inner class name */
    final class C0020c extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2305b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final String f2306c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f2307d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private c f2308e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2309f = false;
        private int q = 0;
        private long r = -1;
        private long s = -1;
        private long t = -1;
        private long u = -1;

        C0020c(c cVar, boolean z) {
            this.f2308e = cVar;
            this.f2306c = z ? "load" : "update";
            this.k = new HashMap();
            this.f2305b = h.f2328b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(String str, String str2, String str3) {
            this.f2307d = str3;
            this.f2305b = String.format("http://%s/%s", str, str2);
            a(v.a().c(), false, "ofloc.map.baidu.com");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            this.q++;
            this.r = System.currentTimeMillis();
        }

        private boolean d() {
            if (this.q < 2) {
                return true;
            }
            if (this.r + 43200000 >= System.currentTimeMillis()) {
                return false;
            }
            this.q = 0;
            this.r = -1L;
            return true;
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:24:0x005a  */
        /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private void e() throws java.lang.Throwable {
            /*
                r9 = this;
                r0 = 0
                r9.f2307d = r0
                boolean r0 = r9.j()
                r1 = 86400000(0x5265c00, double:4.2687272E-316)
                r3 = -1
                if (r0 == 0) goto L22
                long r5 = r9.s
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 == 0) goto L1d
                long r5 = r5 + r1
                long r7 = java.lang.System.currentTimeMillis()
                int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
                if (r0 > 0) goto L28
            L1d:
                java.lang.String r0 = r9.f()
                goto L26
            L22:
                java.lang.String r0 = r9.g()
            L26:
                r9.f2307d = r0
            L28:
                java.lang.String r0 = r9.f2307d
                if (r0 != 0) goto L56
                long r5 = r9.t
                int r0 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r0 == 0) goto L3b
                long r5 = r5 + r1
                long r0 = java.lang.System.currentTimeMillis()
                int r0 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
                if (r0 > 0) goto L56
            L3b:
                com.baidu.location.d.c r0 = com.baidu.location.d.c.this
                com.baidu.location.d.h r0 = com.baidu.location.d.c.a(r0)
                com.baidu.location.d.l r0 = r0.k()
                boolean r0 = r0.a()
                if (r0 == 0) goto L50
                java.lang.String r0 = r9.h()
                goto L54
            L50:
                java.lang.String r0 = r9.i()
            L54:
                r9.f2307d = r0
            L56:
                java.lang.String r0 = r9.f2307d
                if (r0 == 0) goto L6d
                com.baidu.location.a.v r0 = com.baidu.location.a.v.a()
                java.util.concurrent.ExecutorService r0 = r0.c()
                java.lang.String r1 = "https://ofloc.map.baidu.com/offline_loc"
                if (r0 == 0) goto L6a
                r9.a(r0, r1)
                goto L6d
            L6a:
                r9.c(r1)
            L6d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.C0020c.e():void");
        }

        private String f() {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                jSONObject.put("type", AlexaCustomSkillConstant.EVENT_UN_RECOGNIZE);
                jSONObject.put("cuid", com.baidu.location.g.b.a().f2466c);
                jSONObject.put("ver", "1");
                jSONObject.put("prod", com.baidu.location.g.b.f2461f + ":" + com.baidu.location.g.b.f2460e);
            } catch (Exception unused) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:105:? A[SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:107:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x006b  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x0121  */
        /* JADX WARN: Removed duplicated region for block: B:68:0x014a  */
        /* JADX WARN: Removed duplicated region for block: B:70:0x014d  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x010b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0110 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:90:0x00f4 A[EXC_TOP_SPLITTER, PHI: r6 r7 r8
  0x00f4: PHI (r6v2 org.json.JSONObject) = (r6v1 org.json.JSONObject), (r6v4 org.json.JSONObject) binds: [B:58:0x011c, B:35:0x00f2] A[DONT_GENERATE, DONT_INLINE]
  0x00f4: PHI (r7v4 android.database.Cursor) = (r7v3 android.database.Cursor), (r7v9 android.database.Cursor) binds: [B:58:0x011c, B:35:0x00f2] A[DONT_GENERATE, DONT_INLINE]
  0x00f4: PHI (r8v3 android.database.Cursor) = (r8v2 android.database.Cursor), (r8v13 android.database.Cursor) binds: [B:58:0x011c, B:35:0x00f2] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:92:0x0119 A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private java.lang.String g() throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 348
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.C0020c.g():java.lang.String");
        }

        private String h() {
            JSONObject jSONObject;
            try {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", "2");
                    jSONObject.put("ver", "1");
                    jSONObject.put("cuid", com.baidu.location.g.b.a().f2466c);
                    jSONObject.put("prod", com.baidu.location.g.b.f2461f + ":" + com.baidu.location.g.b.f2460e);
                    this.t = System.currentTimeMillis();
                } catch (Exception unused) {
                }
            } catch (Exception unused2) {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        private String i() throws Throwable {
            JSONObject jSONObject;
            JSONObject jSONObjectB;
            try {
                jSONObjectB = c.this.f2285a.k().b();
            } catch (Exception unused) {
            }
            if (jSONObjectB != null) {
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", Constants.DIALDEFNED_VERSION_CONNECT);
                    jSONObject.put("ver", "1");
                    jSONObject.put("cuid", com.baidu.location.g.b.a().f2466c);
                    jSONObject.put("prod", com.baidu.location.g.b.f2461f + ":" + com.baidu.location.g.b.f2460e);
                    jSONObject.put("rgc", jSONObjectB);
                    this.t = System.currentTimeMillis();
                } catch (Exception unused2) {
                }
            } else {
                jSONObject = null;
            }
            if (jSONObject != null) {
                return Jni.encodeOfflineLocationUpdateRequest(jSONObject.toString());
            }
            return null;
        }

        /* JADX WARN: Removed duplicated region for block: B:15:0x0037  */
        /* JADX WARN: Removed duplicated region for block: B:46:0x003f A[EXC_TOP_SPLITTER, PHI: r0 r1
  0x003f: PHI (r0v3 android.database.Cursor) = (r0v2 android.database.Cursor), (r0v6 android.database.Cursor) binds: [B:33:0x0058, B:18:0x003d] A[DONT_GENERATE, DONT_INLINE]
  0x003f: PHI (r1v3 boolean) = (r1v0 boolean), (r1v6 boolean) binds: [B:33:0x0058, B:18:0x003d] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private boolean j() throws java.lang.Throwable {
            /*
                r5 = this;
                r0 = 0
                r1 = 1
                com.baidu.location.d.c r2 = com.baidu.location.d.c.this     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
                android.database.sqlite.SQLiteDatabase r2 = com.baidu.location.d.c.b(r2)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
                java.lang.String r3 = "SELECT COUNT(*) FROM AP;"
                android.database.Cursor r2 = r2.rawQuery(r3, r0)     // Catch: java.lang.Throwable -> L45 java.lang.Exception -> L52
                com.baidu.location.d.c r3 = com.baidu.location.d.c.this     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                android.database.sqlite.SQLiteDatabase r3 = com.baidu.location.d.c.b(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                java.lang.String r4 = "SELECT COUNT(*) FROM CL"
                android.database.Cursor r0 = r3.rawQuery(r4, r0)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                r3 = 0
                if (r2 == 0) goto L38
                boolean r4 = r2.moveToFirst()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                if (r4 == 0) goto L38
                if (r0 == 0) goto L38
                boolean r4 = r0.moveToFirst()     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                if (r4 == 0) goto L38
                int r4 = r2.getInt(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                if (r4 != 0) goto L37
                int r4 = r0.getInt(r3)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L53
                if (r4 == 0) goto L38
            L37:
                r1 = r3
            L38:
                if (r2 == 0) goto L3d
                r2.close()     // Catch: java.lang.Exception -> L3d
            L3d:
                if (r0 == 0) goto L5b
            L3f:
                r0.close()     // Catch: java.lang.Exception -> L5b
                goto L5b
            L43:
                r1 = move-exception
                goto L47
            L45:
                r1 = move-exception
                r2 = r0
            L47:
                if (r2 == 0) goto L4c
                r2.close()     // Catch: java.lang.Exception -> L4c
            L4c:
                if (r0 == 0) goto L51
                r0.close()     // Catch: java.lang.Exception -> L51
            L51:
                throw r1
            L52:
                r2 = r0
            L53:
                if (r2 == 0) goto L58
                r2.close()     // Catch: java.lang.Exception -> L58
            L58:
                if (r0 == 0) goto L5b
                goto L3f
            L5b:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.C0020c.j():boolean");
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.f2309f = true;
            this.f2489h = this.f2305b;
            this.k.clear();
            this.k.put("qt", this.f2306c);
            this.k.put("req", this.f2307d);
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            if (z && this.j != null) {
                new e(this).start();
            } else {
                this.f2309f = false;
                c();
            }
        }

        void b() throws Throwable {
            if (!d() || this.f2309f) {
                return;
            }
            c.this.f2291g.e();
        }
    }

    c(h hVar) {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        this.f2285a = hVar;
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase2 = null;
        try {
            File file = new File(this.f2285a.c(), "ofl_location.db");
            if (!file.exists()) {
                file.createNewFile();
            }
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused) {
            sQLiteDatabaseOpenOrCreateDatabase = null;
        }
        this.f2292h = sQLiteDatabaseOpenOrCreateDatabase;
        SQLiteDatabase sQLiteDatabase = this.f2292h;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
                this.f2292h.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY,x DOUBLE,y DOUBLE,r INTEGER,cl DOUBLE,timestamp INTEGER, frequency INTEGER DEFAULT 0);");
            } catch (Exception unused2) {
            }
        }
        try {
            File file2 = new File(this.f2285a.c(), "ofl_statistics.db");
            if (!file2.exists()) {
                file2.createNewFile();
            }
            sQLiteDatabaseOpenOrCreateDatabase2 = SQLiteDatabase.openOrCreateDatabase(file2, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused3) {
        }
        this.i = sQLiteDatabaseOpenOrCreateDatabase2;
        SQLiteDatabase sQLiteDatabase2 = this.i;
        if (sQLiteDatabase2 != null) {
            try {
                sQLiteDatabase2.execSQL("CREATE TABLE IF NOT EXISTS AP (id LONG PRIMARY KEY, originid VARCHAR(15), frequency INTEGER DEFAULT 0);");
                this.i.execSQL("CREATE TABLE IF NOT EXISTS CL (id LONG PRIMARY KEY, originid VARCHAR(40), frequency INTEGER DEFAULT 0);");
            } catch (Exception unused4) {
            }
        }
    }

    private double a(double d2, double d3, double d4, double d5) {
        double d6 = d5 - d3;
        double d7 = d4 - d2;
        double radians = Math.toRadians(d2);
        Math.toRadians(d3);
        double radians2 = Math.toRadians(d4);
        Math.toRadians(d5);
        double radians3 = Math.toRadians(d6);
        double radians4 = Math.toRadians(d7) / 2.0d;
        double d8 = radians3 / 2.0d;
        double dSin = (Math.sin(radians4) * Math.sin(radians4)) + (Math.cos(radians) * Math.cos(radians2) * Math.sin(d8) * Math.sin(d8));
        return Math.atan2(Math.sqrt(dSin), Math.sqrt(1.0d - dSin)) * 2.0d * 6378137.0d;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int a(java.util.ArrayList<com.baidu.location.d.c.a> r22, double r23) {
        /*
            r21 = this;
            r0 = r22
            int r1 = r22.size()
            r2 = 0
            if (r1 != 0) goto La
            return r2
        La:
            r1 = r2
        Lb:
            int r3 = r22.size()
            r4 = 3
            r5 = 1
            if (r3 < r4) goto L87
            r3 = 0
            r6 = r3
            r8 = r6
            r3 = r2
        L18:
            int r4 = r22.size()
            if (r3 >= r4) goto L33
            java.lang.Object r4 = r0.get(r3)
            com.baidu.location.d.c$a r4 = (com.baidu.location.d.c.a) r4
            double r10 = r4.f2293a
            double r6 = r6 + r10
            java.lang.Object r4 = r0.get(r3)
            com.baidu.location.d.c$a r4 = (com.baidu.location.d.c.a) r4
            double r10 = r4.f2294b
            double r8 = r8 + r10
            int r3 = r3 + 1
            goto L18
        L33:
            int r3 = r22.size()
            double r3 = (double) r3
            double r3 = r6 / r3
            int r6 = r22.size()
            double r6 = (double) r6
            double r6 = r8 / r6
            r8 = -4616189618054758400(0xbff0000000000000, double:-1.0)
            r10 = -1
            r19 = r8
            r9 = r10
            r8 = r2
        L48:
            int r10 = r22.size()
            if (r8 >= r10) goto L73
            java.lang.Object r10 = r0.get(r8)
            com.baidu.location.d.c$a r10 = (com.baidu.location.d.c.a) r10
            double r13 = r10.f2294b
            java.lang.Object r10 = r0.get(r8)
            com.baidu.location.d.c$a r10 = (com.baidu.location.d.c.a) r10
            double r11 = r10.f2293a
            r10 = r21
            r17 = r11
            r11 = r6
            r15 = r13
            r13 = r3
            double r10 = r10.a(r11, r13, r15, r17)
            int r12 = (r10 > r19 ? 1 : (r10 == r19 ? 0 : -1))
            if (r12 <= 0) goto L70
            r9 = r8
            r19 = r10
        L70:
            int r8 = r8 + 1
            goto L48
        L73:
            int r3 = (r19 > r23 ? 1 : (r19 == r23 ? 0 : -1))
            if (r3 <= 0) goto L87
            if (r9 < 0) goto L87
            int r3 = r22.size()
            if (r9 >= r3) goto L87
            int r1 = r1 + 1
            r0.remove(r9)
            r3 = r1
            r1 = r5
            goto L89
        L87:
            r3 = r1
            r1 = r2
        L89:
            if (r1 == r5) goto L8c
            return r3
        L8c:
            r1 = r3
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.a(java.util.ArrayList, double):int");
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.location.BDLocation a(java.lang.Long r20) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 239
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.a(java.lang.Long):com.baidu.location.BDLocation");
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0334 A[EXC_TOP_SPLITTER, PHI: r12 r19 r25 r35 r36 r37
  0x0334: PHI (r12v3 double) = (r12v2 double), (r12v22 double) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE]
  0x0334: PHI (r19v4 double) = (r19v3 double), (r19v15 double) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE]
  0x0334: PHI (r25v2 int) = (r25v1 int), (r25v16 int) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE]
  0x0334: PHI (r35v5 android.database.Cursor) = (r35v4 android.database.Cursor), (r35v25 android.database.Cursor) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE]
  0x0334: PHI (r36v2 int) = (r36v1 int), (r36v11 int) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE]
  0x0334: PHI (r37v4 com.baidu.location.BDLocation) = (r37v3 com.baidu.location.BDLocation), (r37v20 com.baidu.location.BDLocation) binds: [B:142:0x0332, B:133:0x0316] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0137 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x014a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:190:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0296 A[PHI: r1
  0x0296: PHI (r1v25 int) = (r1v23 int), (r1v24 int) binds: [B:95:0x0294, B:98:0x029b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.baidu.location.BDLocation a(java.util.LinkedHashMap<java.lang.String, java.lang.Integer> r41, com.baidu.location.BDLocation r42, int r43) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 857
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.a(java.util.LinkedHashMap, com.baidu.location.BDLocation, int):com.baidu.location.BDLocation");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(BDLocation bDLocation, BDLocation bDLocation2, BDLocation bDLocation3, String str, Long l) {
        if (bDLocation == null || bDLocation.getLocType() != 161) {
            return;
        }
        if (bDLocation2 != null && bDLocation.getNetworkLocationType() != null && bDLocation.getNetworkLocationType().equals("cl") && a(bDLocation2.getLatitude(), bDLocation2.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) > 300.0d) {
            String str2 = String.format(Locale.US, "UPDATE CL SET cl = 0 WHERE id = %d;", l);
            String str3 = String.format(Locale.US, "INSERT OR REPLACE INTO CL VALUES (%d,\"%s\",%d);", l, str, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH));
            try {
                this.f2292h.execSQL(str2);
                this.i.execSQL(str3);
            } catch (Exception unused) {
            }
        }
        if (bDLocation3 == null || bDLocation.getNetworkLocationType() == null || !bDLocation.getNetworkLocationType().equals("wf") || a(bDLocation3.getLatitude(), bDLocation3.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude()) <= 100.0d) {
            return;
        }
        try {
            String str4 = String.format("UPDATE AP SET cl = 0 WHERE id In (%s);", this.j.toString());
            String str5 = String.format("INSERT OR REPLACE INTO AP VALUES %s;", this.k.toString());
            this.f2292h.execSQL(str4);
            this.i.execSQL(str5);
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, Long l, BDLocation bDLocation) {
        if (str != null) {
            try {
                if (bDLocation != null) {
                    this.f2292h.execSQL(String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", l));
                } else {
                    String str2 = String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",0);", l, str);
                    String str3 = String.format(Locale.US, "UPDATE CL SET frequency=frequency+1 WHERE id = %d;", l);
                    this.i.execSQL(str2);
                    this.i.execSQL(str3);
                }
            } catch (Exception unused) {
            }
            if (this.p) {
                try {
                    this.i.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO CL VALUES (%d,\"%s\",%d);", l, str, Integer.valueOf(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH)));
                } catch (Exception unused2) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2, String str3) {
        this.f2290f.a(str, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(LinkedHashMap<String, Integer> linkedHashMap) {
        if (linkedHashMap == null || linkedHashMap.size() <= 0) {
            return;
        }
        this.j = new StringBuffer();
        this.k = new StringBuffer();
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        ConcurrentHashMap<Long, Integer> concurrentHashMap = this.m;
        if (concurrentHashMap != null && concurrentHashMap.keySet() != null) {
            boolean z = true;
            boolean z2 = true;
            for (Long l : this.m.keySet()) {
                try {
                    if (this.l.contains(l)) {
                        if (z) {
                            z = false;
                        } else {
                            this.j.append(',');
                            this.k.append(',');
                        }
                        this.j.append(l);
                        String str = this.n.get(l);
                        StringBuffer stringBuffer3 = this.k;
                        stringBuffer3.append('(');
                        stringBuffer3.append(l);
                        stringBuffer3.append(',');
                        stringBuffer3.append(Typography.quote);
                        stringBuffer3.append(str);
                        stringBuffer3.append(Typography.quote);
                        stringBuffer3.append(',');
                        stringBuffer3.append(BuglyStrategy.a.MAX_USERDATA_VALUE_LENGTH);
                        stringBuffer3.append(')');
                    } else {
                        String str2 = this.n.get(l);
                        if (z2) {
                            z2 = false;
                        } else {
                            stringBuffer.append(',');
                            stringBuffer2.append(',');
                        }
                        stringBuffer.append(l);
                        stringBuffer2.append('(');
                        stringBuffer2.append(l);
                        stringBuffer2.append(',');
                        stringBuffer2.append(Typography.quote);
                        stringBuffer2.append(str2);
                        stringBuffer2.append(Typography.quote);
                        stringBuffer2.append(",0)");
                    }
                } catch (Exception unused) {
                }
            }
        }
        try {
            this.f2292h.execSQL(String.format(Locale.US, "UPDATE AP SET frequency=frequency+1 WHERE id IN(%s)", this.j.toString()));
        } catch (Exception unused2) {
        }
        StringBuffer stringBuffer4 = this.o;
        if (stringBuffer4 != null && stringBuffer4.length() > 0) {
            if (stringBuffer2.length() > 0) {
                stringBuffer2.append(AppInfo.DELIM);
            }
            stringBuffer2.append(this.o);
        }
        try {
            String str3 = String.format("INSERT OR IGNORE INTO AP VALUES %s;", stringBuffer2.toString());
            String str4 = String.format("UPDATE AP SET frequency=frequency+1 WHERE id in (%s);", stringBuffer.toString());
            if (stringBuffer2.length() > 0) {
                this.i.execSQL(str3);
            }
            if (stringBuffer.length() > 0) {
                this.i.execSQL(str4);
            }
        } catch (Exception unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String[] strArr) {
        this.f2285a.l().a(strArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:56:0x0110  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0115  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    android.database.Cursor a(com.baidu.location.d.j.a r21) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.c.a(com.baidu.location.d.j$a):android.database.Cursor");
    }

    SQLiteDatabase a() {
        return this.i;
    }

    void b() throws Throwable {
        this.f2291g.b();
    }
}