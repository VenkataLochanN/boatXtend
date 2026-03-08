package com.autonavi.amap.mapcore;

import android.graphics.Point;

/* JADX INFO: loaded from: classes.dex */
public class MapProjection {
    public static void lonlat2Geo(double d2, double d3, IPoint iPoint) {
        Point pointLatLongToPixels = VirtualEarthProjection.latLongToPixels(d3, d2, 20);
        iPoint.x = pointLatLongToPixels.x;
        iPoint.y = pointLatLongToPixels.y;
    }

    public static void geo2LonLat(int i, int i2, DPoint dPoint) {
        DPoint dPointPixelsToLatLong = VirtualEarthProjection.pixelsToLatLong(i, i2, 20);
        dPoint.x = dPointPixelsToLatLong.x;
        dPoint.y = dPointPixelsToLatLong.y;
        dPointPixelsToLatLong.recycle();
    }
}