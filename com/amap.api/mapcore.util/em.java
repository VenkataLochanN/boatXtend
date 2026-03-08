package com.amap.api.mapcore.util;

import com.amap.api.maps.model.LatLng;

/* JADX INFO: compiled from: SegmentsIntersect.java */
/* JADX INFO: loaded from: classes.dex */
public class em {
    private static double a(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d2 = latLng.latitude - latLng3.latitude;
        return ((latLng.longitude - latLng2.longitude) * (latLng.latitude - latLng2.latitude)) - ((latLng.longitude - latLng2.longitude) * d2);
    }

    private static boolean b(LatLng latLng, LatLng latLng2, LatLng latLng3) {
        double d2 = latLng.longitude - latLng2.longitude > 0.0d ? latLng.longitude : latLng2.longitude;
        return (((latLng.longitude - latLng2.longitude) > 0.0d ? 1 : ((latLng.longitude - latLng2.longitude) == 0.0d ? 0 : -1)) < 0 ? latLng.longitude : latLng2.longitude) <= latLng3.longitude && latLng3.longitude <= d2 && (((latLng.latitude - latLng2.latitude) > 0.0d ? 1 : ((latLng.latitude - latLng2.latitude) == 0.0d ? 0 : -1)) < 0 ? latLng.latitude : latLng2.latitude) <= latLng3.latitude && latLng3.latitude <= (((latLng.latitude - latLng2.latitude) > 0.0d ? 1 : ((latLng.latitude - latLng2.latitude) == 0.0d ? 0 : -1)) > 0 ? latLng.latitude : latLng2.latitude);
    }

    public static boolean a(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4) {
        double dA = a(latLng3, latLng4, latLng);
        double dA2 = a(latLng3, latLng4, latLng2);
        double dA3 = a(latLng, latLng2, latLng3);
        double dA4 = a(latLng, latLng2, latLng4);
        if (((dA > 0.0d && dA2 < 0.0d) || (dA < 0.0d && dA2 > 0.0d)) && ((dA3 > 0.0d && dA4 < 0.0d) || (dA3 < 0.0d && dA4 > 0.0d))) {
            return true;
        }
        if (dA == 0.0d && b(latLng3, latLng4, latLng)) {
            return true;
        }
        if (dA2 == 0.0d && b(latLng3, latLng4, latLng2)) {
            return true;
        }
        if (dA3 == 0.0d && b(latLng, latLng2, latLng3)) {
            return true;
        }
        return dA4 == 0.0d && b(latLng, latLng2, latLng4);
    }
}