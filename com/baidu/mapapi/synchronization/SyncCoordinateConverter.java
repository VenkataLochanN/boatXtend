package com.baidu.mapapi.synchronization;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapsdkplatform.comapi.location.CoordinateType;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;

/* JADX INFO: loaded from: classes.dex */
public class SyncCoordinateConverter {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private LatLng f3325a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private CoordType f3326b;

    public enum CoordType {
        COMMON,
        BD09LL
    }

    private static LatLng a(LatLng latLng) {
        return a(latLng, CoordinateType.GCJ02);
    }

    private static LatLng a(LatLng latLng, String str) {
        if (latLng == null) {
            return null;
        }
        return CoordUtil.Coordinate_encryptEx((float) latLng.longitude, (float) latLng.latitude, str);
    }

    private static LatLng b(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return CoordTrans.baiduToGcj(latLng);
    }

    public LatLng convert() {
        if (this.f3325a == null) {
            return null;
        }
        if (this.f3326b == null) {
            this.f3326b = CoordType.BD09LL;
        }
        int i = a.f3330a[this.f3326b.ordinal()];
        if (i == 1) {
            return a(this.f3325a);
        }
        if (i != 2) {
            return null;
        }
        return b(this.f3325a);
    }

    public SyncCoordinateConverter coord(LatLng latLng) {
        this.f3325a = latLng;
        return this;
    }

    public SyncCoordinateConverter from(CoordType coordType) {
        this.f3326b = coordType;
        return this;
    }
}