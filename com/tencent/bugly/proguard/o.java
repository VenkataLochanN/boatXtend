package com.tencent.bugly.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: BUGLY */
/* JADX INFO: loaded from: classes3.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static o f5714a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static p f5715b = null;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static boolean f5716c = false;

    private o(Context context, List<com.tencent.bugly.a> list) {
        f5715b = new p(context, list);
    }

    public static synchronized o a(Context context, List<com.tencent.bugly.a> list) {
        if (f5714a == null) {
            f5714a = new o(context, list);
        }
        return f5714a;
    }

    public static synchronized o a() {
        return f5714a;
    }

    public final long a(String str, ContentValues contentValues, n nVar, boolean z) {
        return a(str, contentValues, (n) null);
    }

    public final Cursor a(String str, String[] strArr, String str2, String[] strArr2, n nVar, boolean z) {
        return a(false, str, strArr, str2, (String[]) null, (String) null, (String) null, (String) null, (String) null, (n) null, true);
    }

    public final Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, n nVar, boolean z2) {
        if (!z2) {
            a aVar = new a(3, nVar);
            aVar.a(false, str, strArr, str2, strArr2, null, null, str5, str6);
            x.a().a(aVar);
            return null;
        }
        return a(false, str, strArr, str2, strArr2, null, null, str5, str6, nVar);
    }

    public final int a(String str, String str2, String[] strArr, n nVar, boolean z) {
        return a(str, str2, (String[]) null, (n) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized long a(String str, ContentValues contentValues, n nVar) {
        long j;
        j = -1;
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase != null && contentValues != null) {
                long jReplace = writableDatabase.replace(str, "_id", contentValues);
                if (jReplace >= 0) {
                    y.c("[Database] insert %s success.", str);
                } else {
                    y.d("[Database] replace %s error.", str);
                }
                j = jReplace;
            }
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (nVar != null) {
                }
            } finally {
                if (nVar != null) {
                    Long.valueOf(-1L);
                }
            }
        }
        return j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized Cursor a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6, n nVar) {
        Cursor cursorQuery;
        cursorQuery = null;
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase != null) {
                cursorQuery = writableDatabase.query(z, str, strArr, str2, strArr2, str3, str4, str5, str6);
            }
        } finally {
        }
        return cursorQuery;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized int a(String str, String str2, String[] strArr, n nVar) {
        int iDelete;
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            iDelete = writableDatabase != null ? writableDatabase.delete(str, str2, strArr) : 0;
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (nVar != null) {
                }
            } finally {
                if (nVar != null) {
                    Integer.valueOf(0);
                }
            }
        }
        return iDelete;
    }

    public final boolean a(int i, String str, byte[] bArr, n nVar, boolean z) {
        if (!z) {
            a aVar = new a(4, null);
            aVar.a(i, str, bArr);
            x.a().a(aVar);
            return true;
        }
        return a(i, str, bArr, (n) null);
    }

    public final Map<String, byte[]> a(int i, n nVar, boolean z) {
        return a(i, (n) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, byte[] bArr, n nVar) {
        boolean zB = false;
        try {
            q qVar = new q();
            qVar.f5729a = i;
            qVar.f5734f = str;
            qVar.f5733e = System.currentTimeMillis();
            qVar.f5735g = bArr;
            zB = b(qVar);
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (nVar != null) {
                }
            } finally {
                if (nVar != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return zB;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, byte[]> a(int i, n nVar) {
        HashMap map = null;
        try {
            List<q> listC = c(i);
            if (listC == null) {
                return null;
            }
            HashMap map2 = new HashMap();
            try {
                for (q qVar : listC) {
                    byte[] bArr = qVar.f5735g;
                    if (bArr != null) {
                        map2.put(qVar.f5734f, bArr);
                    }
                }
                return map2;
            } catch (Throwable th) {
                th = th;
                map = map2;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (y.a(th)) {
            return map;
        }
        th.printStackTrace();
        return map;
    }

    public final synchronized boolean a(q qVar) {
        ContentValues contentValuesC;
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesC = c(qVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_lr", "_id", contentValuesC);
            if (jReplace < 0) {
                return false;
            }
            y.c("[Database] insert %s success.", "t_lr");
            qVar.f5729a = jReplace;
            return true;
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    private synchronized boolean b(q qVar) {
        ContentValues contentValuesD;
        if (qVar == null) {
            return false;
        }
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase == null || (contentValuesD = d(qVar)) == null) {
                return false;
            }
            long jReplace = writableDatabase.replace("t_pf", "_id", contentValuesD);
            if (jReplace < 0) {
                return false;
            }
            y.c("[Database] insert %s success.", "t_pf");
            qVar.f5729a = jReplace;
            return true;
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                return false;
            } finally {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a8 A[Catch: all -> 0x00b1, TRY_LEAVE, TryCatch #0 {all -> 0x00b1, blocks: (B:35:0x00a2, B:37:0x00a8), top: B:49:0x00a2, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad A[Catch: all -> 0x00ba, TRY_ENTER, TryCatch #3 {, blocks: (B:3:0x0001, B:14:0x0032, B:31:0x009c, B:39:0x00ad, B:42:0x00b4, B:43:0x00b7, B:35:0x00a2, B:37:0x00a8), top: B:55:0x0001, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final synchronized java.util.List<com.tencent.bugly.proguard.q> a(int r11) {
        /*
            r10 = this;
            monitor-enter(r10)
            com.tencent.bugly.proguard.p r0 = com.tencent.bugly.proguard.o.f5715b     // Catch: java.lang.Throwable -> Lba
            android.database.sqlite.SQLiteDatabase r0 = r0.getWritableDatabase()     // Catch: java.lang.Throwable -> Lba
            r9 = 0
            if (r0 == 0) goto Lb8
            if (r11 < 0) goto L21
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L1c
            java.lang.String r2 = "_tp = "
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L1c
            r1.append(r11)     // Catch: java.lang.Throwable -> L1c
            java.lang.String r11 = r1.toString()     // Catch: java.lang.Throwable -> L1c
            r4 = r11
            goto L22
        L1c:
            r11 = move-exception
            r0 = r11
            r11 = r9
            goto La2
        L21:
            r4 = r9
        L22:
            java.lang.String r2 = "t_lr"
            r3 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L1c
            if (r11 != 0) goto L37
            if (r11 == 0) goto L35
            r11.close()     // Catch: java.lang.Throwable -> Lba
        L35:
            monitor-exit(r10)
            return r9
        L37:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La1
            r1.<init>()     // Catch: java.lang.Throwable -> La1
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La1
            r2.<init>()     // Catch: java.lang.Throwable -> La1
        L41:
            boolean r3 = r11.moveToNext()     // Catch: java.lang.Throwable -> La1
            r4 = 0
            if (r3 == 0) goto L72
            com.tencent.bugly.proguard.q r3 = a(r11)     // Catch: java.lang.Throwable -> La1
            if (r3 == 0) goto L52
            r2.add(r3)     // Catch: java.lang.Throwable -> La1
            goto L41
        L52:
            java.lang.String r3 = "_id"
            int r3 = r11.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L6a
            long r5 = r11.getLong(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = " or _id"
            r1.append(r3)     // Catch: java.lang.Throwable -> L6a
            java.lang.String r3 = " = "
            r1.append(r3)     // Catch: java.lang.Throwable -> L6a
            r1.append(r5)     // Catch: java.lang.Throwable -> L6a
            goto L41
        L6a:
            java.lang.String r3 = "[Database] unknown id."
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> La1
            com.tencent.bugly.proguard.y.d(r3, r4)     // Catch: java.lang.Throwable -> La1
            goto L41
        L72:
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La1
            int r3 = r1.length()     // Catch: java.lang.Throwable -> La1
            if (r3 <= 0) goto L9a
            r3 = 4
            java.lang.String r1 = r1.substring(r3)     // Catch: java.lang.Throwable -> La1
            java.lang.String r3 = "t_lr"
            int r0 = r0.delete(r3, r1, r9)     // Catch: java.lang.Throwable -> La1
            java.lang.String r1 = "[Database] deleted %s illegal data %d"
            r3 = 2
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> La1
            java.lang.String r5 = "t_lr"
            r3[r4] = r5     // Catch: java.lang.Throwable -> La1
            r4 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> La1
            r3[r4] = r0     // Catch: java.lang.Throwable -> La1
            com.tencent.bugly.proguard.y.d(r1, r3)     // Catch: java.lang.Throwable -> La1
        L9a:
            if (r11 == 0) goto L9f
            r11.close()     // Catch: java.lang.Throwable -> Lba
        L9f:
            monitor-exit(r10)
            return r2
        La1:
            r0 = move-exception
        La2:
            boolean r1 = com.tencent.bugly.proguard.y.a(r0)     // Catch: java.lang.Throwable -> Lb1
            if (r1 != 0) goto Lab
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lb1
        Lab:
            if (r11 == 0) goto Lb8
            r11.close()     // Catch: java.lang.Throwable -> Lba
            goto Lb8
        Lb1:
            r0 = move-exception
            if (r11 == 0) goto Lb7
            r11.close()     // Catch: java.lang.Throwable -> Lba
        Lb7:
            throw r0     // Catch: java.lang.Throwable -> Lba
        Lb8:
            monitor-exit(r10)
            return r9
        Lba:
            r11 = move-exception
            monitor-exit(r10)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.proguard.o.a(int):java.util.List");
    }

    public final synchronized void a(List<q> list) {
        if (list != null) {
            if (list.size() != 0) {
                SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
                if (writableDatabase != null) {
                    StringBuilder sb = new StringBuilder();
                    for (q qVar : list) {
                        sb.append(" or _id");
                        sb.append(" = ");
                        sb.append(qVar.f5729a);
                    }
                    String string = sb.toString();
                    if (string.length() > 0) {
                        string = string.substring(4);
                    }
                    sb.setLength(0);
                    try {
                        y.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", string, null)));
                    } catch (Throwable th) {
                        if (y.a(th)) {
                            return;
                        }
                        th.printStackTrace();
                    }
                }
            }
        }
    }

    public final synchronized void b(int i) {
        String str;
        SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
        if (writableDatabase != null) {
            if (i >= 0) {
                try {
                    str = "_tp = " + i;
                } catch (Throwable th) {
                    if (y.a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            } else {
                str = null;
            }
            y.c("[Database] deleted %s data %d", "t_lr", Integer.valueOf(writableDatabase.delete("t_lr", str, null)));
        }
    }

    private static ContentValues c(q qVar) {
        if (qVar == null) {
            return null;
        }
        try {
            ContentValues contentValues = new ContentValues();
            if (qVar.f5729a > 0) {
                contentValues.put("_id", Long.valueOf(qVar.f5729a));
            }
            contentValues.put("_tp", Integer.valueOf(qVar.f5730b));
            contentValues.put("_pc", qVar.f5731c);
            contentValues.put("_th", qVar.f5732d);
            contentValues.put("_tm", Long.valueOf(qVar.f5733e));
            if (qVar.f5735g != null) {
                contentValues.put("_dt", qVar.f5735g);
            }
            return contentValues;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static q a(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.f5729a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.f5730b = cursor.getInt(cursor.getColumnIndex("_tp"));
            qVar.f5731c = cursor.getString(cursor.getColumnIndex("_pc"));
            qVar.f5732d = cursor.getString(cursor.getColumnIndex("_th"));
            qVar.f5733e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f5735g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private synchronized List<q> c(int i) {
        Cursor cursorQuery;
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase != null) {
                String str = "_id = " + i;
                cursorQuery = writableDatabase.query("t_pf", null, str, null, null, null, null);
                if (cursorQuery == null) {
                    return null;
                }
                try {
                    StringBuilder sb = new StringBuilder();
                    ArrayList arrayList = new ArrayList();
                    while (cursorQuery.moveToNext()) {
                        q qVarB = b(cursorQuery);
                        if (qVarB != null) {
                            arrayList.add(qVarB);
                        } else {
                            try {
                                String string = cursorQuery.getString(cursorQuery.getColumnIndex("_tp"));
                                sb.append(" or _tp");
                                sb.append(" = ");
                                sb.append(string);
                            } catch (Throwable unused) {
                                y.d("[Database] unknown id.", new Object[0]);
                            }
                        }
                    }
                    if (sb.length() > 0) {
                        sb.append(" and _id");
                        sb.append(" = ");
                        sb.append(i);
                        y.d("[Database] deleted %s illegal data %d.", "t_pf", Integer.valueOf(writableDatabase.delete("t_pf", str.substring(4), null)));
                    }
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th = th;
                    try {
                        if (!y.a(th)) {
                            th.printStackTrace();
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    } finally {
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            cursorQuery = null;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized boolean a(int i, String str, n nVar) {
        boolean z;
        String str2;
        z = false;
        try {
            SQLiteDatabase writableDatabase = f5715b.getWritableDatabase();
            if (writableDatabase != null) {
                if (ab.a(str)) {
                    str2 = "_id = " + i;
                } else {
                    str2 = "_id = " + i + " and _tp = \"" + str + "\"";
                }
                int iDelete = writableDatabase.delete("t_pf", str2, null);
                y.c("[Database] deleted %s data %d", "t_pf", Integer.valueOf(iDelete));
                if (iDelete > 0) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            try {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
                if (nVar != null) {
                }
            } finally {
                if (nVar != null) {
                    Boolean.valueOf(false);
                }
            }
        }
        return z;
    }

    private static ContentValues d(q qVar) {
        if (qVar != null && !ab.a(qVar.f5734f)) {
            try {
                ContentValues contentValues = new ContentValues();
                if (qVar.f5729a > 0) {
                    contentValues.put("_id", Long.valueOf(qVar.f5729a));
                }
                contentValues.put("_tp", qVar.f5734f);
                contentValues.put("_tm", Long.valueOf(qVar.f5733e));
                if (qVar.f5735g != null) {
                    contentValues.put("_dt", qVar.f5735g);
                }
                return contentValues;
            } catch (Throwable th) {
                if (!y.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    private static q b(Cursor cursor) {
        if (cursor == null) {
            return null;
        }
        try {
            q qVar = new q();
            qVar.f5729a = cursor.getLong(cursor.getColumnIndex("_id"));
            qVar.f5733e = cursor.getLong(cursor.getColumnIndex("_tm"));
            qVar.f5734f = cursor.getString(cursor.getColumnIndex("_tp"));
            qVar.f5735g = cursor.getBlob(cursor.getColumnIndex("_dt"));
            return qVar;
        } catch (Throwable th) {
            if (!y.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    /* JADX INFO: compiled from: BUGLY */
    class a extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f5717a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private n f5718b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f5719c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private ContentValues f5720d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f5721e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String[] f5722f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f5723g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String[] f5724h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private String[] n;
        private int o;
        private String p;
        private byte[] q;

        public a(int i, n nVar) {
            this.f5717a = i;
            this.f5718b = nVar;
        }

        public final void a(boolean z, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
            this.f5721e = z;
            this.f5719c = str;
            this.f5722f = strArr;
            this.f5723g = str2;
            this.f5724h = strArr2;
            this.i = str3;
            this.j = str4;
            this.k = str5;
            this.l = str6;
        }

        public final void a(int i, String str, byte[] bArr) {
            this.o = i;
            this.p = str;
            this.q = bArr;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            switch (this.f5717a) {
                case 1:
                    o.this.a(this.f5719c, this.f5720d, this.f5718b);
                    break;
                case 2:
                    o.this.a(this.f5719c, this.m, this.n, this.f5718b);
                    break;
                case 3:
                    Cursor cursorA = o.this.a(this.f5721e, this.f5719c, this.f5722f, this.f5723g, this.f5724h, this.i, this.j, this.k, this.l, this.f5718b);
                    if (cursorA != null) {
                        cursorA.close();
                    }
                    break;
                case 4:
                    o.this.a(this.o, this.p, this.q, this.f5718b);
                    break;
                case 5:
                    o.this.a(this.o, this.f5718b);
                    break;
                case 6:
                    o.this.a(this.o, this.p, this.f5718b);
                    break;
            }
        }
    }
}