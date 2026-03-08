package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class IndoorRouteLine extends RouteLine<IndoorRouteStep> {
    public static final Parcelable.Creator<IndoorRouteLine> CREATOR = new g();

    public static class IndoorRouteStep extends RouteStep {

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private RouteNode f3237d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private RouteNode f3238e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private String f3239f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f3240g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f3241h;
        private List<IndoorStepNode> i;
        private List<Double> j;

        public static class IndoorStepNode {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private String f3242a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private int f3243b;

            /* JADX INFO: renamed from: c, reason: collision with root package name */
            private LatLng f3244c;

            /* JADX INFO: renamed from: d, reason: collision with root package name */
            private String f3245d;

            public String getDetail() {
                return this.f3245d;
            }

            public LatLng getLocation() {
                return this.f3244c;
            }

            public String getName() {
                return this.f3242a;
            }

            public int getType() {
                return this.f3243b;
            }

            public void setDetail(String str) {
                this.f3245d = str;
            }

            public void setLocation(LatLng latLng) {
                this.f3244c = latLng;
            }

            public void setName(String str) {
                this.f3242a = str;
            }

            public void setType(int i) {
                this.f3243b = i;
            }
        }

        private List<LatLng> a(List<Double> list) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < list.size(); i += 2) {
                arrayList.add(new LatLng(list.get(i).doubleValue(), list.get(i + 1).doubleValue()));
            }
            return arrayList;
        }

        public String getBuildingId() {
            return this.f3241h;
        }

        public RouteNode getEntrace() {
            return this.f3237d;
        }

        public RouteNode getExit() {
            return this.f3238e;
        }

        public String getFloorId() {
            return this.f3240g;
        }

        public String getInstructions() {
            return this.f3239f;
        }

        public List<IndoorStepNode> getStepNodes() {
            return this.i;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = a(this.j);
            }
            return this.mWayPoints;
        }

        public void setBuildingId(String str) {
            this.f3241h = str;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f3237d = routeNode;
        }

        public void setExit(RouteNode routeNode) {
            this.f3238e = routeNode;
        }

        public void setFloorId(String str) {
            this.f3240g = str;
        }

        public void setInstructions(String str) {
            this.f3239f = str;
        }

        public void setPath(List<Double> list) {
            this.j = list;
        }

        public void setStepNodes(List<IndoorStepNode> list) {
            this.i = list;
        }
    }

    public IndoorRouteLine() {
        setType(RouteLine.TYPE.WALKSTEP);
    }

    protected IndoorRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<IndoorRouteStep> getAllStep() {
        return super.getAllStep();
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}