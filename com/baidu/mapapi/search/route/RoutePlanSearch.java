package com.baidu.mapapi.search.route;

import com.baidu.mapapi.BMapManager;

/* JADX INFO: loaded from: classes.dex */
public class RoutePlanSearch extends com.baidu.mapapi.search.core.l {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3275b = false;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.baidu.platform.core.d.e f3274a = new com.baidu.platform.core.d.j();

    RoutePlanSearch() {
    }

    public static RoutePlanSearch newInstance() {
        BMapManager.init();
        return new RoutePlanSearch();
    }

    public boolean bikingSearch(BikingRoutePlanOption bikingRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (bikingRoutePlanOption == null || bikingRoutePlanOption.mTo == null || bikingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin or destination can not be null");
        }
        if (bikingRoutePlanOption.mFrom.getLocation() == null && (bikingRoutePlanOption.mFrom.getName() == null || bikingRoutePlanOption.mFrom.getName() == "")) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin is illegal");
        }
        if (bikingRoutePlanOption.mTo.getLocation() == null && (bikingRoutePlanOption.mTo.getName() == null || bikingRoutePlanOption.mTo.getName() == "")) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , destination is illegal");
        }
        return this.f3274a.a(bikingRoutePlanOption);
    }

    public void destroy() {
        if (this.f3275b) {
            return;
        }
        this.f3275b = true;
        this.f3274a.a();
        BMapManager.destroy();
    }

    public boolean drivingSearch(DrivingRoutePlanOption drivingRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (drivingRoutePlanOption == null || drivingRoutePlanOption.mTo == null || drivingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option , origin or destination can not be null");
        }
        return this.f3274a.a(drivingRoutePlanOption);
    }

    public boolean masstransitSearch(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (massTransitRoutePlanOption == null || massTransitRoutePlanOption.mTo == null || massTransitRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin or destination can not be null");
        }
        if (massTransitRoutePlanOption.mFrom.getLocation() == null && (massTransitRoutePlanOption.mFrom.getName() == null || massTransitRoutePlanOption.mFrom.getCity() == null)) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin is illegal");
        }
        if (massTransitRoutePlanOption.mTo.getLocation() == null && (massTransitRoutePlanOption.mTo.getName() == null || massTransitRoutePlanOption.mTo.getCity() == null)) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,destination is illegal");
        }
        return this.f3274a.a(massTransitRoutePlanOption);
    }

    public void setOnGetRoutePlanResultListener(OnGetRoutePlanResultListener onGetRoutePlanResultListener) {
        com.baidu.platform.core.d.e eVar = this.f3274a;
        if (eVar == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (onGetRoutePlanResultListener == null) {
            throw new IllegalArgumentException("BDMapSDKException: listener can not be null");
        }
        eVar.a(onGetRoutePlanResultListener);
    }

    public boolean transitSearch(TransitRoutePlanOption transitRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (transitRoutePlanOption == null || transitRoutePlanOption.mCityName == null || transitRoutePlanOption.mTo == null || transitRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: route plan option,origin or destination or city can not be null");
        }
        return this.f3274a.a(transitRoutePlanOption);
    }

    public boolean walkingIndoorSearch(IndoorRoutePlanOption indoorRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (indoorRoutePlanOption == null || indoorRoutePlanOption.mTo == null || indoorRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: option , origin or destination can not be null");
        }
        return this.f3274a.a(indoorRoutePlanOption);
    }

    public boolean walkingSearch(WalkingRoutePlanOption walkingRoutePlanOption) {
        if (this.f3274a == null) {
            throw new IllegalStateException("BDMapSDKException: RoutePlanSearch is null, please call newInstance() first.");
        }
        if (walkingRoutePlanOption == null || walkingRoutePlanOption.mTo == null || walkingRoutePlanOption.mFrom == null) {
            throw new IllegalArgumentException("BDMapSDKException: option , origin or destination can not be null");
        }
        return this.f3274a.a(walkingRoutePlanOption);
    }
}