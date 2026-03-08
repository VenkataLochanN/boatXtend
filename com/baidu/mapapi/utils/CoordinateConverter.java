package com.baidu.mapapi.utils;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;

/* JADX INFO: loaded from: classes.dex */
public class CoordinateConverter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LatLng f3357a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CoordType f3358b;

    public enum CoordType {
        GPS,
        COMMON,
        BD09LL,
        BD09MC
    }

    private static LatLng a(LatLng latLng) {
        return a(latLng, CoordinateType.WGS84);
    }

    private static LatLng a(LatLng latLng, String str) {
        if (latLng == null) {
            return null;
        }
        return CoordUtil.Coordinate_encryptEx((float) latLng.longitude, (float) latLng.latitude, str);
    }

    private static LatLng b(LatLng latLng) {
        return a(latLng, CoordinateType.GCJ02);
    }

    private static LatLng c(LatLng latLng) {
        return a(latLng, CoordinateType.BD09MC);
    }

    private static LatLng d(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return CoordTrans.baiduToGcj(latLng);
    }

    public LatLng convert() {
        if (this.f3357a == null) {
            return null;
        }
        if (this.f3358b == null) {
            this.f3358b = CoordType.GPS;
        }
        int i = a.f3359a[this.f3358b.ordinal()];
        if (i == 1) {
            return b(this.f3357a);
        }
        if (i == 2) {
            return a(this.f3357a);
        }
        if (i == 3) {
            return d(this.f3357a);
        }
        if (i != 4) {
            return null;
        }
        return c(this.f3357a);
    }

    public CoordinateConverter coord(LatLng latLng) {
        this.f3357a = latLng;
        return this;
    }

    public CoordinateConverter from(CoordType coordType) {
        this.f3358b = coordType;
        return this;
    }
}