package com.amap.api.mapcore.util;

/* JADX INFO: compiled from: IntArray.java */
/* JADX INFO: loaded from: classes.dex */
public class eb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int[] f712a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f714c;

    public int hashCode() {
        return 42;
    }

    public eb() {
        this(true, 16);
    }

    public eb(boolean z, int i) {
        this.f714c = z;
        this.f712a = new int[i];
    }

    public void a(int i) {
        int[] iArrD = this.f712a;
        int i2 = this.f713b;
        if (i2 == iArrD.length) {
            iArrD = d(Math.max(8, (int) (i2 * 1.75f)));
        }
        int i3 = this.f713b;
        this.f713b = i3 + 1;
        iArrD[i3] = i;
    }

    public int b(int i) {
        int i2 = this.f713b;
        if (i >= i2) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.f713b);
        }
        int[] iArr = this.f712a;
        int i3 = iArr[i];
        this.f713b = i2 - 1;
        if (this.f714c) {
            System.arraycopy(iArr, i + 1, iArr, i, this.f713b - i);
        } else {
            iArr[i] = iArr[this.f713b];
        }
        return i3;
    }

    public void a() {
        this.f713b = 0;
    }

    public int[] c(int i) {
        int i2 = this.f713b + i;
        if (i2 > this.f712a.length) {
            d(Math.max(8, i2));
        }
        return this.f712a;
    }

    protected int[] d(int i) {
        int[] iArr = new int[i];
        System.arraycopy(this.f712a, 0, iArr, 0, Math.min(this.f713b, iArr.length));
        this.f712a = iArr;
        return iArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof eb)) {
            return false;
        }
        eb ebVar = (eb) obj;
        int i = this.f713b;
        if (i != ebVar.f713b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.f712a[i2] != ebVar.f712a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.f713b == 0) {
            return "[]";
        }
        int[] iArr = this.f712a;
        StringBuilder sb = new StringBuilder(32);
        sb.append('[');
        sb.append(iArr[0]);
        for (int i = 1; i < this.f713b; i++) {
            sb.append(", ");
            sb.append(iArr[i]);
        }
        sb.append(']');
        return sb.toString();
    }
}