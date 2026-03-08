package com.baidu.location.d;

import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.authorization.AuthorizationResponseParser;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.ido.life.util.DateUtil;
import java.util.HashMap;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2358d = String.format(Locale.US, "DELETE FROM LOG WHERE timestamp NOT IN (SELECT timestamp FROM LOG ORDER BY timestamp DESC LIMIT %d);", 3000);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2359e = String.format(Locale.US, "SELECT * FROM LOG ORDER BY timestamp DESC LIMIT %d;", 3);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SQLiteDatabase f2361b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f2360a = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final a f2362c = new a(this);

    private class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f2364b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private long f2365c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private String f2366d = null;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2367e = false;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2368f = false;
        private k q;

        a(k kVar) {
            this.q = kVar;
            this.k = new HashMap();
            this.f2364b = 0;
            this.f2365c = -1L;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (this.f2367e) {
                return;
            }
            this.f2366d = this.q.b();
            long j = this.f2365c;
            if (j != -1 && j + DateUtil.DAY <= System.currentTimeMillis()) {
                this.f2364b = 0;
                this.f2365c = -1L;
            }
            if (this.f2366d == null || this.f2364b >= 2) {
                return;
            }
            this.f2367e = true;
            ExecutorService executorServiceC = v.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, "https://ofloc.map.baidu.com/offline_loc");
            } else {
                c("https://ofloc.map.baidu.com/offline_loc");
            }
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.k.clear();
            this.k.put("qt", "ofbh");
            this.k.put("req", this.f2366d);
            this.f2489h = h.f2328b;
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            this.f2368f = false;
            if (z && this.j != null) {
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    if (jSONObject.has(AuthorizationResponseParser.ERROR) && jSONObject.getInt(AuthorizationResponseParser.ERROR) == 161) {
                        this.f2368f = true;
                    }
                } catch (Exception unused) {
                }
            }
            if (!this.f2368f) {
                this.f2364b++;
                this.f2365c = System.currentTimeMillis();
            }
            this.q.a(this.f2368f);
            this.f2367e = false;
        }
    }

    k(SQLiteDatabase sQLiteDatabase) {
        this.f2361b = sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2 = this.f2361b;
        if (sQLiteDatabase2 == null || !sQLiteDatabase2.isOpen()) {
            return;
        }
        try {
            this.f2361b.execSQL("CREATE TABLE IF NOT EXISTS LOG(timestamp LONG PRIMARY KEY, log VARCHAR(4000))");
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        String str;
        if (z && (str = this.f2360a) != null) {
            String str2 = String.format("DELETE FROM LOG WHERE timestamp in (%s);", str);
            try {
                if (this.f2360a.length() > 0) {
                    this.f2361b.execSQL(str2);
                }
            } catch (Exception unused) {
            }
        }
        this.f2360a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:39:0x005d A[EXC_TOP_SPLITTER, PHI: r2 r3
  0x005d: PHI (r2v2 java.lang.String) = (r2v1 java.lang.String), (r2v7 java.lang.String) binds: [B:30:0x006a, B:21:0x005b] A[DONT_GENERATE, DONT_INLINE]
  0x005d: PHI (r3v4 android.database.Cursor) = (r3v3 android.database.Cursor), (r3v6 android.database.Cursor) binds: [B:30:0x006a, B:21:0x005b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String b() throws java.lang.Throwable {
        /*
            r7 = this;
            org.json.JSONArray r0 = new org.json.JSONArray
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r7.f2361b     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L69
            java.lang.String r4 = com.baidu.location.d.k.f2359e     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L69
            android.database.Cursor r3 = r3.rawQuery(r4, r2)     // Catch: java.lang.Throwable -> L61 java.lang.Exception -> L69
            if (r3 == 0) goto L5b
            int r4 = r3.getCount()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            if (r4 <= 0) goto L5b
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r4.<init>()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r3.moveToFirst()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
        L23:
            boolean r5 = r3.isAfterLast()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            if (r5 != 0) goto L48
            r5 = 1
            java.lang.String r5 = r3.getString(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r0.put(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            int r5 = r4.length()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            if (r5 == 0) goto L3c
            java.lang.String r5 = ","
            r4.append(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
        L3c:
            r5 = 0
            long r5 = r3.getLong(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r4.append(r5)     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r3.moveToNext()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            goto L23
        L48:
            java.lang.String r5 = "ofloc"
            r1.put(r5, r0)     // Catch: org.json.JSONException -> L52 java.lang.Throwable -> L59 java.lang.Exception -> L6a
            java.lang.String r0 = r1.toString()     // Catch: org.json.JSONException -> L52 java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r2 = r0
        L52:
            java.lang.String r0 = r4.toString()     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            r7.f2360a = r0     // Catch: java.lang.Throwable -> L59 java.lang.Exception -> L6a
            goto L5b
        L59:
            r0 = move-exception
            goto L63
        L5b:
            if (r3 == 0) goto L6d
        L5d:
            r3.close()     // Catch: java.lang.Exception -> L6d
            goto L6d
        L61:
            r0 = move-exception
            r3 = r2
        L63:
            if (r3 == 0) goto L68
            r3.close()     // Catch: java.lang.Exception -> L68
        L68:
            throw r0
        L69:
            r3 = r2
        L6a:
            if (r3 == 0) goto L6d
            goto L5d
        L6d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.k.b():java.lang.String");
    }

    void a() {
        this.f2362c.b();
    }

    void a(String str) {
        try {
            this.f2361b.execSQL(String.format(Locale.US, "INSERT OR IGNORE INTO LOG VALUES (%d,\"%s\");", Long.valueOf(System.currentTimeMillis()), Jni.encodeOfflineLocationUpdateRequest(str)));
            this.f2361b.execSQL(f2358d);
        } catch (Exception unused) {
        }
    }
}