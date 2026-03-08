package com.autonavi.amap.mapcore;

import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
public class VirtualEarthProjection {
    public static final double EARTH_CIRCUMFERENCE_IN_METERS = 4.007501668557849E7d;
    public static final int EARTH_RADIUS_IN_METERS = 6378137;
    private static final int KMA_MAX_MAP_SIZE = 268435456;
    private static final double K_EARTH_CIRCLE = 4.0075016E7d;
    private static final double K_EARTH_CIRCLE_2 = 2.0037508E7d;
    public static final int MAXZOOMLEVEL = 20;
    public static final double MAX_LATITUDE = 85.0511287798d;
    public static final double MAX_LONGITUDE = 360.0d;
    public static final double MIN_LATITUDE = -85.0511287798d;
    public static final double MIN_LONGITUDE = -360.0d;
    public static final int PIXELS_PER_TILE = 256;
    public static final int TILE_SPLIT_LEVEL = 0;

    private static double degToRad(double d2) {
        return d2 * 0.017453292519943295d;
    }

    private static double radToDeg(double d2) {
        return d2 * 57.29577951308232d;
    }

    public static double clip(double d2, double d3, double d4) {
        return Math.min(Math.max(d2, d3), d4);
    }

    public static Point latLongToPixels(int i, int i2, int i3) {
        return latLongToPixels(((double) i2) / 3600000.0d, ((double) i) / 3600000.0d, i3);
    }

    public static void latLongToPixels(double d2, double d3, int i, Point point) {
        double dClip = clip(d2, -85.0511287798d, 85.0511287798d);
        double dDegToRad = degToRad(clip(d3, -360.0d, 360.0d)) * 6378137.0d;
        double dSin = Math.sin(degToRad(dClip));
        double dLog = (Math.log((dSin + 1.0d) / (1.0d - dSin)) * 6378137.0d) / 2.0d;
        double d4 = (dDegToRad + K_EARTH_CIRCLE_2) / 0.14929106831550598d;
        double d5 = (K_EARTH_CIRCLE_2 - dLog) / 0.14929106831550598d;
        point.x = (int) d4;
        point.y = (int) d5;
    }

    public static Point latLongToPixels(double d2, double d3, int i) {
        Point point = new Point();
        latLongToPixels(d2, d3, i, point);
        return point;
    }

    public static DPoint latLongToPixelsDouble(double d2, double d3, int i) {
        DPoint dPoint = new DPoint();
        double dClip = clip(d2, -85.0511287798d, 85.0511287798d);
        double dDegToRad = degToRad(clip(d3, -360.0d, 360.0d)) * 6378137.0d;
        double dSin = Math.sin(degToRad(dClip));
        double dLog = (Math.log((dSin + 1.0d) / (1.0d - dSin)) * 6378137.0d) / 2.0d;
        double d4 = (dDegToRad + K_EARTH_CIRCLE_2) / 0.14929106831550598d;
        double d5 = (K_EARTH_CIRCLE_2 - dLog) / 0.14929106831550598d;
        dPoint.x = d4;
        dPoint.y = d5;
        return dPoint;
    }

    public static DPoint pixelsToLatLong(long j, long j2, int i) {
        DPoint dPointObtain = DPoint.obtain();
        double d2 = (j * 0.14929106831550598d) - K_EARTH_CIRCLE_2;
        double d3 = K_EARTH_CIRCLE_2 - (j2 * 0.14929106831550598d);
        dPointObtain.x = radToDeg(d2 / 6378137.0d);
        double dExp = Math.exp((d3 / 6378137.0d) * 2.0d);
        dPointObtain.y = radToDeg(Math.asin((dExp - 1.0d) / (dExp + 1.0d)));
        return dPointObtain;
    }

    public static DPoint pixelsToLatLong(double d2, double d3, int i) {
        DPoint dPointObtain = DPoint.obtain();
        double d4 = (d2 * 0.14929106831550598d) - K_EARTH_CIRCLE_2;
        double d5 = K_EARTH_CIRCLE_2 - (d3 * 0.14929106831550598d);
        dPointObtain.x = radToDeg(d4 / 6378137.0d);
        double dExp = Math.exp((d5 / 6378137.0d) * 2.0d);
        dPointObtain.y = radToDeg(Math.asin((dExp - 1.0d) / (dExp + 1.0d)));
        return dPointObtain;
    }
}