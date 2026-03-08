package com.baidu.location.d;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.ido.life.util.DateUtil;
import com.realsil.sdk.dfu.DfuConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final double[] f2369b = {45.0d, 135.0d, 225.0d, 315.0d};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final h f2370a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final int f2371c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final SQLiteDatabase f2372d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f2373e = -1;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private int f2374f = -1;

    private static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private double f2375a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private double f2376b;

        private a(double d2, double d3) {
            this.f2375a = d2;
            this.f2376b = d3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    static abstract class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f2377a = new m("AREA", 0, "RGCAREA", "area", "addrv", 0, 1000);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final b f2378b = new n("ROAD", 1, "RGCROAD", "road", "addrv", 1000, 10000);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final b f2379c = new o("SITE", 2, "RGCSITE", "site", "addrv", 100, net.sqlcipher.database.SQLiteDatabase.SQLITE_MAX_LIKE_PATTERN_LENGTH);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final b f2380d = new p("POI", 3, "RGCPOI", "poi", "poiv", 1000, 5000);
        private static final /* synthetic */ b[] j = {f2377a, f2378b, f2379c, f2380d};

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private final int f2381e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private final String f2382f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final String f2383g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private final String f2384h;
        private final int i;

        private b(String str, int i, String str2, String str3, String str4, int i2, int i3) {
            this.f2382f = str2;
            this.f2383g = str3;
            this.f2384h = str4;
            this.f2381e = i2;
            this.i = i3;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a(int i, double d2, double d3) {
            HashSet<String> hashSet = new HashSet();
            hashSet.add(l.b(i, d2, d3));
            int i2 = this.f2381e;
            double d4 = ((double) i2) * 1.414d;
            if (i2 > 0) {
                for (int i3 = 0; i3 < l.f2369b.length; i3++) {
                    double[] dArrB = l.b(d3, d2, d4, l.f2369b[i3]);
                    hashSet.add(l.b(i, dArrB[1], dArrB[0]));
                }
            }
            StringBuffer stringBuffer = new StringBuffer();
            boolean z = true;
            for (String str : hashSet) {
                if (z) {
                    z = false;
                } else {
                    stringBuffer.append(',');
                }
                stringBuffer.append("\"");
                stringBuffer.append(str);
                stringBuffer.append("\"");
            }
            return String.format("SELECT * FROM %s WHERE gridkey IN (%s);", this.f2382f, stringBuffer.toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String a(JSONObject jSONObject) {
            Iterator<String> itKeys = jSONObject.keys();
            StringBuffer stringBuffer = new StringBuffer();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (stringBuffer.length() != 0) {
                    stringBuffer.append(AppInfo.DELIM);
                }
                stringBuffer.append("\"");
                stringBuffer.append(next);
                stringBuffer.append("\"");
            }
            return String.format(Locale.US, "DELETE FROM %s WHERE gridkey IN (%s)", this.f2382f, stringBuffer);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(StringBuffer stringBuffer, String str, String str2, int i) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(AppInfo.DELIM);
            }
            stringBuffer.append("(\"");
            stringBuffer.append(str);
            stringBuffer.append("\",\"");
            stringBuffer.append(str2);
            stringBuffer.append("\",");
            stringBuffer.append(i);
            stringBuffer.append(AppInfo.DELIM);
            stringBuffer.append(System.currentTimeMillis() / DateUtil.DAY);
            stringBuffer.append(")");
        }

        public static b valueOf(String str) {
            return (b) Enum.valueOf(b.class, str);
        }

        public static b[] values() {
            return (b[]) j.clone();
        }

        abstract List<String> a(JSONObject jSONObject, String str, int i);
    }

    l(h hVar, SQLiteDatabase sQLiteDatabase, int i) {
        this.f2370a = hVar;
        this.f2372d = sQLiteDatabase;
        this.f2371c = i;
        SQLiteDatabase sQLiteDatabase2 = this.f2372d;
        if (sQLiteDatabase2 == null || !sQLiteDatabase2.isOpen()) {
            return;
        }
        try {
            this.f2372d.execSQL("CREATE TABLE IF NOT EXISTS RGCAREA(gridkey VARCHAR(10) PRIMARY KEY, country VARCHAR(100),countrycode VARCHAR(100), province VARCHAR(100), city VARCHAR(100), citycode VARCHAR(100), district VARCHAR(100), timestamp INTEGER, version VARCHAR(50))");
            this.f2372d.execSQL("CREATE TABLE IF NOT EXISTS RGCROAD(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), x1 DOUBLE, y1 DOUBLE, x2 DOUBLE, y2 DOUBLE)");
            this.f2372d.execSQL("CREATE TABLE IF NOT EXISTS RGCSITE(_id INTEGER PRIMARY KEY AUTOINCREMENT, gridkey VARCHAR(10), street VARCHAR(100), streetnumber VARCHAR(100), x DOUBLE, y DOUBLE)");
            this.f2372d.execSQL("CREATE TABLE IF NOT EXISTS RGCPOI(pid VARCHAR(50) PRIMARY KEY , gridkey VARCHAR(10), name VARCHAR(100), type VARCHAR(50), x DOUBLE, y DOUBLE, rank INTEGER)");
            this.f2372d.execSQL("CREATE TABLE IF NOT EXISTS RGCUPDATE(gridkey VARCHAR(10), version VARCHAR(50), type INTEGER, timestamp INTEGER, PRIMARY KEY(gridkey, type))");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private double a(double d2, double d3, double d4, double d5, double d6, double d7) {
        double d8 = d6 - d4;
        double d9 = d2 - d4;
        double d10 = d7 - d5;
        double d11 = d3 - d5;
        double d12 = (d8 * d9) + (d10 * d11);
        if (d12 <= 0.0d) {
            return Math.sqrt((d9 * d9) + (d11 * d11));
        }
        double d13 = (d8 * d8) + (d10 * d10);
        if (d12 >= d13) {
            double d14 = d2 - d6;
            double d15 = d3 - d7;
            return Math.sqrt((d14 * d14) + (d15 * d15));
        }
        double d16 = d12 / d13;
        double d17 = d2 - (d4 + (d8 * d16));
        double d18 = (d5 + (d10 * d16)) - d3;
        return Math.sqrt((d17 * d17) + (d18 * d18));
    }

    private static int a(int i, int i2) {
        double d2;
        int i3;
        if (100 > i2) {
            d2 = -0.1d;
            i3 = DfuConstants.DFU_UPLOAD_IMAGE_TIMEOUT;
        } else if (500 > i2) {
            d2 = -0.75d;
            i3 = 55500;
        } else {
            d2 = -0.5d;
            i3 = 0;
        }
        return ((int) ((d2 * ((double) i2)) + ((double) i3))) + i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(int i, double d2, double d3) {
        double d4;
        a aVar;
        int i2 = i * 5;
        char[] cArr = new char[i + 1];
        a aVar2 = new a(90.0d, -90.0d);
        a aVar3 = new a(180.0d, -180.0d);
        int i3 = 1;
        boolean z = true;
        int i4 = 0;
        for (int i5 = 1; i5 <= i2; i5++) {
            if (z) {
                d4 = d2;
                aVar = aVar3;
            } else {
                d4 = d3;
                aVar = aVar2;
            }
            double d5 = (aVar.f2376b + aVar.f2375a) / 2.0d;
            i4 <<= i3;
            if (((int) (d4 * 1000000.0d)) > ((int) (d5 * 1000000.0d))) {
                aVar.f2376b = d5;
                i4 |= 1;
            } else {
                aVar.f2375a = d5;
            }
            if (i5 % 5 == 0) {
                i3 = 1;
                cArr[(i5 / 5) - 1] = "0123456789bcdefghjkmnpqrstuvwxyz".charAt(i4);
                i4 = 0;
            } else {
                i3 = 1;
            }
            z = !z;
        }
        cArr[i] = 0;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i6 = 0; i6 < i; i6++) {
            stringBuffer.append(cArr[i6]);
        }
        return stringBuffer.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double[] b(double d2, double d3, double d4, double d5) {
        double radians = Math.toRadians(d2);
        double radians2 = Math.toRadians(d3);
        double radians3 = Math.toRadians(d5);
        double d6 = d4 / 6378137.0d;
        double dAsin = Math.asin((Math.sin(radians) * Math.cos(d6)) + (Math.cos(radians) * Math.sin(d6) * Math.cos(radians3)));
        return new double[]{Math.toDegrees(dAsin), Math.toDegrees(radians2 + Math.atan2(Math.sin(radians3) * Math.sin(d6) * Math.cos(radians), Math.cos(d6) - (Math.sin(radians) * Math.sin(dAsin))))};
    }

    private double c(double d2, double d3, double d4, double d5) {
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x01e4  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01f4  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0202  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x0216  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0227  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0249  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01dd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:158:0x007b A[EXC_TOP_SPLITTER, PHI: r18 r21 r26
  0x007b: PHI (r18v1 java.lang.String) = (r18v3 java.lang.String), (r18v6 java.lang.String) binds: [B:35:0x0099, B:23:0x0079] A[DONT_GENERATE, DONT_INLINE]
  0x007b: PHI (r21v1 java.lang.String) = (r21v11 java.lang.String), (r21v14 java.lang.String) binds: [B:35:0x0099, B:23:0x0079] A[DONT_GENERATE, DONT_INLINE]
  0x007b: PHI (r26v3 android.database.Cursor) = (r26v6 android.database.Cursor), (r26v10 android.database.Cursor) binds: [B:35:0x0099, B:23:0x0079] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:164:0x01c1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0139 A[EXC_TOP_SPLITTER, PHI: r15 r21 r24
  0x0139: PHI (r15v3 ??) = (r15v4 ??), (r15v6 ??) binds: [B:70:0x0152, B:58:0x0137] A[DONT_GENERATE, DONT_INLINE]
  0x0139: PHI (r21v3 java.lang.String) = (r21v4 java.lang.String), (r21v6 java.lang.String) binds: [B:70:0x0152, B:58:0x0137] A[DONT_GENERATE, DONT_INLINE]
  0x0139: PHI (r24v3 android.database.Cursor) = (r24v4 android.database.Cursor), (r24v7 android.database.Cursor) binds: [B:70:0x0152, B:58:0x0137] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:182:0x009e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x016e A[Catch: all -> 0x01c5, Exception -> 0x01c7, TryCatch #17 {all -> 0x01c5, blocks: (B:75:0x0168, B:77:0x016e, B:79:0x0174, B:80:0x017e, B:81:0x0188, B:82:0x0192, B:83:0x019c, B:84:0x01a6), top: B:169:0x0168 }] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ba  */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.baidu.location.Address$Builder] */
    /* JADX WARN: Type inference failed for: r0v15, types: [com.baidu.location.Address$Builder] */
    /* JADX WARN: Type inference failed for: r0v16, types: [com.baidu.location.Address$Builder] */
    /* JADX WARN: Type inference failed for: r0v17, types: [com.baidu.location.Address$Builder] */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r0v33, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v36 */
    /* JADX WARN: Type inference failed for: r0v37 */
    /* JADX WARN: Type inference failed for: r0v38 */
    /* JADX WARN: Type inference failed for: r0v39 */
    /* JADX WARN: Type inference failed for: r0v40 */
    /* JADX WARN: Type inference failed for: r0v41 */
    /* JADX WARN: Type inference failed for: r0v42 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /* JADX WARN: Type inference failed for: r11v10, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r11v9, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r15v11, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r15v12 */
    /* JADX WARN: Type inference failed for: r15v13, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v14, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r15v15 */
    /* JADX WARN: Type inference failed for: r15v16 */
    /* JADX WARN: Type inference failed for: r15v17 */
    /* JADX WARN: Type inference failed for: r15v18 */
    /* JADX WARN: Type inference failed for: r15v19 */
    /* JADX WARN: Type inference failed for: r15v2 */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    /* JADX WARN: Type inference failed for: r15v6 */
    /* JADX WARN: Type inference failed for: r15v8 */
    /* JADX WARN: Type inference failed for: r15v9 */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v16, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v33 */
    /* JADX WARN: Type inference failed for: r1v34 */
    /* JADX WARN: Type inference failed for: r1v35 */
    /* JADX WARN: Type inference failed for: r1v36 */
    /* JADX WARN: Type inference failed for: r1v37 */
    /* JADX WARN: Type inference failed for: r1v38 */
    /* JADX WARN: Type inference failed for: r1v39 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v15 */
    /* JADX WARN: Type inference failed for: r2v16 */
    /* JADX WARN: Type inference failed for: r2v17 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v22, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v27 */
    /* JADX WARN: Type inference failed for: r2v28 */
    /* JADX WARN: Type inference failed for: r2v29 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r3v10, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v11 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v20 */
    /* JADX WARN: Type inference failed for: r3v23, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v30 */
    /* JADX WARN: Type inference failed for: r3v31 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v8 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v15 */
    /* JADX WARN: Type inference failed for: r4v18, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v19 */
    /* JADX WARN: Type inference failed for: r4v20 */
    /* JADX WARN: Type inference failed for: r4v21 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r5v8 */
    /* JADX WARN: Type inference failed for: r5v9 */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.baidu.location.Address$Builder] */
    /* JADX WARN: Type inference failed for: r7v1, types: [com.baidu.location.Address$Builder] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    com.baidu.location.Address a(double r35, double r37) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 661
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.l.a(double, double):com.baidu.location.Address");
    }

    void a(JSONObject jSONObject) {
        SQLiteDatabase sQLiteDatabase = this.f2372d;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            return;
        }
        try {
            this.f2372d.beginTransaction();
            for (b bVar : b.values()) {
                if (jSONObject.has(bVar.f2383g)) {
                    String string = jSONObject.has(bVar.f2384h) ? jSONObject.getString(bVar.f2384h) : "";
                    ArrayList arrayList = new ArrayList();
                    JSONObject jSONObject2 = jSONObject.getJSONObject(bVar.f2383g);
                    arrayList.add(bVar.a(jSONObject2));
                    arrayList.addAll(bVar.a(jSONObject2, string, bVar.i));
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        this.f2372d.execSQL((String) it.next());
                    }
                }
            }
            this.f2372d.setTransactionSuccessful();
            this.f2373e = -1;
            this.f2374f = -1;
        } catch (Exception unused) {
        } catch (Throwable th) {
            try {
                this.f2372d.endTransaction();
            } catch (Exception unused2) {
            }
            throw th;
        }
        try {
            this.f2372d.endTransaction();
        } catch (Exception unused3) {
        }
    }

    boolean a() throws Throwable {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursorRawQuery;
        if (this.f2370a.l().l() && this.f2374f == -1 && this.f2373e == -1 && (sQLiteDatabase = this.f2372d) != null && sQLiteDatabase.isOpen()) {
            Cursor cursorRawQuery2 = null;
            try {
                cursorRawQuery = this.f2372d.rawQuery("SELECT COUNT(*) FROM RGCSITE;", null);
                try {
                    cursorRawQuery.moveToFirst();
                    this.f2374f = cursorRawQuery.getInt(0);
                    cursorRawQuery2 = this.f2372d.rawQuery("SELECT COUNT(*) FROM RGCAREA;", null);
                    cursorRawQuery2.moveToFirst();
                    this.f2373e = cursorRawQuery2.getInt(0);
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Exception unused) {
                        }
                    }
                } catch (Exception unused2) {
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Exception unused3) {
                        }
                    }
                    if (cursorRawQuery2 != null) {
                    }
                    if (this.f2374f != 0) {
                    }
                } catch (Throwable th) {
                    th = th;
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (cursorRawQuery2 == null) {
                        throw th;
                    }
                    try {
                        cursorRawQuery2.close();
                        throw th;
                    } catch (Exception unused5) {
                        throw th;
                    }
                }
            } catch (Exception unused6) {
                cursorRawQuery = null;
            } catch (Throwable th2) {
                th = th2;
                cursorRawQuery = null;
            }
            if (cursorRawQuery2 != null) {
                try {
                    cursorRawQuery2.close();
                } catch (Exception unused7) {
                }
            }
        }
        return this.f2374f != 0 && this.f2373e == 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x008d A[EXC_TOP_SPLITTER, PHI: r11 r13
  0x008d: PHI (r11v4 android.database.Cursor) = (r11v3 android.database.Cursor), (r11v5 android.database.Cursor) binds: [B:29:0x009f, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE]
  0x008d: PHI (r13v2 com.baidu.location.Poi) = (r13v1 com.baidu.location.Poi), (r13v6 com.baidu.location.Poi) binds: [B:29:0x009f, B:17:0x008b] A[DONT_GENERATE, DONT_INLINE], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    java.util.List<com.baidu.location.Poi> b(double r18, double r20) throws java.lang.Throwable {
        /*
            r17 = this;
            r10 = r17
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            com.baidu.location.d.l$b r1 = com.baidu.location.d.l.b.f2380d
            int r2 = r10.f2371c
            r3 = r18
            r5 = r20
            java.lang.String r1 = com.baidu.location.d.l.b.a(r1, r2, r3, r5)
            r2 = 0
            android.database.sqlite.SQLiteDatabase r3 = r10.f2372d     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L9d
            android.database.Cursor r11 = r3.rawQuery(r1, r2)     // Catch: java.lang.Throwable -> L95 java.lang.Exception -> L9d
            boolean r1 = r11.moveToFirst()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L93
            if (r1 == 0) goto L8a
            r12 = 0
            r13 = r2
            r14 = r12
        L23:
            boolean r1 = r11.isAfterLast()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            if (r1 != 0) goto L8b
            java.lang.String r15 = r11.getString(r12)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r1 = 2
            java.lang.String r16 = r11.getString(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r1 = 4
            double r8 = r11.getDouble(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r1 = 5
            double r6 = r11.getDouble(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r1 = 6
            int r4 = r11.getInt(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r1 = r17
            r2 = r20
            r12 = r4
            r4 = r18
            double r1 = r1.c(r2, r4, r6, r8)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            com.baidu.location.d.l$b r3 = com.baidu.location.d.l.b.f2380d     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            int r3 = com.baidu.location.d.l.b.d(r3)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            double r3 = (double) r3     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 >= 0) goto L85
            com.baidu.location.Poi r3 = new com.baidu.location.Poi     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            java.lang.String r4 = new java.lang.String     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            byte[] r5 = r15.getBytes()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            byte[] r5 = com.baidu.android.bbalbs.common.a.b.a(r5)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            java.lang.String r5 = new java.lang.String     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            byte[] r6 = r16.getBytes()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            byte[] r6 = com.baidu.android.bbalbs.common.a.b.a(r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r6 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            r3.<init>(r4, r5, r6)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            float r1 = (float) r1     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            int r1 = java.lang.Math.round(r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            int r1 = a(r12, r1)     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            if (r1 <= r14) goto L85
            r14 = r1
            r13 = r3
        L85:
            r11.moveToNext()     // Catch: java.lang.Throwable -> L91 java.lang.Exception -> L9f
            r12 = 0
            goto L23
        L8a:
            r13 = r2
        L8b:
            if (r11 == 0) goto La2
        L8d:
            r11.close()     // Catch: java.lang.Exception -> La2
            goto La2
        L91:
            r0 = move-exception
            goto L97
        L93:
            r13 = r2
            goto L9f
        L95:
            r0 = move-exception
            r11 = r2
        L97:
            if (r11 == 0) goto L9c
            r11.close()     // Catch: java.lang.Exception -> L9c
        L9c:
            throw r0
        L9d:
            r11 = r2
            r13 = r11
        L9f:
            if (r11 == 0) goto La2
            goto L8d
        La2:
            if (r13 == 0) goto La7
            r0.add(r13)
        La7:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.l.b(double, double):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0221 A[Catch: Exception -> 0x0224, TRY_ENTER, TRY_LEAVE, TryCatch #14 {Exception -> 0x0224, blocks: (B:106:0x0221, B:86:0x01f2), top: B:132:0x0020 }] */
    /* JADX WARN: Removed duplicated region for block: B:109:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0213 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:0x020e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x021c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:154:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0196  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    org.json.JSONObject b() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 566
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.d.l.b():org.json.JSONObject");
    }
}