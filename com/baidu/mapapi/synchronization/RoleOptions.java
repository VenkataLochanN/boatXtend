package com.baidu.mapapi.synchronization;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.synchronization.SyncCoordinateConverter;

/* JADX INFO: loaded from: classes.dex */
public final class RoleOptions {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3317a = RoleOptions.class.getSimpleName();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3318b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3319c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3320d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private String f3321e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private SyncCoordinateConverter.CoordType f3322f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private LatLng f3323g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private String f3324h = null;
    private String i = null;
    private LatLng j = null;
    private String k = null;
    private String l = null;
    private LatLng m = null;
    private String n = null;
    private String o = null;

    public RoleOptions() {
        this.f3318b = null;
        this.f3319c = 0;
        this.f3320d = null;
        this.f3321e = null;
        this.f3322f = SyncCoordinateConverter.CoordType.BD09LL;
        this.f3318b = null;
        this.f3319c = 0;
        this.f3320d = null;
        this.f3321e = null;
        this.f3322f = SyncCoordinateConverter.CoordType.BD09LL;
    }

    private LatLng a(LatLng latLng) {
        return new SyncCoordinateConverter().from(this.f3322f).coord(latLng).convert();
    }

    public SyncCoordinateConverter.CoordType getCoordType() {
        return this.f3322f;
    }

    public String getDriverId() {
        return this.f3320d;
    }

    public LatLng getDriverPosition() {
        return this.m;
    }

    public String getDriverPositionName() {
        return this.o;
    }

    public String getDriverPositionPoiUid() {
        return this.n;
    }

    public LatLng getEndPosition() {
        return this.j;
    }

    public String getEndPositionName() {
        return this.l;
    }

    public String getEndPositionPoiUid() {
        return this.k;
    }

    public String getOrderId() {
        return this.f3318b;
    }

    public int getRoleType() {
        return this.f3319c;
    }

    public LatLng getStartPosition() {
        return this.f3323g;
    }

    public String getStartPositionName() {
        return this.i;
    }

    public String getStartPositionPoiUid() {
        return this.f3324h;
    }

    public String getUserId() {
        return this.f3321e;
    }

    public RoleOptions setCoordType(SyncCoordinateConverter.CoordType coordType) {
        if (SyncCoordinateConverter.CoordType.BD09LL != coordType && SyncCoordinateConverter.CoordType.COMMON != coordType) {
            throw new IllegalArgumentException("BDMapSDKException: CoordType only can be BD09LL or COMMON, please check!");
        }
        this.f3322f = coordType;
        return this;
    }

    public RoleOptions setDriverId(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: driverId is null");
        }
        this.f3320d = str;
        return this;
    }

    public RoleOptions setDriverPosition(LatLng latLng) {
        if (latLng == null) {
            this.m = null;
            return this;
        }
        if (SyncCoordinateConverter.CoordType.COMMON == this.f3322f) {
            latLng = a(latLng);
        }
        this.m = latLng;
        return this;
    }

    public RoleOptions setDriverPositionName(String str) {
        this.o = str;
        return this;
    }

    public RoleOptions setDriverPositionPoiUid(String str) {
        this.n = str;
        return this;
    }

    public RoleOptions setEndPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: endPosition is null, must be applied!");
        }
        if (SyncCoordinateConverter.CoordType.COMMON == this.f3322f) {
            latLng = a(latLng);
        }
        this.j = latLng;
        return this;
    }

    public RoleOptions setEndPositionName(String str) {
        this.l = str;
        return this;
    }

    public RoleOptions setEndPositionPoiUid(String str) {
        this.k = str;
        return this;
    }

    public RoleOptions setOrderId(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: orderId is null.");
        }
        this.f3318b = str;
        return this;
    }

    public RoleOptions setRoleType(int i) {
        if (i == 0) {
            this.f3319c = i;
            return this;
        }
        throw new IllegalArgumentException("BDMapSDKException: role type is invalid: " + i);
    }

    public RoleOptions setStartPosition(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("BDMapSDKException: StartPosition is null, must be applied!");
        }
        if (SyncCoordinateConverter.CoordType.COMMON == this.f3322f) {
            latLng = a(latLng);
        }
        this.f3323g = latLng;
        return this;
    }

    public RoleOptions setStartPositionName(String str) {
        this.i = str;
        return this;
    }

    public RoleOptions setStartPositionPoiUid(String str) {
        this.f3324h = str;
        return this;
    }

    public RoleOptions setUserId(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("BDMapSDKException: user id is null");
        }
        this.f3321e = str;
        return this;
    }
}