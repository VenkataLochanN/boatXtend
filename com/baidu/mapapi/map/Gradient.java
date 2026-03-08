package com.baidu.mapapi.map;

import android.graphics.Color;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class Gradient {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f2812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int[] f2813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float[] f2814c;

    private class a {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f2816b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f2817c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final float f2818d;

        private a(int i, int i2, float f2) {
            this.f2816b = i;
            this.f2817c = i2;
            this.f2818d = f2;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        if (iArr == null || fArr == null) {
            throw new IllegalArgumentException("BDMapSDKException: colors and startPoints should not be null");
        }
        if (iArr.length != fArr.length) {
            throw new IllegalArgumentException("BDMapSDKException: colors and startPoints should be same length");
        }
        if (iArr.length == 0) {
            throw new IllegalArgumentException("BDMapSDKException: No colors have been defined");
        }
        for (int i2 = 1; i2 < fArr.length; i2++) {
            if (fArr[i2] <= fArr[i2 - 1]) {
                throw new IllegalArgumentException("BDMapSDKException: startPoints should be in increasing order");
            }
        }
        this.f2812a = i;
        this.f2813b = new int[iArr.length];
        this.f2814c = new float[fArr.length];
        System.arraycopy(iArr, 0, this.f2813b, 0, iArr.length);
        System.arraycopy(fArr, 0, this.f2814c, 0, fArr.length);
    }

    private static int a(int i, int i2, float f2) {
        int iAlpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f2) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        if (fArr[0] - fArr2[0] > 180.0f) {
            fArr2[0] = fArr2[0] + 360.0f;
        } else if (fArr2[0] - fArr[0] > 180.0f) {
            fArr[0] = fArr[0] + 360.0f;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f2) + fArr[i3];
        }
        return Color.HSVToColor(iAlpha, fArr3);
    }

    private HashMap<Integer, a> a() {
        HashMap<Integer, a> map = new HashMap<>();
        if (this.f2814c[0] != 0.0f) {
            map.put(0, new a(Color.argb(0, Color.red(this.f2813b[0]), Color.green(this.f2813b[0]), Color.blue(this.f2813b[0])), this.f2813b[0], this.f2812a * this.f2814c[0]));
        }
        for (int i = 1; i < this.f2813b.length; i++) {
            int i2 = i - 1;
            Integer numValueOf = Integer.valueOf((int) (this.f2812a * this.f2814c[i2]));
            int[] iArr = this.f2813b;
            int i3 = iArr[i2];
            int i4 = iArr[i];
            float f2 = this.f2812a;
            float[] fArr = this.f2814c;
            map.put(numValueOf, new a(i3, i4, (fArr[i] - fArr[i2]) * f2));
        }
        float[] fArr2 = this.f2814c;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer numValueOf2 = Integer.valueOf((int) (this.f2812a * fArr2[length]));
            int[] iArr2 = this.f2813b;
            map.put(numValueOf2, new a(iArr2[length], iArr2[length], this.f2812a * (1.0f - this.f2814c[length])));
        }
        return map;
    }

    int[] a(double d2) {
        HashMap<Integer, a> mapA = a();
        int[] iArr = new int[this.f2812a];
        int i = 0;
        a aVar = mapA.get(0);
        for (int i2 = 0; i2 < this.f2812a; i2++) {
            if (mapA.containsKey(Integer.valueOf(i2))) {
                aVar = mapA.get(Integer.valueOf(i2));
                i = i2;
            }
            iArr[i2] = a(aVar.f2816b, aVar.f2817c, (i2 - i) / aVar.f2818d);
        }
        if (d2 != 1.0d) {
            for (int i3 = 0; i3 < this.f2812a; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (((double) Color.alpha(i4)) * d2), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }
}