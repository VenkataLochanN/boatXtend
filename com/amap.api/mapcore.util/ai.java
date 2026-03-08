package com.amap.api.mapcore.util;

import android.content.Context;
import com.amap.api.maps.model.LatLng;
import com.autonavi.amap.mapcore.DPoint;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: OffsetUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class ai {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static double f130a = 3.141592653589793d;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static boolean f133d = false;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final double[] f134e = {25.575374d, 120.391111d};

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final double[] f135f = {21.405235d, 121.649046d};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private static final List<LatLng> f136g = new ArrayList(Arrays.asList(new LatLng(23.379947d, 119.757001d), new LatLng(24.983296d, 120.474496d), new LatLng(25.518722d, 121.359866d), new LatLng(25.41329d, 122.443582d), new LatLng(24.862708d, 122.288354d), new LatLng(24.461292d, 122.188319d), new LatLng(21.584761d, 120.968923d), new LatLng(21.830837d, 120.654445d)));

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static double f131b = 6378245.0d;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static double f132c = 0.006693421622965943d;

    public static LatLng a(Context context, LatLng latLng) {
        if (context == null) {
            return null;
        }
        if (!ek.a(latLng.latitude, latLng.longitude)) {
            return latLng;
        }
        DPoint dPointA = a(DPoint.obtain(latLng.longitude, latLng.latitude), f133d);
        LatLng latLng2 = new LatLng(dPointA.y, dPointA.x, false);
        dPointA.recycle();
        return latLng2;
    }

    private static DPoint a(DPoint dPoint, boolean z) {
        try {
            if (!ek.a(dPoint.y, dPoint.x)) {
                return dPoint;
            }
            double[] dArrA = new double[2];
            if (!z) {
                dArrA = jy.a(dPoint.x, dPoint.y);
            }
            dPoint.recycle();
            return DPoint.obtain(dArrA[0], dArrA[1]);
        } catch (Throwable unused) {
            return dPoint;
        }
    }

    public static LatLng b(Context context, LatLng latLng) {
        try {
            if (!ek.a(latLng.latitude, latLng.longitude)) {
                return latLng;
            }
            DPoint dPointF = f(latLng.longitude, latLng.latitude);
            LatLng latLngA = a(context, new LatLng(dPointF.y, dPointF.x, false));
            dPointF.recycle();
            return latLngA;
        } catch (Throwable th) {
            th.printStackTrace();
            return latLng;
        }
    }

    public static double a(double d2, double d3) {
        return (Math.cos(d3 / 100000.0d) * (d2 / 18000.0d)) + (Math.sin(d2 / 100000.0d) * (d3 / 9000.0d));
    }

    public static double b(double d2, double d3) {
        return (Math.sin(d3 / 100000.0d) * (d2 / 18000.0d)) + (Math.cos(d2 / 100000.0d) * (d3 / 9000.0d));
    }

    private static DPoint f(double d2, double d3) {
        double d4 = ((long) (d2 * 100000.0d)) % 36000000;
        double d5 = ((long) (d3 * 100000.0d)) % 36000000;
        double d6 = (int) ((-a(d4, d5)) + d4);
        double d7 = (int) ((-b(d4, d5)) + d5);
        double d8 = (int) ((-a(d6, d7)) + d4 + ((double) (d4 > 0.0d ? 1 : -1)));
        return DPoint.obtain(d8 / 100000.0d, ((double) ((int) (((-b(d8, d7)) + d5) + ((double) (d5 <= 0.0d ? -1 : 1))))) / 100000.0d);
    }

    public static LatLng a(LatLng latLng) {
        if (latLng != null) {
            try {
                if (ek.a(latLng.latitude, latLng.longitude)) {
                    DPoint dPointA = a(latLng.longitude, latLng.latitude, 2);
                    LatLng latLng2 = new LatLng(dPointA.y, dPointA.x, false);
                    dPointA.recycle();
                    return latLng2;
                }
                if (!c(latLng.latitude, latLng.longitude)) {
                    return latLng;
                }
                DPoint dPointA2 = a(latLng.longitude, latLng.latitude, 2);
                return h(dPointA2.y, dPointA2.x);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return latLng;
    }

    private static double a(double d2) {
        return Math.sin(d2 * 3000.0d * (f130a / 180.0d)) * 2.0E-5d;
    }

    private static double b(double d2) {
        return Math.cos(d2 * 3000.0d * (f130a / 180.0d)) * 3.0E-6d;
    }

    private static DPoint g(double d2, double d3) {
        DPoint dPointObtain = DPoint.obtain();
        double d4 = (d2 * d2) + (d3 * d3);
        double dCos = (Math.cos(b(d2) + Math.atan2(d3, d2)) * (a(d3) + Math.sqrt(d4))) + 0.0065d;
        double dSin = (Math.sin(b(d2) + Math.atan2(d3, d2)) * (a(d3) + Math.sqrt(d4))) + 0.006d;
        dPointObtain.x = a(dCos, 8);
        dPointObtain.y = a(dSin, 8);
        return dPointObtain;
    }

    private static double a(double d2, int i) {
        return new BigDecimal(d2).setScale(i, 4).doubleValue();
    }

    private static DPoint a(double d2, double d3, int i) {
        DPoint dPointA = null;
        double d4 = 0.006401062d;
        double d5 = 0.0060424805d;
        for (int i2 = 0; i2 < i; i2++) {
            dPointA = a(d2, d3, d4, d5);
            d4 = d2 - dPointA.x;
            d5 = d3 - dPointA.y;
        }
        return dPointA;
    }

    private static DPoint a(double d2, double d3, double d4, double d5) {
        DPoint dPointObtain = DPoint.obtain();
        double d6 = d2 - d4;
        double d7 = d3 - d5;
        DPoint dPointG = g(d6, d7);
        dPointObtain.x = a((d2 + d6) - dPointG.x, 8);
        dPointObtain.y = a((d3 + d7) - dPointG.y, 8);
        return dPointObtain;
    }

    public static boolean c(double d2, double d3) {
        return er.a(new LatLng(d2, d3), f136g);
    }

    private static LatLng h(double d2, double d3) {
        LatLng latLngI = i(d2, d3);
        return new LatLng((d2 * 2.0d) - latLngI.latitude, (d3 * 2.0d) - latLngI.longitude);
    }

    private static LatLng i(double d2, double d3) {
        double d4 = d3 - 105.0d;
        double d5 = d2 - 35.0d;
        double d6 = d(d4, d5);
        double dE = e(d4, d5);
        double d7 = (d2 / 180.0d) * f130a;
        double dSin = Math.sin(d7);
        double d8 = 1.0d - ((f132c * dSin) * dSin);
        double dSqrt = Math.sqrt(d8);
        double d9 = f131b;
        return new LatLng(d2 + ((d6 * 180.0d) / ((((1.0d - f132c) * d9) / (d8 * dSqrt)) * f130a)), d3 + ((dE * 180.0d) / (((d9 / dSqrt) * Math.cos(d7)) * f130a)));
    }

    public static double d(double d2, double d3) {
        double d4 = d2 * 2.0d;
        return (-100.0d) + d4 + (d3 * 3.0d) + (d3 * 0.2d * d3) + (0.1d * d2 * d3) + (Math.sqrt(Math.abs(d2)) * 0.2d) + ((((Math.sin((d2 * 6.0d) * f130a) * 20.0d) + (Math.sin(d4 * f130a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f130a * d3) * 20.0d) + (Math.sin((d3 / 3.0d) * f130a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d3 / 12.0d) * f130a) * 160.0d) + (Math.sin((d3 * f130a) / 30.0d) * 320.0d)) * 2.0d) / 3.0d);
    }

    public static double e(double d2, double d3) {
        double d4 = d2 * 0.1d;
        return d2 + 300.0d + (d3 * 2.0d) + (d4 * d2) + (d4 * d3) + (Math.sqrt(Math.abs(d2)) * 0.1d) + ((((Math.sin((6.0d * d2) * f130a) * 20.0d) + (Math.sin((d2 * 2.0d) * f130a) * 20.0d)) * 2.0d) / 3.0d) + ((((Math.sin(f130a * d2) * 20.0d) + (Math.sin((d2 / 3.0d) * f130a) * 40.0d)) * 2.0d) / 3.0d) + ((((Math.sin((d2 / 12.0d) * f130a) * 150.0d) + (Math.sin((d2 / 30.0d) * f130a) * 300.0d)) * 2.0d) / 3.0d);
    }
}