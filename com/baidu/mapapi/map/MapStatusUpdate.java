package com.baidu.mapapi.map;

import android.graphics.Point;
import android.util.Log;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.inner.GeoPoint;

/* JADX INFO: loaded from: classes.dex */
public final class MapStatusUpdate {
    private static final String o = MapStatusUpdate.class.getSimpleName();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    MapStatus f2880a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    LatLng f2881b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    LatLngBounds f2882c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f2883d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f2884e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    float f2885f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    int f2886g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    int f2887h;
    float i;
    Point j;
    int k = 0;
    int l = 0;
    int m = 0;
    int n = 0;
    private int p;

    private MapStatusUpdate() {
    }

    MapStatusUpdate(int i) {
        this.p = i;
    }

    private float a(float f2) {
        return (float) (Math.pow(2.0d, 18.0f - f2) / ((double) (SysOSUtil.getDensityDpi() / 310.0f)));
    }

    private float a(LatLngBounds latLngBounds, com.baidu.mapsdkplatform.comapi.map.e eVar, int i, int i2) {
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLngBounds.southwest);
        GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(latLngBounds.northeast);
        int longitudeE6 = (int) geoPointLl2mc.getLongitudeE6();
        int latitudeE6 = (int) geoPointLl2mc.getLatitudeE6();
        return eVar.a(longitudeE6, (int) geoPointLl2mc2.getLatitudeE6(), (int) geoPointLl2mc2.getLongitudeE6(), latitudeE6, i, i2);
    }

    private MapStatusUpdate a(MapStatus mapStatus) {
        MapStatusUpdate mapStatusUpdate = new MapStatusUpdate();
        synchronized (this) {
            mapStatusUpdate.f2880a = mapStatus;
            mapStatusUpdate.f2882c = this.f2882c;
            mapStatusUpdate.k = this.k;
            mapStatusUpdate.l = this.l;
            mapStatusUpdate.m = this.m;
            mapStatusUpdate.n = this.n;
        }
        return mapStatusUpdate;
    }

    private LatLng a(LatLngBounds latLngBounds, com.baidu.mapsdkplatform.comapi.map.e eVar, float f2) {
        double latitudeE6;
        double latitudeE62;
        if (latLngBounds == null || eVar == null) {
            return null;
        }
        GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLngBounds.getCenter());
        int i = this.k;
        double d2 = i * f2;
        int i2 = this.m;
        double d3 = i2 * f2;
        double d4 = this.l * f2;
        double d5 = this.n * f2;
        double longitudeE6 = i > i2 ? geoPointLl2mc.getLongitudeE6() - ((d2 - d3) / 2.0d) : i < i2 ? geoPointLl2mc.getLongitudeE6() + ((d3 - d2) / 2.0d) : geoPointLl2mc.getLongitudeE6();
        int i3 = this.l;
        int i4 = this.n;
        if (i3 < i4) {
            latitudeE62 = geoPointLl2mc.getLatitudeE6() - ((d5 - d4) / 2.0d);
        } else {
            if (i3 <= i4) {
                latitudeE6 = geoPointLl2mc.getLatitudeE6();
                return CoordUtil.mc2ll(new GeoPoint(latitudeE6, longitudeE6));
            }
            latitudeE62 = geoPointLl2mc.getLatitudeE6();
            d4 -= d5;
        }
        latitudeE6 = latitudeE62 + (d4 / 2.0d);
        return CoordUtil.mc2ll(new GeoPoint(latitudeE6, longitudeE6));
    }

    private boolean a(int i, int i2, int i3, int i4, com.baidu.mapsdkplatform.comapi.map.e eVar) {
        MapStatusUpdate mapStatusUpdateG = eVar.G();
        return (mapStatusUpdateG != null && i == mapStatusUpdateG.k && i2 == mapStatusUpdateG.l && i3 == mapStatusUpdateG.m && i4 == mapStatusUpdateG.n) ? false : true;
    }

    private boolean a(LatLngBounds latLngBounds, com.baidu.mapsdkplatform.comapi.map.e eVar) {
        MapStatusUpdate mapStatusUpdateG = eVar.G();
        if (mapStatusUpdateG == null) {
            return true;
        }
        return (latLngBounds.southwest.latitude == mapStatusUpdateG.f2882c.southwest.latitude && latLngBounds.southwest.longitude == mapStatusUpdateG.f2882c.southwest.longitude && latLngBounds.northeast.latitude == mapStatusUpdateG.f2882c.northeast.latitude && latLngBounds.northeast.longitude == mapStatusUpdateG.f2882c.northeast.longitude) ? false : true;
    }

    MapStatus a(com.baidu.mapsdkplatform.comapi.map.e eVar, MapStatus mapStatus) {
        if (eVar == null || mapStatus == null) {
            return null;
        }
        switch (this.p) {
            case 3:
                LatLngBounds latLngBounds = this.f2882c;
                if (latLngBounds != null) {
                    GeoPoint geoPointLl2mc = CoordUtil.ll2mc(latLngBounds.southwest);
                    GeoPoint geoPointLl2mc2 = CoordUtil.ll2mc(this.f2882c.northeast);
                    break;
                }
                break;
            case 5:
                GeoPoint geoPointB = eVar.b((eVar.H() / 2) + this.f2886g, (eVar.I() / 2) + this.f2887h);
                break;
            case 7:
                break;
            case 9:
                LatLngBounds latLngBounds2 = this.f2882c;
                if (latLngBounds2 != null) {
                    GeoPoint geoPointLl2mc3 = CoordUtil.ll2mc(latLngBounds2.southwest);
                    GeoPoint geoPointLl2mc4 = CoordUtil.ll2mc(this.f2882c.northeast);
                    break;
                }
                break;
            case 10:
                if (this.f2882c != null) {
                    int iH = (eVar.H() - this.k) - this.m;
                    if (iH < 0) {
                        iH = eVar.H();
                        Log.e(o, "Bound paddingLeft or paddingRight too larger, please check");
                    }
                    int I = (eVar.I() - this.l) - this.n;
                    if (I < 0) {
                        I = eVar.I();
                        Log.e(o, "Bound paddingTop or paddingBottom too larger, please check");
                    }
                    float fA = a(this.f2882c, eVar, iH, I);
                    LatLng latLngA = a(this.f2882c, eVar, a(fA));
                    if (latLngA != null) {
                        boolean zA = a(this.f2882c, eVar);
                        boolean zA2 = a(this.k, this.l, this.m, this.n, eVar);
                        if (zA || zA2) {
                            MapStatus mapStatus2 = new MapStatus(mapStatus.rotate, latLngA, mapStatus.overlook, fA, null, null);
                            eVar.a(a(mapStatus2));
                        } else if (eVar.G() == null) {
                        }
                    } else {
                        Log.e(o, "Bound center error");
                    }
                    break;
                }
                break;
            case 11:
                if (this.f2882c != null) {
                    WinRound winRound = mapStatus.winRound;
                    int iAbs = Math.abs(winRound.right - winRound.left);
                    int iAbs2 = Math.abs(winRound.bottom - winRound.top);
                    GeoPoint geoPointLl2mc5 = CoordUtil.ll2mc(this.f2882c.southwest);
                    GeoPoint geoPointLl2mc6 = CoordUtil.ll2mc(this.f2882c.northeast);
                    double longitudeE6 = geoPointLl2mc5.getLongitudeE6();
                    double latitudeE6 = geoPointLl2mc6.getLatitudeE6();
                    double longitudeE62 = geoPointLl2mc6.getLongitudeE6();
                    double latitudeE62 = geoPointLl2mc5.getLatitudeE6();
                    float fA2 = eVar.a((int) longitudeE6, (int) latitudeE6, (int) longitudeE62, (int) latitudeE62, (iAbs - this.k) - this.m, (iAbs2 - this.l) - Math.abs(this.n));
                    if (fA2 != 0.0f) {
                        Point pointA = eVar.a(CoordUtil.ll2mc(this.f2882c.northeast));
                        Point pointA2 = eVar.a(CoordUtil.ll2mc(this.f2882c.southwest));
                        if (Math.abs(pointA.y) < Math.abs(this.n)) {
                            fA2 -= this.n / iAbs2;
                        }
                        if (longitudeE6 == longitudeE62 && latitudeE6 == latitudeE62) {
                            fA2 = mapStatus.zoom;
                        }
                        int i = iAbs / 2;
                        int i2 = iAbs2 - pointA2.y;
                        int i3 = this.n;
                        break;
                    }
                }
                break;
        }
        return null;
    }
}