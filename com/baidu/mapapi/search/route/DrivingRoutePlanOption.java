package com.baidu.mapapi.search.route;

import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrivingRoutePlanOption {
    public String mCityName;
    public PlanNode mFrom = null;
    public PlanNode mTo = null;
    public DrivingPolicy mPolicy = DrivingPolicy.ECAR_TIME_FIRST;
    public List<PlanNode> mWayPoints = null;
    public DrivingTrafficPolicy mtrafficPolicy = DrivingTrafficPolicy.ROUTE_PATH;

    public enum DrivingPolicy {
        ECAR_AVOID_JAM(3),
        ECAR_TIME_FIRST(0),
        ECAR_DIS_FIRST(1),
        ECAR_FEE_FIRST(2);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3228a;

        DrivingPolicy(int i) {
            this.f3228a = i;
        }

        public int getInt() {
            return this.f3228a;
        }
    }

    public enum DrivingTrafficPolicy {
        ROUTE_PATH(0),
        ROUTE_PATH_AND_TRAFFIC(1);


        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f3230a;

        DrivingTrafficPolicy(int i) {
            this.f3230a = i;
        }

        public int getInt() {
            return this.f3230a;
        }
    }

    public DrivingRoutePlanOption currentCity(String str) {
        this.mCityName = str;
        return this;
    }

    public DrivingRoutePlanOption from(PlanNode planNode) {
        this.mFrom = planNode;
        return this;
    }

    public DrivingRoutePlanOption passBy(List<PlanNode> list) {
        this.mWayPoints = list;
        return this;
    }

    public DrivingRoutePlanOption policy(DrivingPolicy drivingPolicy) {
        this.mPolicy = drivingPolicy;
        return this;
    }

    public DrivingRoutePlanOption to(PlanNode planNode) {
        this.mTo = planNode;
        return this;
    }

    public DrivingRoutePlanOption trafficPolicy(DrivingTrafficPolicy drivingTrafficPolicy) {
        this.mtrafficPolicy = drivingTrafficPolicy;
        return this;
    }
}