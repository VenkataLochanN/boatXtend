package com.baidu.location.a;

import android.location.Location;
import com.google.android.material.timepicker.TimeModel;
import com.realsil.sdk.dfu.image.BinIndicator;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class w {
    private int B;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    long f2189a = 0;
    private a z;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ArrayList<String> f2182b = new ArrayList<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static ArrayList<String> f2183c = new ArrayList<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static ArrayList<String> f2184d = new ArrayList<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static String f2185e = com.baidu.location.g.j.f2498a + "/yo.dat";

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static String f2186f = com.baidu.location.g.j.f2498a + "/yoh.dat";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static String f2187g = com.baidu.location.g.j.f2498a + "/yom.dat";

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static String f2188h = com.baidu.location.g.j.f2498a + "/yol.dat";
    private static String i = com.baidu.location.g.j.f2498a + "/yor.dat";
    private static File j = null;
    private static int k = 8;
    private static int l = 8;
    private static int m = 16;
    private static int n = 1024;
    private static double o = 0.0d;
    private static double p = 0.1d;
    private static double q = 30.0d;
    private static double r = 100.0d;
    private static int s = 0;
    private static int t = 64;
    private static int u = 128;
    private static Location v = null;
    private static Location w = null;
    private static Location x = null;
    private static com.baidu.location.e.h y = null;
    private static w A = null;
    private static long C = 0;

    private class a extends com.baidu.location.g.e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f2190a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2191b = 0;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2192c = 0;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private ArrayList<String> f2194e = new ArrayList<>();

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private boolean f2195f = true;

        public a() {
            this.k = new HashMap();
        }

        @Override // com.baidu.location.g.e
        public void a() {
            Map<String, Object> map;
            StringBuilder sb;
            String str;
            this.f2489h = com.baidu.location.g.k.e();
            if (this.f2191b != 1) {
                this.f2489h = com.baidu.location.g.k.g();
            }
            this.i = 2;
            if (this.f2194e != null) {
                for (int i = 0; i < this.f2194e.size(); i++) {
                    if (this.f2191b == 1) {
                        map = this.k;
                        sb = new StringBuilder();
                        str = "cldc[";
                    } else {
                        map = this.k;
                        sb = new StringBuilder();
                        str = "cltr[";
                    }
                    sb.append(str);
                    sb.append(i);
                    sb.append("]");
                    map.put(sb.toString(), this.f2194e.get(i));
                }
                this.k.put("trtm", String.format(Locale.CHINA, TimeModel.NUMBER_FORMAT, Long.valueOf(System.currentTimeMillis())));
                if (this.f2191b != 1) {
                    this.k.put("qt", "cltrg");
                }
            }
        }

        @Override // com.baidu.location.g.e
        public void a(boolean z) {
            if (z && this.j != null) {
                ArrayList<String> arrayList = this.f2194e;
                if (arrayList != null) {
                    arrayList.clear();
                }
                try {
                    JSONObject jSONObject = new JSONObject(this.j);
                    if (jSONObject.has("ison") && jSONObject.getInt("ison") == 0) {
                        this.f2195f = false;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
            this.f2190a = false;
        }

        public synchronized void b() {
            ExecutorService executorServiceC;
            String strG;
            String strG2;
            if (this.f2190a) {
                return;
            }
            if (p > 4 && this.f2192c < p) {
                this.f2192c++;
                return;
            }
            this.f2192c = 0;
            this.f2190a = true;
            this.f2191b = 0;
            try {
                if (this.f2194e == null || this.f2194e.size() < 1) {
                    if (this.f2194e == null) {
                        this.f2194e = new ArrayList<>();
                    }
                    this.f2191b = 0;
                    int length = 0;
                    while (true) {
                        String strA = null;
                        String strB = this.f2191b < 2 ? w.b() : null;
                        if (strB == null && this.f2191b != 1 && this.f2195f) {
                            this.f2191b = 2;
                            try {
                                strA = g.a();
                            } catch (Exception unused) {
                            }
                        } else {
                            this.f2191b = 1;
                            strA = strB;
                        }
                        if (strA == null) {
                            break;
                        }
                        if (!strA.contains("err!")) {
                            this.f2194e.add(strA);
                            length += strA.length();
                            if (length >= com.baidu.location.g.a.i) {
                                break;
                            }
                        }
                    }
                }
                if (this.f2194e == null || this.f2194e.size() < 1) {
                    if (this.f2194e != null) {
                        this.f2194e.clear();
                    }
                    this.f2190a = false;
                    return;
                }
                if (this.f2191b != 1) {
                    executorServiceC = v.a().c();
                    if (executorServiceC != null) {
                        strG2 = com.baidu.location.g.k.g();
                        a(executorServiceC, strG2);
                    } else {
                        strG = com.baidu.location.g.k.g();
                        c(strG);
                    }
                } else {
                    executorServiceC = v.a().c();
                    if (executorServiceC != null) {
                        strG2 = com.baidu.location.g.k.f2505f;
                        a(executorServiceC, strG2);
                    } else {
                        strG = com.baidu.location.g.k.f2505f;
                        c(strG);
                    }
                }
            } catch (Exception unused2) {
                if (this.f2194e != null) {
                    this.f2194e.clear();
                }
            }
        }
    }

    private w() {
        this.z = null;
        this.B = 0;
        this.z = new a();
        this.B = 0;
    }

    private static synchronized int a(List<String> list, int i2) {
        if (list != null && i2 <= 256) {
            if (i2 >= 0) {
                try {
                    if (j == null) {
                        j = new File(f2185e);
                        if (!j.exists()) {
                            j = null;
                            return -2;
                        }
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(j, "rw");
                    if (randomAccessFile.length() < 1) {
                        randomAccessFile.close();
                        return -3;
                    }
                    long j2 = i2;
                    randomAccessFile.seek(j2);
                    int i3 = randomAccessFile.readInt();
                    int i4 = randomAccessFile.readInt();
                    int i5 = randomAccessFile.readInt();
                    int i6 = randomAccessFile.readInt();
                    long j3 = randomAccessFile.readLong();
                    long j4 = j3;
                    if (a(i3, i4, i5, i6, j3)) {
                        int i7 = 1;
                        if (i4 >= 1) {
                            byte[] bArr = new byte[n];
                            int i8 = k;
                            while (i8 > 0 && i4 > 0) {
                                long j5 = (((i3 + i4) - i7) % i5) * i6;
                                byte[] bArr2 = bArr;
                                long j6 = j4;
                                randomAccessFile.seek(j5 + j6);
                                int i9 = randomAccessFile.readInt();
                                if (i9 > 0 && i9 < i6) {
                                    randomAccessFile.read(bArr2, 0, i9);
                                    int i10 = i9 - 1;
                                    if (bArr2[i10] == 0) {
                                        list.add(new String(bArr2, 0, i10));
                                    }
                                }
                                i8--;
                                i4--;
                                j4 = j6;
                                bArr = bArr2;
                                i7 = 1;
                            }
                            randomAccessFile.seek(j2);
                            randomAccessFile.writeInt(i3);
                            randomAccessFile.writeInt(i4);
                            randomAccessFile.writeInt(i5);
                            randomAccessFile.writeInt(i6);
                            randomAccessFile.writeLong(j4);
                            randomAccessFile.close();
                            return k - i8;
                        }
                    }
                    randomAccessFile.close();
                    return -4;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return -5;
                }
            }
        }
        return -1;
    }

    public static synchronized w a() {
        if (A == null) {
            A = new w();
        }
        return A;
    }

    private static String a(int i2) {
        String str;
        ArrayList<String> arrayList;
        String str2 = null;
        if (i2 == 1) {
            str = f2186f;
            arrayList = f2182b;
        } else if (i2 == 2) {
            str = f2187g;
            arrayList = f2183c;
        } else {
            if (i2 == 3) {
                str = f2188h;
            } else {
                if (i2 != 4) {
                    return null;
                }
                str = i;
            }
            arrayList = f2184d;
        }
        if (arrayList == null) {
            return null;
        }
        if (arrayList.size() < 1) {
            a(str, arrayList);
        }
        synchronized (w.class) {
            int size = arrayList.size();
            if (size > 0) {
                int i3 = size - 1;
                try {
                    String str3 = arrayList.get(i3);
                    try {
                        arrayList.remove(i3);
                    } catch (Exception unused) {
                    }
                    str2 = str3;
                } catch (Exception unused2) {
                }
            }
        }
        return str2;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0060  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00df A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00cc A[EDGE_INSN: B:49:0x00cc->B:40:0x00cc BREAK  A[LOOP:0: B:28:0x005b->B:38:0x00c8], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void a(int r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 230
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.w.a(int, boolean):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x01eb  */
    /* JADX WARN: Removed duplicated region for block: B:107:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(com.baidu.location.e.a r10, com.baidu.location.e.h r11, android.location.Location r12, java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 494
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.w.a(com.baidu.location.e.a, com.baidu.location.e.h, android.location.Location, java.lang.String):void");
    }

    private static void a(String str) {
        e(str);
    }

    private static boolean a(int i2, int i3, int i4, int i5, long j2) {
        return i2 >= 0 && i2 < i4 && i3 >= 0 && i3 <= i4 && i4 >= 0 && i4 <= 1024 && i5 >= 128 && i5 <= 1024;
    }

    private static boolean a(Location location) {
        if (location == null) {
            return false;
        }
        Location location2 = w;
        if (location2 == null || v == null) {
            w = location;
            return true;
        }
        double dDistanceTo = location.distanceTo(location2);
        return ((double) location.distanceTo(v)) > (((((double) com.baidu.location.g.k.T) * dDistanceTo) * dDistanceTo) + (((double) com.baidu.location.g.k.U) * dDistanceTo)) + ((double) com.baidu.location.g.k.V);
    }

    private static boolean a(Location location, com.baidu.location.e.h hVar) {
        boolean z = false;
        if (location != null && hVar != null && hVar.f2420a != null && !hVar.f2420a.isEmpty()) {
            if (hVar.b(y)) {
                return false;
            }
            z = true;
            if (x == null) {
                x = location;
            }
        }
        return z;
    }

    public static boolean a(Location location, boolean z) {
        return com.baidu.location.e.e.a(v, location, z);
    }

    private static boolean a(String str, List<String> list) {
        File file = new File(str);
        if (!file.exists()) {
            return false;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(8L);
            int i2 = randomAccessFile.readInt();
            int i3 = randomAccessFile.readInt();
            int i4 = randomAccessFile.readInt();
            byte[] bArr = new byte[n];
            int i5 = l + 1;
            boolean z = false;
            while (i5 > 0 && i3 > 0) {
                if (i3 < i4) {
                    i4 = 0;
                }
                try {
                    randomAccessFile.seek(((i3 - 1) * i2) + 128);
                    int i6 = randomAccessFile.readInt();
                    if (i6 > 0 && i6 < i2) {
                        randomAccessFile.read(bArr, 0, i6);
                        int i7 = i6 - 1;
                        if (bArr[i7] == 0) {
                            list.add(0, new String(bArr, 0, i7));
                            z = true;
                        }
                    }
                    i5--;
                    i3--;
                } catch (Exception unused) {
                    return z;
                }
            }
            randomAccessFile.seek(12L);
            randomAccessFile.writeInt(i3);
            randomAccessFile.writeInt(i4);
            randomAccessFile.close();
            return z;
        } catch (Exception unused2) {
            return false;
        }
    }

    public static String b() {
        return f();
    }

    private static void b(String str) {
        e(str);
    }

    private static void c(String str) {
        e(str);
    }

    public static void d() {
        l = 0;
        a(1, false);
        a(2, false);
        a(3, false);
        l = 8;
    }

    private static void d(String str) {
        try {
            File file = new File(str);
            if (file.exists()) {
                return;
            }
            File file2 = new File(com.baidu.location.g.j.f2498a);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            if (!file.createNewFile()) {
                file = null;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(32);
            randomAccessFile.writeInt(2048);
            randomAccessFile.writeInt(BinIndicator.SubBinId.Bbpro.DSP_UI_PARAMETER_FILE);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    public static String e() {
        File file = new File(f2187g);
        String str = null;
        if (file.exists()) {
            try {
                RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                randomAccessFile.seek(20L);
                int i2 = randomAccessFile.readInt();
                if (i2 > 128) {
                    String str2 = "&p1=" + i2;
                    try {
                        randomAccessFile.seek(20L);
                        randomAccessFile.writeInt(0);
                        randomAccessFile.close();
                        return str2;
                    } catch (Exception unused) {
                        str = str2;
                    }
                } else {
                    randomAccessFile.close();
                }
            } catch (Exception unused2) {
            }
        }
        File file2 = new File(f2188h);
        if (file2.exists()) {
            try {
                RandomAccessFile randomAccessFile2 = new RandomAccessFile(file2, "rw");
                randomAccessFile2.seek(20L);
                int i3 = randomAccessFile2.readInt();
                if (i3 > 256) {
                    String str3 = "&p2=" + i3;
                    try {
                        randomAccessFile2.seek(20L);
                        randomAccessFile2.writeInt(0);
                        randomAccessFile2.close();
                        return str3;
                    } catch (Exception unused3) {
                        str = str3;
                    }
                } else {
                    randomAccessFile2.close();
                }
            } catch (Exception unused4) {
            }
        }
        File file3 = new File(i);
        if (file3.exists()) {
            try {
                RandomAccessFile randomAccessFile3 = new RandomAccessFile(file3, "rw");
                randomAccessFile3.seek(20L);
                int i4 = randomAccessFile3.readInt();
                if (i4 > 512) {
                    String str4 = "&p3=" + i4;
                    try {
                        randomAccessFile3.seek(20L);
                        randomAccessFile3.writeInt(0);
                        randomAccessFile3.close();
                        return str4;
                    } catch (Exception unused5) {
                        str = str4;
                    }
                } else {
                    randomAccessFile3.close();
                }
            } catch (Exception unused6) {
            }
        }
        return str;
    }

    private static synchronized void e(String str) {
        ArrayList<String> arrayList;
        if (str.contains("err!")) {
            return;
        }
        int i2 = com.baidu.location.g.k.q;
        if (i2 == 1) {
            arrayList = f2182b;
        } else if (i2 == 2) {
            arrayList = f2183c;
        } else if (i2 != 3) {
            return;
        } else {
            arrayList = f2184d;
        }
        if (arrayList == null) {
            return;
        }
        if (arrayList.size() <= m) {
            arrayList.add(str);
        }
        if (arrayList.size() >= m) {
            a(i2, false);
        }
        while (arrayList.size() > m) {
            arrayList.remove(0);
        }
    }

    private static String f() {
        String strA = null;
        for (int i2 = 1; i2 < 5; i2++) {
            strA = a(i2);
            if (strA != null) {
                return strA;
            }
        }
        a(f2184d, t);
        if (f2184d.size() > 0) {
            strA = f2184d.get(0);
            f2184d.remove(0);
        }
        if (strA != null) {
            return strA;
        }
        a(f2184d, s);
        if (f2184d.size() > 0) {
            strA = f2184d.get(0);
            f2184d.remove(0);
        }
        if (strA != null) {
            return strA;
        }
        a(f2184d, u);
        if (f2184d.size() <= 0) {
            return strA;
        }
        String str = f2184d.get(0);
        f2184d.remove(0);
        return str;
    }

    public void c() {
        if (com.baidu.location.e.i.j() && !com.baidu.location.g.k.b()) {
            this.z.b();
        }
    }
}