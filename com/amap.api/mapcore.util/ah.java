package com.amap.api.mapcore.util;

import android.graphics.Point;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.AbstractCameraUpdateMessage;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.VirtualEarthProjection;

/* JADX INFO: compiled from: CameraUpdateFactoryDelegate.java */
/* JADX INFO: loaded from: classes.dex */
public class ah {
    public static AbstractCameraUpdateMessage a() {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        agVar.amount = 1.0f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage b() {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        agVar.amount = -1.0f;
        return agVar;
    }

    public static AbstractCameraUpdateMessage a(float f2, float f3) {
        af afVar = new af();
        afVar.nowType = AbstractCameraUpdateMessage.Type.scrollBy;
        afVar.xPixel = f2;
        afVar.yPixel = f3;
        return afVar;
    }

    public static AbstractCameraUpdateMessage a(float f2) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        aeVar.zoom = f2;
        return aeVar;
    }

    public static AbstractCameraUpdateMessage b(float f2) {
        return a(f2, (Point) null);
    }

    public static AbstractCameraUpdateMessage a(float f2, Point point) {
        ag agVar = new ag();
        agVar.nowType = AbstractCameraUpdateMessage.Type.zoomBy;
        agVar.amount = f2;
        agVar.focus = point;
        return agVar;
    }

    public static AbstractCameraUpdateMessage a(CameraPosition cameraPosition) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        if (cameraPosition != null && cameraPosition.target != null) {
            DPoint dPointLatLongToPixelsDouble = VirtualEarthProjection.latLongToPixelsDouble(cameraPosition.target.latitude, cameraPosition.target.longitude, 20);
            aeVar.geoPoint = new DPoint(dPointLatLongToPixelsDouble.x, dPointLatLongToPixelsDouble.y);
            aeVar.zoom = cameraPosition.zoom;
            aeVar.bearing = cameraPosition.bearing;
            aeVar.tilt = cameraPosition.tilt;
            aeVar.cameraPosition = cameraPosition;
        }
        return aeVar;
    }

    public static AbstractCameraUpdateMessage a(Point point) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        aeVar.geoPoint = new DPoint(point.x, point.y);
        return aeVar;
    }

    public static AbstractCameraUpdateMessage c(float f2) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        aeVar.tilt = f2;
        return aeVar;
    }

    public static AbstractCameraUpdateMessage d(float f2) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        aeVar.bearing = f2;
        return aeVar;
    }

    public static AbstractCameraUpdateMessage b(float f2, Point point) {
        ae aeVar = new ae();
        aeVar.nowType = AbstractCameraUpdateMessage.Type.newCameraPosition;
        aeVar.geoPoint = new DPoint(point.x, point.y);
        aeVar.bearing = f2;
        return aeVar;
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng) {
        return a(CameraPosition.builder().target(latLng).zoom(Float.NaN).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLng latLng, float f2) {
        return a(CameraPosition.builder().target(latLng).zoom(f2).bearing(Float.NaN).tilt(Float.NaN).build());
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i) {
        ad adVar = new ad();
        adVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBounds;
        adVar.bounds = latLngBounds;
        adVar.paddingLeft = i;
        adVar.paddingRight = i;
        adVar.paddingTop = i;
        adVar.paddingBottom = i;
        return adVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3) {
        ad adVar = new ad();
        adVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBoundsWithSize;
        adVar.bounds = latLngBounds;
        adVar.paddingLeft = i3;
        adVar.paddingRight = i3;
        adVar.paddingTop = i3;
        adVar.paddingBottom = i3;
        adVar.width = i;
        adVar.height = i2;
        return adVar;
    }

    public static AbstractCameraUpdateMessage a(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        ad adVar = new ad();
        adVar.nowType = AbstractCameraUpdateMessage.Type.newLatLngBounds;
        adVar.bounds = latLngBounds;
        adVar.paddingLeft = i;
        adVar.paddingRight = i2;
        adVar.paddingTop = i3;
        adVar.paddingBottom = i4;
        return adVar;
    }

    public static AbstractCameraUpdateMessage c() {
        return new ae();
    }
}