package com.amap.api.location;

/* JADX INFO: loaded from: classes.dex */
public class CoordUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static boolean f85a = false;

    public static native int convertToGcj(double[] dArr, double[] dArr2);

    public static boolean isLoadedSo() {
        return f85a;
    }

    public static void setLoadedSo(boolean z) {
        f85a = z;
    }
}