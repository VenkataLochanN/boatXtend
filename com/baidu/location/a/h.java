package com.baidu.location.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import com.baidu.location.Jni;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.ido.life.constants.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static Object f2099c = new Object();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static h f2100d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final String f2101e = com.baidu.location.g.k.j() + "/hst.db";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SQLiteDatabase f2104f = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2105g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    a f2102a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    a f2103b = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f2106h = null;
    private int i = -2;

    class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2108b = null;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2109c = null;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f2110d = true;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f2111e = false;

        a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            this.i = 1;
            this.f2489h = com.baidu.location.g.k.e();
            String strEncodeTp4 = Jni.encodeTp4(this.f2109c);
            this.f2109c = null;
            this.k.put("bloc", strEncodeTp4);
        }

        public void a(String str, String str2) {
            if (h.this.f2105g) {
                return;
            }
            h.this.f2105g = true;
            this.f2108b = str;
            this.f2109c = str2;
            ExecutorService executorServiceC = v.a().c();
            if (executorServiceC != null) {
                a(executorServiceC, com.baidu.location.g.k.f2505f);
            } else {
                c(com.baidu.location.g.k.f2505f);
            }
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            if (z && this.j != null) {
                try {
                    String str = this.j;
                    if (this.f2110d) {
                        JSONObject jSONObject = new JSONObject(str);
                        JSONObject jSONObject2 = jSONObject.has(FirebaseAnalytics.Param.CONTENT) ? jSONObject.getJSONObject(FirebaseAnalytics.Param.CONTENT) : null;
                        if (jSONObject2 != null && jSONObject2.has("imo")) {
                            Long lValueOf = Long.valueOf(jSONObject2.getJSONObject("imo").getString("mac"));
                            int i = jSONObject2.getJSONObject("imo").getInt("mv");
                            if (Jni.encode3(this.f2108b).longValue() == lValueOf.longValue()) {
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                                contentValues.put("hst", Integer.valueOf(i));
                                try {
                                    if (h.this.f2104f.update("hstdata", contentValues, "id = \"" + lValueOf + "\"", null) <= 0) {
                                        contentValues.put("id", lValueOf);
                                        h.this.f2104f.insert("hstdata", null, contentValues);
                                    }
                                } catch (Exception unused) {
                                }
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("mac", this.f2108b.getBytes());
                                bundle.putInt("hotspot", i);
                                h.this.a(bundle);
                            }
                        }
                    }
                } catch (Exception unused2) {
                }
            } else if (this.f2110d) {
                h.this.f();
            }
            if (this.k != null) {
                this.k.clear();
            }
            h.this.f2105g = false;
        }
    }

    public static h a() {
        h hVar;
        synchronized (f2099c) {
            if (f2100d == null) {
                f2100d = new h();
            }
            hVar = f2100d;
        }
        return hVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String a(boolean r5) {
        /*
            r4 = this;
            com.baidu.location.e.b r0 = com.baidu.location.e.b.a()
            com.baidu.location.e.a r0 = r0.f()
            com.baidu.location.e.i r1 = com.baidu.location.e.i.a()
            com.baidu.location.e.h r1 = r1.p()
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r3 = 1024(0x400, float:1.435E-42)
            r2.<init>(r3)
            if (r0 == 0) goto L26
            boolean r3 = r0.b()
            if (r3 == 0) goto L26
            java.lang.String r0 = r0.h()
            r2.append(r0)
        L26:
            if (r1 == 0) goto L36
            int r0 = r1.a()
            r3 = 1
            if (r0 <= r3) goto L36
            r0 = 15
            java.lang.String r0 = r1.a(r0)
            goto L48
        L36:
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()
            java.lang.String r0 = r0.m()
            if (r0 == 0) goto L4b
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()
            java.lang.String r0 = r0.m()
        L48:
            r2.append(r0)
        L4b:
            if (r5 == 0) goto L52
            java.lang.String r5 = "&imo=1"
            r2.append(r5)
        L52:
            com.baidu.location.g.b r5 = com.baidu.location.g.b.a()
            r0 = 0
            java.lang.String r5 = r5.a(r0)
            r2.append(r5)
            com.baidu.location.a.a r5 = com.baidu.location.a.a.a()
            java.lang.String r5 = r5.d()
            r2.append(r5)
            java.lang.String r5 = r2.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.h.a(boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Bundle bundle) {
        com.baidu.location.a.a.a().a(bundle, Constants.BindErrorCode.CONNECTED_FAILED_DISCOVER_SERVICE_FAILED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        Bundle bundle = new Bundle();
        bundle.putInt("hotspot", -1);
        a(bundle);
    }

    public void a(String str) {
        if (this.f2105g) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.has(FirebaseAnalytics.Param.CONTENT) ? jSONObject.getJSONObject(FirebaseAnalytics.Param.CONTENT) : null;
            if (jSONObject2 == null || !jSONObject2.has("imo")) {
                return;
            }
            Long lValueOf = Long.valueOf(jSONObject2.getJSONObject("imo").getString("mac"));
            int i = jSONObject2.getJSONObject("imo").getInt("mv");
            ContentValues contentValues = new ContentValues();
            contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
            contentValues.put("hst", Integer.valueOf(i));
            if (this.f2104f.update("hstdata", contentValues, "id = \"" + lValueOf + "\"", null) <= 0) {
                contentValues.put("id", lValueOf);
                this.f2104f.insert("hstdata", null, contentValues);
            }
        } catch (Exception unused) {
        }
    }

    public void b() {
        try {
            File file = new File(f2101e);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.f2104f = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                this.f2104f.execSQL("CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                this.f2104f.setVersion(1);
            }
        } catch (Exception unused) {
            this.f2104f = null;
        }
    }

    public void c() {
        SQLiteDatabase sQLiteDatabase = this.f2104f;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f2104f = null;
                throw th;
            }
            this.f2104f = null;
        }
    }

    public synchronized int d() {
        WifiInfo wifiInfoL;
        int i = -3;
        if (this.f2105g) {
            return -3;
        }
        try {
            if (com.baidu.location.e.i.j() && this.f2104f != null && (wifiInfoL = com.baidu.location.e.i.a().l()) != null && wifiInfoL.getBSSID() != null) {
                String strReplace = wifiInfoL.getBSSID().replace(":", "");
                Long lEncode3 = Jni.encode3(strReplace);
                if (this.f2106h == null || !strReplace.equals(this.f2106h) || this.i <= -2) {
                    Cursor cursorRawQuery = null;
                    try {
                        cursorRawQuery = this.f2104f.rawQuery("select * from hstdata where id = \"" + lEncode3 + "\";", null);
                        if (cursorRawQuery == null || !cursorRawQuery.moveToFirst()) {
                            i = -2;
                        } else {
                            i = cursorRawQuery.getInt(1);
                            this.f2106h = strReplace;
                            this.i = i;
                        }
                    } catch (Exception unused) {
                        if (cursorRawQuery != null) {
                        }
                    } catch (Throwable th) {
                        if (cursorRawQuery != null) {
                            try {
                                cursorRawQuery.close();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th;
                    }
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                    }
                } else {
                    i = this.i;
                }
            }
        } catch (Exception unused3) {
        }
        this.i = i;
        return i;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x008a A[EXC_TOP_SPLITTER, PHI: r2 r3
  0x008a: PHI (r2v2 boolean) = (r2v1 boolean), (r2v6 boolean) binds: [B:33:0x0097, B:25:0x0088] A[DONT_GENERATE, DONT_INLINE]
  0x008a: PHI (r3v3 android.database.Cursor) = (r3v2 android.database.Cursor), (r3v4 android.database.Cursor) binds: [B:33:0x0097, B:25:0x0088] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void e() {
        /*
            r10 = this;
            boolean r0 = r10.f2105g
            if (r0 == 0) goto L5
            return
        L5:
            boolean r0 = com.baidu.location.e.i.j()     // Catch: java.lang.Exception -> Lb6
            if (r0 == 0) goto Lb3
            android.database.sqlite.SQLiteDatabase r0 = r10.f2104f     // Catch: java.lang.Exception -> Lb6
            if (r0 == 0) goto Lb3
            com.baidu.location.e.i r0 = com.baidu.location.e.i.a()     // Catch: java.lang.Exception -> Lb6
            android.net.wifi.WifiInfo r0 = r0.l()     // Catch: java.lang.Exception -> Lb6
            if (r0 == 0) goto Lb3
            java.lang.String r1 = r0.getBSSID()     // Catch: java.lang.Exception -> Lb6
            if (r1 == 0) goto Lb3
            java.lang.String r0 = r0.getBSSID()     // Catch: java.lang.Exception -> Lb6
            java.lang.String r1 = ":"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.replace(r1, r2)     // Catch: java.lang.Exception -> Lb6
            java.lang.Long r1 = com.baidu.location.Jni.encode3(r0)     // Catch: java.lang.Exception -> Lb6
            r2 = 0
            r3 = 0
            r4 = 1
            android.database.sqlite.SQLiteDatabase r5 = r10.f2104f     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r6.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.String r7 = "select * from hstdata where id = \""
            r6.append(r7)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r6.append(r1)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.String r1 = "\";"
            r6.append(r1)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.String r1 = r6.toString()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            android.database.Cursor r3 = r5.rawQuery(r1, r3)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            if (r3 == 0) goto L87
            boolean r1 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            if (r1 == 0) goto L87
            int r1 = r3.getInt(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r5 = 2
            int r5 = r3.getInt(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 / r8
            long r8 = (long) r5     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            long r6 = r6 - r8
            r8 = 259200(0x3f480, double:1.28062E-318)
            int r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r5 <= 0) goto L70
            goto L87
        L70:
            android.os.Bundle r5 = new android.os.Bundle     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r5.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.String r6 = "mac"
            byte[] r7 = r0.getBytes()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r5.putByteArray(r6, r7)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            java.lang.String r6 = "hotspot"
            r5.putInt(r6, r1)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            r10.a(r5)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L95
            goto L88
        L87:
            r2 = r4
        L88:
            if (r3 == 0) goto L98
        L8a:
            r3.close()     // Catch: java.lang.Exception -> L98
            goto L98
        L8e:
            r0 = move-exception
            if (r3 == 0) goto L94
            r3.close()     // Catch: java.lang.Exception -> L94
        L94:
            throw r0     // Catch: java.lang.Exception -> Lb6
        L95:
            if (r3 == 0) goto L98
            goto L8a
        L98:
            if (r2 == 0) goto Lb6
            com.baidu.location.a.h$a r1 = r10.f2102a     // Catch: java.lang.Exception -> Lb6
            if (r1 != 0) goto La5
            com.baidu.location.a.h$a r1 = new com.baidu.location.a.h$a     // Catch: java.lang.Exception -> Lb6
            r1.<init>()     // Catch: java.lang.Exception -> Lb6
            r10.f2102a = r1     // Catch: java.lang.Exception -> Lb6
        La5:
            com.baidu.location.a.h$a r1 = r10.f2102a     // Catch: java.lang.Exception -> Lb6
            if (r1 == 0) goto Lb6
            com.baidu.location.a.h$a r1 = r10.f2102a     // Catch: java.lang.Exception -> Lb6
            java.lang.String r2 = r10.a(r4)     // Catch: java.lang.Exception -> Lb6
            r1.a(r0, r2)     // Catch: java.lang.Exception -> Lb6
            goto Lb6
        Lb3:
            r10.f()     // Catch: java.lang.Exception -> Lb6
        Lb6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.h.e():void");
    }
}