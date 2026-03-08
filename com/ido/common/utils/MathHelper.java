package com.ido.common.utils;

import android.content.Context;

/* JADX INFO: loaded from: classes2.dex */
public class MathHelper {
    public static int getRound5(float f2) {
        return (((int) (((double) f2) + 2.5d)) / 5) * 5;
    }

    public static int getCeil5(float f2) {
        return (((int) (((double) f2) + 4.9999999d)) / 5) * 5;
    }

    public static int getCeil10(float f2) {
        return (((int) (((double) f2) + 9.9999999d)) / 10) * 10;
    }

    public static int getRound10(float f2) {
        return (((int) (f2 + 5.0f)) / 10) * 10;
    }

    public static int getCast10(float f2) {
        return (((int) f2) / 10) * 10;
    }

    public static double getPointAngle(float f2, float f3, float f4, float f5) {
        double d2 = f2 - f4;
        double dAcos = (Math.acos(d2 / Math.sqrt(Math.pow(d2, 2.0d) + Math.pow(f3 - f5, 2.0d))) / 3.141592653589793d) * 180.0d;
        return f3 < f5 ? 360.0d - dAcos : dAcos;
    }

    public static int dip2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(Context context, float f2) {
        return (int) ((f2 * context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}