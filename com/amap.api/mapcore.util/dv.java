package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: EarClippingTriangulator.java */
/* JADX INFO: loaded from: classes.dex */
public class dv {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private short[] f684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double[] f685c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f686d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final en f683a = new en();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final eb f687e = new eb();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final en f688f = new en();

    public en a(double[] dArr) {
        return a(dArr, 0, dArr.length);
    }

    public en a(double[] dArr, int i, int i2) {
        this.f685c = dArr;
        int i3 = i2 / 2;
        this.f686d = i3;
        int i4 = i / 2;
        en enVar = this.f683a;
        enVar.a();
        enVar.c(i3);
        enVar.f749b = i3;
        short[] sArr = enVar.f748a;
        this.f684b = sArr;
        int i5 = i3 - 1;
        for (int i6 = 0; i6 < i3; i6++) {
            sArr[i6] = (short) ((i4 + i5) - i6);
        }
        eb ebVar = this.f687e;
        ebVar.a();
        ebVar.c(i3);
        for (int i7 = 0; i7 < i3; i7++) {
            ebVar.a(a(i7));
        }
        en enVar2 = this.f688f;
        enVar2.a();
        enVar2.c(Math.max(0, i3 - 2) * 3);
        a();
        return enVar2;
    }

    private void a() {
        int i;
        int[] iArr = this.f687e.f712a;
        while (true) {
            i = this.f686d;
            if (i <= 3) {
                break;
            }
            int iB = b();
            c(iB);
            int iD = d(iB);
            if (iB == this.f686d) {
                iB = 0;
            }
            iArr[iD] = a(iD);
            iArr[iB] = a(iB);
        }
        if (i == 3) {
            en enVar = this.f688f;
            short[] sArr = this.f684b;
            enVar.a(sArr[0]);
            enVar.a(sArr[1]);
            enVar.a(sArr[2]);
        }
    }

    private int a(int i) {
        short[] sArr = this.f684b;
        int i2 = sArr[d(i)] * 2;
        int i3 = sArr[i] * 2;
        int i4 = sArr[e(i)] * 2;
        double[] dArr = this.f685c;
        return a(dArr[i2], dArr[i2 + 1], dArr[i3], dArr[i3 + 1], dArr[i4], dArr[i4 + 1]);
    }

    private int b() {
        int i = this.f686d;
        for (int i2 = 0; i2 < i; i2++) {
            if (b(i2)) {
                return i2;
            }
        }
        int[] iArr = this.f687e.f712a;
        for (int i3 = 0; i3 < i; i3++) {
            if (iArr[i3] != -1) {
                return i3;
            }
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean b(int i) {
        int i2;
        int[] iArr = this.f687e.f712a;
        if (iArr[i] == -1) {
            return false;
        }
        int iD = d(i);
        int iE = e(i);
        short[] sArr = this.f684b;
        int i3 = sArr[iD] * 2;
        int i4 = sArr[i] * 2;
        int i5 = sArr[iE] * 2;
        double[] dArr = this.f685c;
        double d2 = dArr[i3];
        int i6 = 1;
        double d3 = dArr[i3 + 1];
        double d4 = dArr[i4];
        double d5 = dArr[i4 + 1];
        double d6 = dArr[i5];
        double d7 = dArr[i5 + 1];
        int iE2 = e(iE);
        while (iE2 != iD) {
            if (iArr[iE2] != i6) {
                int i7 = sArr[iE2] * 2;
                double d8 = dArr[i7];
                double d9 = dArr[i7 + i6];
                i2 = i6;
                if (a(d6, d7, d2, d3, d8, d9) >= 0 && a(d2, d3, d4, d5, d8, d9) >= 0 && a(d4, d5, d6, d7, d8, d9) >= 0) {
                    return false;
                }
            } else {
                i2 = i6;
            }
            iE2 = e(iE2);
            i6 = i2;
        }
        return i6;
    }

    private void c(int i) {
        short[] sArr = this.f684b;
        en enVar = this.f688f;
        enVar.a(sArr[d(i)]);
        enVar.a(sArr[i]);
        enVar.a(sArr[e(i)]);
        this.f683a.b(i);
        this.f687e.b(i);
        this.f686d--;
    }

    private int d(int i) {
        if (i == 0) {
            i = this.f686d;
        }
        return i - 1;
    }

    private int e(int i) {
        return (i + 1) % this.f686d;
    }

    private static int a(double d2, double d3, double d4, double d5, double d6, double d7) {
        return (int) Math.signum((d2 * (d7 - d5)) + (d4 * (d3 - d7)) + (d6 * (d5 - d3)));
    }
}