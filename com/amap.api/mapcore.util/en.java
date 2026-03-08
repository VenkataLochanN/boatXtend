package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: ShortArray.java */
/* JADX INFO: loaded from: classes.dex */
public class en {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public short[] f748a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f749b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f750c;

    public int hashCode() {
        return 42;
    }

    public en() {
        this(true, 16);
    }

    public en(boolean z, int i) {
        this.f750c = z;
        this.f748a = new short[i];
    }

    public void a(short s) {
        short[] sArrD = this.f748a;
        int i = this.f749b;
        if (i == sArrD.length) {
            sArrD = d(Math.max(8, (int) (i * 1.75f)));
        }
        int i2 = this.f749b;
        this.f749b = i2 + 1;
        sArrD[i2] = s;
    }

    public short a(int i) {
        if (i >= this.f749b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f749b);
        }
        return this.f748a[i];
    }

    public short b(int i) {
        int i2 = this.f749b;
        if (i >= i2) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f749b);
        }
        short[] sArr = this.f748a;
        short s = sArr[i];
        this.f749b = i2 - 1;
        if (this.f750c) {
            System.arraycopy(sArr, i + 1, sArr, i, this.f749b - i);
        } else {
            sArr[i] = sArr[this.f749b];
        }
        return s;
    }

    public void a() {
        this.f749b = 0;
    }

    public short[] c(int i) {
        int i2 = this.f749b + i;
        if (i2 > this.f748a.length) {
            d(Math.max(8, i2));
        }
        return this.f748a;
    }

    protected short[] d(int i) {
        short[] sArr = new short[i];
        System.arraycopy(this.f748a, 0, sArr, 0, Math.min(this.f749b, sArr.length));
        this.f748a = sArr;
        return sArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof en)) {
            return false;
        }
        en enVar = (en) obj;
        int i = this.f749b;
        if (i != enVar.f749b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f748a[i2] != enVar.f748a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f749b == 0) {
            return "[]";
        }
        short[] sArr = this.f748a;
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        sb.append((int) sArr[0]);
        for (int i = 1; i < this.f749b; i++) {
            sb.append(", ");
            sb.append((int) sArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}