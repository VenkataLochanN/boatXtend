package com.baidu.location.g;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static c f2468c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f2469a = "firll.dat";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f2470b = 3164;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2471d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2472e = 20;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    int f2473f = 40;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2474g = 60;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2475h = 80;
    int i = 100;

    private long a(int i) throws Throwable {
        RandomAccessFile randomAccessFile;
        String strJ = k.j();
        if (strJ == null) {
            return -1L;
        }
        try {
            randomAccessFile = new RandomAccessFile(strJ + File.separator + this.f2469a, "rw");
        } catch (Exception unused) {
            randomAccessFile = null;
        } catch (Throwable th) {
            th = th;
            randomAccessFile = null;
        }
        try {
            randomAccessFile.seek(i);
            int i2 = randomAccessFile.readInt();
            long j = randomAccessFile.readLong();
            if (i2 == randomAccessFile.readInt()) {
                try {
                    randomAccessFile.close();
                } catch (IOException unused2) {
                }
                return j;
            }
        } catch (Exception unused3) {
            if (randomAccessFile != null) {
            }
            return -1L;
        } catch (Throwable th2) {
            th = th2;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException unused4) {
                }
            }
            throw th;
        }
        try {
            randomAccessFile.close();
        } catch (IOException unused5) {
        }
        return -1L;
    }

    public static c a() {
        if (f2468c == null) {
            f2468c = new c();
        }
        return f2468c;
    }

    private void a(int i, long j) {
        String strJ = k.j();
        if (strJ == null) {
            return;
        }
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(strJ + File.separator + this.f2469a, "rw");
            randomAccessFile.seek(i);
            randomAccessFile.writeInt(this.f2470b);
            randomAccessFile.writeLong(j);
            randomAccessFile.writeInt(this.f2470b);
            randomAccessFile.close();
        } catch (Exception unused) {
        }
    }

    public void a(long j) {
        a(this.f2471d, j);
    }

    public long b() {
        return a(this.f2471d);
    }

    public void b(long j) {
        a(this.f2472e, j);
    }

    public long c() {
        return a(this.f2472e);
    }

    public void c(long j) {
        a(this.f2474g, j);
    }

    public long d() {
        return a(this.f2474g);
    }
}