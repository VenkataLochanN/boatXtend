package com.baidu.location.a;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static g f2091a = null;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static String f2092b = "Temp_in.dat";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static File f2093c = new File(com.baidu.location.g.j.f2498a, f2092b);

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static StringBuffer f2094d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static boolean f2095e = true;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static int f2096f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static int f2097g = 0;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static long f2098h = 0;
    private static long i = 0;
    private static long j = 0;
    private static double k = 0.0d;
    private static double l = 0.0d;
    private static int m = 0;
    private static int n = 0;
    private static int o = 0;

    public static String a() {
        RandomAccessFile randomAccessFile;
        int i2;
        int i3;
        int i4;
        int i5;
        File file = f2093c;
        if (file == null || !file.exists()) {
            return null;
        }
        try {
            randomAccessFile = new RandomAccessFile(f2093c, "rw");
            randomAccessFile.seek(0L);
            i2 = randomAccessFile.readInt();
            i3 = randomAccessFile.readInt();
            i4 = randomAccessFile.readInt();
        } catch (IOException unused) {
        }
        if (!a(i2, i3, i4)) {
            randomAccessFile.close();
            c();
            return null;
        }
        if (i3 != 0 && i3 != i4) {
            long j2 = ((long) (((i3 - 1) * 1024) + 12)) + 0;
            randomAccessFile.seek(j2);
            int i6 = randomAccessFile.readInt();
            byte[] bArr = new byte[i6];
            randomAccessFile.seek(j2 + 4);
            for (int i7 = 0; i7 < i6; i7++) {
                bArr[i7] = randomAccessFile.readByte();
            }
            String str = new String(bArr);
            int i8 = 1;
            if (i2 < com.baidu.location.g.k.af) {
                i5 = i3 + 1;
            } else {
                if (i3 != com.baidu.location.g.k.af) {
                    i8 = 1 + i3;
                }
                i5 = i8;
            }
            randomAccessFile.seek(4L);
            randomAccessFile.writeInt(i5);
            randomAccessFile.close();
            return str;
        }
        randomAccessFile.close();
        return null;
    }

    private static boolean a(int i2, int i3, int i4) {
        int i5;
        return i2 >= 0 && i2 <= com.baidu.location.g.k.af && i3 >= 0 && i3 <= (i5 = i2 + 1) && i4 >= 1 && i4 <= i5 && i4 <= com.baidu.location.g.k.af;
    }

    private static void b() {
        f2095e = true;
        f2094d = null;
        f2096f = 0;
        f2097g = 0;
        f2098h = 0L;
        i = 0L;
        j = 0L;
        k = 0.0d;
        l = 0.0d;
        m = 0;
        n = 0;
        o = 0;
    }

    private static boolean c() {
        if (f2093c.exists()) {
            f2093c.delete();
        }
        if (!f2093c.getParentFile().exists()) {
            f2093c.getParentFile().mkdirs();
        }
        try {
            f2093c.createNewFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(f2093c, "rw");
            randomAccessFile.seek(0L);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(0);
            randomAccessFile.writeInt(1);
            randomAccessFile.close();
            b();
            return f2093c.exists();
        } catch (IOException unused) {
            return false;
        }
    }
}