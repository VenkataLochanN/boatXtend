package com.autonavi.base.amap.mapcore;

/* JADX INFO: loaded from: classes.dex */
public class AMapNativeHeatMapLayer {
    public static native long nativeCreate();

    public static native long nativeDestroy(long j);

    public static native Object nativeGetHeatMapItem(long j, double d2, double d3);

    public static native void nativeRender(long j, float[] fArr, float[] fArr2, int i, int i2, float f2);

    public static native void nativeSetGLShaderManager(long j, long j2);

    public static native void nativeSetOptions(long j, double[] dArr, int i, float f2, int[] iArr, float[] fArr, float f3, float f4, float f5, float f6, int i2, double d2);
}