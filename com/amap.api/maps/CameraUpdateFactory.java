package com.amap.api.maps;

import android.graphics.Point;
import android.util.Log;
import com.amap.api.mapcore.util.ah;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

/* JADX INFO: loaded from: classes.dex */
public final class CameraUpdateFactory {
    private static final String CLASSNAME = "CameraUpdateFactory";

    public static CameraUpdate zoomIn() {
        return new CameraUpdate(ah.a());
    }

    public static CameraUpdate zoomOut() {
        return new CameraUpdate(ah.b());
    }

    public static CameraUpdate scrollBy(float f2, float f3) {
        return new CameraUpdate(ah.a(f2, f3));
    }

    public static CameraUpdate zoomTo(float f2) {
        return new CameraUpdate(ah.a(f2));
    }

    public static CameraUpdate zoomBy(float f2) {
        return new CameraUpdate(ah.b(f2));
    }

    public static CameraUpdate zoomBy(float f2, Point point) {
        return new CameraUpdate(ah.a(f2, point));
    }

    public static CameraUpdate newCameraPosition(CameraPosition cameraPosition) {
        if (cameraPosition == null) {
            Log.w(CLASSNAME, "cameraPosition is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(cameraPosition));
    }

    public static CameraUpdate newLatLng(LatLng latLng) {
        if (latLng == null) {
            Log.w(CLASSNAME, "latLng is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(latLng));
    }

    public static CameraUpdate newLatLngZoom(LatLng latLng, float f2) {
        if (latLng == null) {
            Log.w(CLASSNAME, "target is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(latLng, f2));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i) {
        if (latLngBounds == null) {
            Log.w(CLASSNAME, "bounds is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(latLngBounds, i));
    }

    public static CameraUpdate changeLatLng(LatLng latLng) {
        if (latLng == null) {
            Log.w(CLASSNAME, "target is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(VirtualEarthProjection.latLongToPixels(latLng.latitude, latLng.longitude, 20)));
    }

    public static CameraUpdate changeBearing(float f2) {
        return new CameraUpdate(ah.d(f2 % 360.0f));
    }

    public static CameraUpdate changeBearingGeoCenter(float f2, IPoint iPoint) {
        if (iPoint == null) {
            Log.w(CLASSNAME, "geoPoint is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.b(f2 % 360.0f, new Point(iPoint.x, iPoint.y)));
    }

    public static CameraUpdate changeTilt(float f2) {
        return new CameraUpdate(ah.c(f2));
    }

    public static CameraUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3) {
        if (latLngBounds == null) {
            Log.w(CLASSNAME, "bounds is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(latLngBounds, i, i2, i3));
    }

    public static CameraUpdate newLatLngBoundsRect(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (latLngBounds == null) {
            Log.w(CLASSNAME, "bounds is null");
            return new CameraUpdate(ah.c());
        }
        return new CameraUpdate(ah.a(latLngBounds, i, i2, i3, i4));
    }
}