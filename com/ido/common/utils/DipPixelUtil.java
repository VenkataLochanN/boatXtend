package com.ido.common.utils;

import android.content.Context;
import android.content.res.Resources;

/* JADX INFO: loaded from: classes2.dex */
public class DipPixelUtil {
    public static int sp2pix(float f2, float f3) {
        return (int) ((f2 * f3) + 0.5f);
    }

    public static int dip2px(float f2) {
        return (int) ((f2 * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static float dip2pxF(float f2) {
        return f2 * Resources.getSystem().getDisplayMetrics().density;
    }

    @Deprecated
    public static int px2dip(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static int px2dip(float f2) {
        return (int) ((f2 / Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(float f2) {
        return (int) (f2 * Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

    @Deprecated
    public static int px2sp(Context context, float f2) {
        return (int) ((f2 / context.getResources().getDisplayMetrics().scaledDensity) + 0.5f);
    }

    public static int px2sp(float f2) {
        return (int) ((f2 / Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
    }
}