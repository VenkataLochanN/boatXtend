package com.baidu.location.d;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.net.wifi.ScanResult;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static a f2274b;
    private static final String l = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/";
    private static final String m = Environment.getExternalStorageDirectory().getPath() + "/baidu/tempdata/ls.db";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private String f2276c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f2277d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2278e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private double f2279f = 0.0d;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private double f2280g = 0.0d;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private double f2281h = 0.0d;
    private double i = 0.0d;
    private double j = 0.0d;
    private volatile boolean k = false;
    private Handler n = null;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f2275a = false;

    /* JADX INFO: renamed from: com.baidu.location.d.a$a, reason: collision with other inner class name */
    private class AsyncTaskC0019a extends AsyncTask<Boolean, Void, Boolean> {
        private AsyncTaskC0019a() {
        }

        /* synthetic */ AsyncTaskC0019a(a aVar, com.baidu.location.d.b bVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Boolean... boolArr) {
            if (boolArr.length != 4) {
                return false;
            }
            SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
            try {
                sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, (SQLiteDatabase.CursorFactory) null);
            } catch (Exception unused) {
            }
            if (sQLiteDatabaseOpenOrCreateDatabase == null) {
                return false;
            }
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            try {
                sQLiteDatabaseOpenOrCreateDatabase.beginTransaction();
                if (boolArr[0].booleanValue()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("delete from wof where ac < ");
                    sb.append(iCurrentTimeMillis - 35);
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb.toString());
                    } catch (Exception unused2) {
                    }
                }
                if (boolArr[1].booleanValue()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("delete from bdcltb09 where ac is NULL or ac < ");
                    sb2.append(iCurrentTimeMillis - 130);
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.execSQL(sb2.toString());
                    } catch (Exception unused3) {
                    }
                }
                sQLiteDatabaseOpenOrCreateDatabase.setTransactionSuccessful();
                sQLiteDatabaseOpenOrCreateDatabase.endTransaction();
                sQLiteDatabaseOpenOrCreateDatabase.close();
            } catch (Exception unused4) {
            }
            return true;
        }
    }

    private class b extends AsyncTask<Object, Void, Boolean> {
        private b() {
        }

        /* synthetic */ b(a aVar, com.baidu.location.d.b bVar) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean doInBackground(Object... objArr) {
            if (objArr.length == 4) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
                try {
                    sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(a.m, (SQLiteDatabase.CursorFactory) null);
                } catch (Exception unused) {
                }
                if (sQLiteDatabaseOpenOrCreateDatabase != null) {
                    try {
                        sQLiteDatabaseOpenOrCreateDatabase.beginTransaction();
                        a.this.a((String) objArr[0], (com.baidu.location.e.a) objArr[1], sQLiteDatabaseOpenOrCreateDatabase);
                        a.this.a((com.baidu.location.e.h) objArr[2], (BDLocation) objArr[3], sQLiteDatabaseOpenOrCreateDatabase);
                        sQLiteDatabaseOpenOrCreateDatabase.setTransactionSuccessful();
                        sQLiteDatabaseOpenOrCreateDatabase.endTransaction();
                        sQLiteDatabaseOpenOrCreateDatabase.close();
                    } catch (Exception unused2) {
                    }
                    a.this.k = false;
                    return true;
                }
            }
            a.this.k = false;
            return false;
        }
    }

    private a() {
        b();
    }

    public static synchronized a a() {
        if (f2274b == null) {
            f2274b = new a();
        }
        return f2274b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.baidu.location.e.h hVar, BDLocation bDLocation, SQLiteDatabase sQLiteDatabase) {
        int i;
        Iterator<ScanResult> it;
        double d2;
        double d3;
        boolean z;
        int i2;
        int i3;
        String str;
        if (bDLocation == null || bDLocation.getLocType() != 161) {
            return;
        }
        if (("wf".equals(bDLocation.getNetworkLocationType()) || bDLocation.getRadius() < 300.0f) && hVar.f2420a != null) {
            int iCurrentTimeMillis = (int) (System.currentTimeMillis() >> 28);
            System.currentTimeMillis();
            Iterator<ScanResult> it2 = hVar.f2420a.iterator();
            int i4 = 0;
            while (it2.hasNext()) {
                ScanResult next = it2.next();
                if (next.level != 0) {
                    int i5 = i4 + 1;
                    if (i5 > 6) {
                        return;
                    }
                    ContentValues contentValues = new ContentValues();
                    String strEncode2 = Jni.encode2(next.BSSID.replace(":", ""));
                    try {
                        Cursor cursorRawQuery = sQLiteDatabase.rawQuery("select * from wof where id = \"" + strEncode2 + "\";", null);
                        d2 = 0.0d;
                        if (cursorRawQuery == null || !cursorRawQuery.moveToFirst()) {
                            d3 = 0.0d;
                            z = false;
                            i2 = 0;
                            i3 = 0;
                        } else {
                            d2 = cursorRawQuery.getDouble(1) - 113.2349d;
                            d3 = cursorRawQuery.getDouble(2) - 432.1238d;
                            int i6 = cursorRawQuery.getInt(4);
                            i2 = cursorRawQuery.getInt(5);
                            i3 = i6;
                            z = true;
                        }
                        if (cursorRawQuery != null) {
                            cursorRawQuery.close();
                        }
                    } catch (Exception unused) {
                    }
                    if (z) {
                        if (i2 != 0) {
                            it = it2;
                            try {
                                float[] fArr = new float[1];
                                Location.distanceBetween(d3, d2, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                                if (fArr[0] > 1500.0f) {
                                    int i7 = i2 + 1;
                                    if (i7 <= 10 || i7 <= i3 * 3) {
                                        contentValues.put("cc", Integer.valueOf(i7));
                                    } else {
                                        contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                                        contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                                        contentValues.put("bc", (Integer) 1);
                                        contentValues.put("cc", (Integer) 1);
                                        contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                                    }
                                    i = i5;
                                    str = "wof";
                                } else {
                                    i = i5;
                                    int i8 = i3;
                                    double d4 = i8;
                                    try {
                                        int i9 = i8 + 1;
                                        str = "wof";
                                        double d5 = i9;
                                        double longitude = ((d2 * d4) + bDLocation.getLongitude()) / d5;
                                        double latitude = ((d3 * d4) + bDLocation.getLatitude()) / d5;
                                        contentValues.put("mktime", Double.valueOf(longitude + 113.2349d));
                                        contentValues.put("time", Double.valueOf(latitude + 432.1238d));
                                        contentValues.put("bc", Integer.valueOf(i9));
                                        contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                                    } catch (Exception unused2) {
                                    }
                                }
                                sQLiteDatabase.update(str, contentValues, "id = \"" + strEncode2 + "\"", null);
                            } catch (Exception unused3) {
                                i = i5;
                            }
                            i4 = i;
                            it2 = it;
                        }
                        i = i5;
                        i4 = i;
                        it2 = it;
                    } else {
                        contentValues.put("mktime", Double.valueOf(bDLocation.getLongitude() + 113.2349d));
                        contentValues.put("time", Double.valueOf(bDLocation.getLatitude() + 432.1238d));
                        contentValues.put("bc", (Integer) 1);
                        contentValues.put("cc", (Integer) 1);
                        contentValues.put("ac", Integer.valueOf(iCurrentTimeMillis));
                        contentValues.put("id", strEncode2);
                        sQLiteDatabase.insert("wof", null, contentValues);
                    }
                    it = it2;
                    i = i5;
                    i4 = i;
                    it2 = it;
                }
            }
        }
    }

    private void a(String str, SQLiteDatabase sQLiteDatabase) {
        if (str == null || str.equals(this.f2276c)) {
            return;
        }
        this.f2277d = false;
        Cursor cursorRawQuery = null;
        try {
            cursorRawQuery = sQLiteDatabase.rawQuery("select * from bdcltb09 where id = \"" + str + "\";", null);
            this.f2276c = str;
            if (cursorRawQuery.moveToFirst()) {
                this.f2280g = cursorRawQuery.getDouble(1) - 1235.4323d;
                this.f2279f = cursorRawQuery.getDouble(2) - 4326.0d;
                this.f2281h = cursorRawQuery.getDouble(3) - 2367.3217d;
                this.f2277d = true;
            }
            if (cursorRawQuery == null) {
                return;
            }
        } catch (Exception unused) {
            if (cursorRawQuery == null) {
                return;
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
        try {
            cursorRawQuery.close();
        } catch (Exception unused3) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ca A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00cb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(java.lang.String r20, com.baidu.location.e.a r21, android.database.sqlite.SQLiteDatabase r22) {
        /*
            Method dump skipped, instruction units count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.a(java.lang.String, com.baidu.location.e.a, android.database.sqlite.SQLiteDatabase):void");
    }

    private void a(String str, List<ScanResult> list) {
        this.f2277d = false;
        this.f2278e = false;
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(m, (SQLiteDatabase.CursorFactory) null);
        } catch (Throwable unused) {
        }
        if (str != null && sQLiteDatabaseOpenOrCreateDatabase != null) {
            a(str, sQLiteDatabaseOpenOrCreateDatabase);
        }
        if (list != null && sQLiteDatabaseOpenOrCreateDatabase != null) {
            a(list, sQLiteDatabaseOpenOrCreateDatabase);
        }
        if (sQLiteDatabaseOpenOrCreateDatabase == null || !sQLiteDatabaseOpenOrCreateDatabase.isOpen()) {
            return;
        }
        sQLiteDatabaseOpenOrCreateDatabase.close();
    }

    /* JADX WARN: Removed duplicated region for block: B:60:0x0178 A[Catch: all -> 0x0197, Exception -> 0x019e, TryCatch #4 {Exception -> 0x019e, all -> 0x0197, blocks: (B:20:0x005d, B:23:0x0089, B:25:0x0090, B:28:0x00b5, B:29:0x00bb, B:31:0x00bf, B:33:0x00e5, B:34:0x00e9, B:60:0x0178, B:37:0x00f9, B:44:0x0121, B:47:0x0135, B:49:0x014e, B:50:0x0158, B:53:0x015e, B:57:0x016b, B:62:0x0187), top: B:79:0x005d }] */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0177 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void a(java.util.List<android.net.wifi.ScanResult> r31, android.database.sqlite.SQLiteDatabase r32) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.a(java.util.List, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0028  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String b(boolean r17) {
        /*
            Method dump skipped, instruction units count: 209
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.a.b(boolean):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase;
        com.baidu.location.d.b bVar = null;
        try {
            sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(m, (SQLiteDatabase.CursorFactory) null);
        } catch (Exception unused) {
            sQLiteDatabaseOpenOrCreateDatabase = null;
        }
        if (sQLiteDatabaseOpenOrCreateDatabase == null) {
            return;
        }
        try {
            long jQueryNumEntries = DatabaseUtils.queryNumEntries(sQLiteDatabaseOpenOrCreateDatabase, "wof");
            long jQueryNumEntries2 = DatabaseUtils.queryNumEntries(sQLiteDatabaseOpenOrCreateDatabase, "bdcltb09");
            boolean z = jQueryNumEntries > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
            boolean z2 = jQueryNumEntries2 > DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT;
            sQLiteDatabaseOpenOrCreateDatabase.close();
            if (z || z2) {
                new AsyncTaskC0019a(this, bVar).execute(Boolean.valueOf(z), Boolean.valueOf(z2));
            }
        } catch (Exception unused2) {
        }
    }

    public BDLocation a(String str, List<ScanResult> list, boolean z) {
        if (!this.f2275a) {
            return new BDLocation("{\"result\":{\"time\":\"" + com.baidu.location.g.k.a() + "\",\"error\":\"67\"}}");
        }
        String str2 = "{\"result\":{\"time\":\"" + com.baidu.location.g.k.a() + "\",\"error\":\"67\"}}";
        try {
            a(str, list);
            String strB = b(true);
            if (strB != null) {
                str2 = strB;
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return new BDLocation(str2);
    }

    public BDLocation a(boolean z) {
        if (!this.f2275a) {
            return new BDLocation("{\"result\":{\"time\":\"" + com.baidu.location.g.k.a() + "\",\"error\":\"67\"}}");
        }
        com.baidu.location.e.a aVarF = com.baidu.location.e.b.a().f();
        String strG = (aVarF == null || !aVarF.e()) ? null : aVarF.g();
        com.baidu.location.e.h hVarO = com.baidu.location.e.i.a().o();
        BDLocation bDLocationA = hVarO != null ? a(strG, hVarO.f2420a, true) : null;
        if (bDLocationA != null && bDLocationA.getLocType() == 66) {
            StringBuffer stringBuffer = new StringBuffer(1024);
            stringBuffer.append(String.format(Locale.CHINA, "&ofl=%f|%f|%f", Double.valueOf(bDLocationA.getLatitude()), Double.valueOf(bDLocationA.getLongitude()), Float.valueOf(bDLocationA.getRadius())));
            if (hVarO != null && hVarO.a() > 0) {
                stringBuffer.append("&wf=");
                stringBuffer.append(hVarO.c(15));
            }
            if (aVarF != null) {
                stringBuffer.append(aVarF.h());
            }
            stringBuffer.append("&uptype=oldoff");
            stringBuffer.append(com.baidu.location.g.k.e(com.baidu.location.f.getServiceContext()));
            stringBuffer.append(com.baidu.location.g.b.a().a(false));
            stringBuffer.append(com.baidu.location.a.a.a().d());
            stringBuffer.toString();
        }
        return bDLocationA;
    }

    public void a(String str, com.baidu.location.e.a aVar, com.baidu.location.e.h hVar, BDLocation bDLocation) {
        if (this.f2275a) {
            boolean z = (aVar.b() && com.baidu.location.a.l.c().h()) ? false : true;
            boolean z2 = bDLocation == null || bDLocation.getLocType() != 161 || (!"wf".equals(bDLocation.getNetworkLocationType()) && bDLocation.getRadius() >= 300.0f);
            if (hVar.f2420a == null) {
                z2 = true;
            }
            if ((z && z2) || this.k) {
                return;
            }
            this.k = true;
            new b(this, null).execute(str, aVar, hVar, bDLocation);
        }
    }

    public void b() {
        try {
            File file = new File(l);
            File file2 = new File(m);
            if (!file.exists()) {
                file.mkdirs();
            }
            if (!file2.exists()) {
                file2.createNewFile();
            }
            if (file2.exists()) {
                SQLiteDatabase sQLiteDatabaseOpenOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file2, (SQLiteDatabase.CursorFactory) null);
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS bdcltb09(id CHAR(40) PRIMARY KEY,time DOUBLE,tag DOUBLE, type DOUBLE , ac INT);");
                sQLiteDatabaseOpenOrCreateDatabase.execSQL("CREATE TABLE IF NOT EXISTS wof(id CHAR(15) PRIMARY KEY,mktime DOUBLE,time DOUBLE, ac INT, bc INT, cc INT);");
                sQLiteDatabaseOpenOrCreateDatabase.setVersion(1);
                sQLiteDatabaseOpenOrCreateDatabase.close();
            }
            this.f2275a = true;
        } catch (Throwable unused) {
            this.f2275a = false;
        }
    }

    public void c() {
        if (this.n == null) {
            this.n = new Handler();
        }
        this.n.postDelayed(new com.baidu.location.d.b(this), 3000L);
    }
}