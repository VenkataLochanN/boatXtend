package com.baidu.mapapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;

/* JADX INFO: loaded from: classes.dex */
public final class MapStatusUpdateFactory {
    MapStatusUpdateFactory() {
    }

    public static MapStatusUpdate newLatLng(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(2);
        mapStatusUpdate.f2881b = latLng;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(3);
        mapStatusUpdate.f2882c = latLngBounds;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2) {
        if (latLngBounds == null || i <= 0 || i2 <= 0) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(9);
        mapStatusUpdate.f2882c = latLngBounds;
        mapStatusUpdate.f2883d = i;
        mapStatusUpdate.f2884e = i2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngBounds(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(10);
        mapStatusUpdate.f2882c = latLngBounds;
        mapStatusUpdate.k = i;
        mapStatusUpdate.l = i2;
        mapStatusUpdate.m = i3;
        mapStatusUpdate.n = i4;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngZoom(LatLng latLng, float f2) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(4);
        if (latLng == null) {
            return null;
        }
        mapStatusUpdate.f2881b = latLng;
        mapStatusUpdate.f2885f = f2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newLatLngZoom(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        if (latLngBounds == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(11);
        mapStatusUpdate.f2882c = latLngBounds;
        mapStatusUpdate.k = i;
        mapStatusUpdate.l = i2;
        mapStatusUpdate.m = i3;
        mapStatusUpdate.n = i4;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate newMapStatus(MapStatus mapStatus) {
        if (mapStatus == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(1);
        mapStatusUpdate.f2880a = mapStatus;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate scrollBy(int i, int i2) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(5);
        mapStatusUpdate.f2886g = i;
        mapStatusUpdate.f2887h = i2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomBy(float f2) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.i = f2;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomBy(float f2, Point point) {
        if (point == null) {
            return null;
        }
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(7);
        mapStatusUpdate.i = f2;
        mapStatusUpdate.j = point;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomIn() {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.i = 1.0f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomOut() {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(6);
        mapStatusUpdate.i = -1.0f;
        return mapStatusUpdate;
    }

    public static MapStatusUpdate zoomTo(float f2) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate(8);
        mapStatusUpdate.f2885f = f2;
        return mapStatusUpdate;
    }
}