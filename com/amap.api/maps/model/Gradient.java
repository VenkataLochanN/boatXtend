package com.amap.api.maps.model;

import android.graphics.Color;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.utils.SpatialRelationUtil;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class Gradient {
    private static final int DEFAULT_COLOR_MAP_SIZE = 1000;
    private boolean isAvailable;
    private int mColorMapSize;
    private int[] mColors;
    private float[] mStartPoints;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final int f1857a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f1858b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final float f1859c;

        private a(int i, int i2, float f2) {
            this.f1857a = i;
            this.f1858b = i2;
            this.f1859c = f2;
        }
    }

    public Gradient(int[] iArr, float[] fArr) {
        this(iArr, fArr, 1000);
    }

    private Gradient(int[] iArr, float[] fArr, int i) {
        this.isAvailable = true;
        try {
            if (iArr == null || fArr == null) {
                throw new AMapException("colors and startPoints should not be null");
            }
            if (iArr.length != fArr.length) {
                throw new AMapException("colors and startPoints should be same length");
            }
            if (iArr.length == 0) {
                throw new AMapException("No colors have been defined");
            }
            for (int i2 = 1; i2 < fArr.length; i2++) {
                if (fArr[i2] < fArr[i2 - 1]) {
                    throw new AMapException("startPoints should be in increasing order");
                }
            }
            this.mColorMapSize = i;
            this.mColors = new int[iArr.length];
            this.mStartPoints = new float[fArr.length];
            System.arraycopy(iArr, 0, this.mColors, 0, iArr.length);
            System.arraycopy(fArr, 0, this.mStartPoints, 0, fArr.length);
            this.isAvailable = true;
        } catch (AMapException e2) {
            this.isAvailable = false;
            Log.e("amap", e2.getErrorMessage());
            e2.printStackTrace();
        }
    }

    public int[] getColors() {
        return this.mColors;
    }

    public float[] getStartPoints() {
        return this.mStartPoints;
    }

    private HashMap<Integer, a> a() {
        HashMap<Integer, a> map = new HashMap<>(32);
        if (this.mStartPoints[0] != 0.0f) {
            map.put(0, new a(Color.argb(0, Color.red(this.mColors[0]), Color.green(this.mColors[0]), Color.blue(this.mColors[0])), this.mColors[0], this.mColorMapSize * this.mStartPoints[0]));
        }
        for (int i = 1; i < this.mColors.length; i++) {
            int i2 = i - 1;
            Integer numValueOf = Integer.valueOf((int) (this.mColorMapSize * this.mStartPoints[i2]));
            int[] iArr = this.mColors;
            int i3 = iArr[i2];
            int i4 = iArr[i];
            float f2 = this.mColorMapSize;
            float[] fArr = this.mStartPoints;
            map.put(numValueOf, new a(i3, i4, f2 * (fArr[i] - fArr[i2])));
        }
        float[] fArr2 = this.mStartPoints;
        if (fArr2[fArr2.length - 1] != 1.0f) {
            int length = fArr2.length - 1;
            Integer numValueOf2 = Integer.valueOf((int) (this.mColorMapSize * fArr2[length]));
            int[] iArr2 = this.mColors;
            map.put(numValueOf2, new a(iArr2[length], iArr2[length], this.mColorMapSize * (1.0f - this.mStartPoints[length])));
        }
        return map;
    }

    protected int[] generateColorMap(double d2) {
        HashMap<Integer, a> mapA = a();
        int[] iArr = new int[this.mColorMapSize];
        int i = 0;
        a aVar = mapA.get(0);
        for (int i2 = 0; i2 < this.mColorMapSize; i2++) {
            if (mapA.containsKey(Integer.valueOf(i2))) {
                aVar = mapA.get(Integer.valueOf(i2));
                i = i2;
            }
            iArr[i2] = a(aVar.f1857a, aVar.f1858b, (i2 - i) / aVar.f1859c);
        }
        if (d2 != 1.0d) {
            for (int i3 = 0; i3 < this.mColorMapSize; i3++) {
                int i4 = iArr[i3];
                iArr[i3] = Color.argb((int) (((double) Color.alpha(i4)) * d2), Color.red(i4), Color.green(i4), Color.blue(i4));
            }
        }
        return iArr;
    }

    static int a(int i, int i2, float f2) {
        int iAlpha = (int) (((Color.alpha(i2) - Color.alpha(i)) * f2) + Color.alpha(i));
        float[] fArr = new float[3];
        Color.RGBToHSV(Color.red(i), Color.green(i), Color.blue(i), fArr);
        float[] fArr2 = new float[3];
        Color.RGBToHSV(Color.red(i2), Color.green(i2), Color.blue(i2), fArr2);
        float f3 = 180;
        if (fArr[0] - fArr2[0] > f3) {
            fArr2[0] = fArr2[0] + SpatialRelationUtil.A_CIRCLE_DEGREE;
        } else if (fArr2[0] - fArr[0] > f3) {
            fArr[0] = fArr[0] + SpatialRelationUtil.A_CIRCLE_DEGREE;
        }
        float[] fArr3 = new float[3];
        for (int i3 = 0; i3 < 3; i3++) {
            fArr3[i3] = ((fArr2[i3] - fArr[i3]) * f2) + fArr[i3];
        }
        return Color.HSVToColor(iAlpha, fArr3);
    }

    protected boolean isAvailable() {
        return this.isAvailable;
    }
}