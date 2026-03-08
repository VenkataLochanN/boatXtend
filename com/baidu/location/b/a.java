package com.baidu.location.b;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.baidu.location.BDLocation;
import com.baidu.location.Jni;
import com.baidu.location.a.v;
import com.baidu.location.g.k;
import com.bumptech.glide.load.Key;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static Object f2203b = new Object();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static a f2204c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final String f2205d = k.j() + "/gal.db";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static Lock f2206f = new ReentrantLock();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private SQLiteDatabase f2208e = null;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2209g = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    C0017a f2207a = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private Map<String, Integer> f2210h = new HashMap();
    private String i = null;
    private int j = -1;
    private String k = null;
    private double l = Double.MAX_VALUE;
    private double m = Double.MAX_VALUE;

    /* JADX INFO: renamed from: com.baidu.location.b.a$a, reason: collision with other inner class name */
    class C0017a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2211a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2212b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2213c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f2214d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        double f2215e;

        C0017a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            String strA;
            this.f2489h = "http://loc.map.baidu.com/gpsz";
            String str = String.format(Locale.CHINESE, "&is_vdr=1&x=%d&y=%d%s", Integer.valueOf(this.f2211a), Integer.valueOf(this.f2212b), com.baidu.location.g.b.a().c());
            String strEncode = Jni.encode(str);
            if (!strEncode.contains("err!")) {
                this.k.put("gpsz", strEncode);
                return;
            }
            try {
                strA = com.baidu.android.bbalbs.common.a.b.a(str.toString().getBytes(), Key.STRING_CHARSET_NAME);
            } catch (Exception unused) {
                strA = "err2!";
            }
            this.k.put("gpszb", strA);
        }

        public void a(double d2, double d3, double d4) {
            if (a.this.f2209g) {
                return;
            }
            double[] dArrCoorEncrypt = Jni.coorEncrypt(d2, d3, "gcj2wgs");
            this.f2211a = (int) Math.floor(dArrCoorEncrypt[0] * 100.0d);
            this.f2212b = (int) Math.floor(dArrCoorEncrypt[1] * 100.0d);
            this.f2213c = (int) Math.floor(d2 * 100.0d);
            this.f2214d = (int) Math.floor(d3 * 100.0d);
            this.f2215e = d4;
            a.this.f2209g = true;
            if (k.b()) {
                return;
            }
            b(v.a().c());
        }

        /* JADX WARN: Can't wrap try/catch for region: R(9:18|19|(1:21)(2:23|(1:25)(7:26|(1:28)(1:29)|30|85|31|(2:33|89)(1:90)|34))|22|30|85|31|(0)(0)|34) */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0121 A[Catch: Exception -> 0x0132, TRY_LEAVE, TryCatch #4 {Exception -> 0x0132, blocks: (B:31:0x00fc, B:33:0x0121), top: B:85:0x00fc }] */
        /* JADX WARN: Removed duplicated region for block: B:90:0x0132 A[SYNTHETIC] */
        @Override // com.baidu.location.g.e
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void a(boolean r26) {
            /*
                Method dump skipped, instruction units count: 556
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.a.C0017a.a(boolean):void");
        }
    }

    public static a a() {
        a aVar;
        synchronized (f2203b) {
            if (f2204c == null) {
                f2204c = new a();
            }
            aVar = f2204c;
        }
        return aVar;
    }

    private void a(double d2, double d3, double d4) {
        if (this.f2207a == null) {
            this.f2207a = new C0017a();
        }
        this.f2207a.a(d2, d3, d4);
    }

    public int a(BDLocation bDLocation) {
        double altitude;
        float radius;
        if (bDLocation != null) {
            radius = bDLocation.getRadius();
            altitude = bDLocation.getAltitude();
        } else {
            altitude = 0.0d;
            radius = 0.0f;
        }
        if (this.f2208e == null || radius <= 0.0f || altitude <= 0.0d || bDLocation == null) {
            return 0;
        }
        double d2 = a(bDLocation.getLongitude(), bDLocation.getLatitude())[0];
        if (d2 == Double.MAX_VALUE) {
            return 0;
        }
        double gpsSwiftRadius = Jni.getGpsSwiftRadius(radius, altitude, d2);
        if (gpsSwiftRadius > 50.0d) {
            return 3;
        }
        return gpsSwiftRadius > 20.0d ? 2 : 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:46:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0128  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0114 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public double[] a(double r20, double r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.b.a.a(double, double):double[]");
    }

    public void b() {
        try {
            File file = new File(f2205d);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.f2208e = SQLiteDatabase.openOrCreateDatabase(file, (SQLiteDatabase.CursorFactory) null);
                Cursor cursorRawQuery = this.f2208e.rawQuery("SELECT count(*) FROM sqlite_master WHERE type='table' AND name='galdata'", null);
                if (cursorRawQuery.moveToFirst()) {
                    if (cursorRawQuery.getInt(0) == 0) {
                        this.f2208e.execSQL("CREATE TABLE IF NOT EXISTS galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    } else {
                        this.f2208e.execSQL("DROP TABLE galdata");
                        this.f2208e.execSQL("CREATE TABLE galdata_new(id CHAR(40) PRIMARY KEY,aldata DOUBLE, sigma DOUBLE,tt INT);");
                    }
                    this.f2208e.execSQL("CREATE TABLE IF NOT EXISTS locStateData(id CHAR(40) PRIMARY KEY,state INT);");
                }
                this.f2208e.setVersion(1);
                cursorRawQuery.close();
            }
        } catch (Exception unused) {
            this.f2208e = null;
        }
    }

    public void c() {
        SQLiteDatabase sQLiteDatabase = this.f2208e;
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.close();
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f2208e = null;
                throw th;
            }
            this.f2208e = null;
        }
    }
}