package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class WalkingRouteLine extends RouteLine<WalkingStep> implements Parcelable {
    public static final Parcelable.Creator<WalkingRouteLine> CREATOR = new r();

    public static class WalkingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<WalkingStep> CREATOR = new s();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3293d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private RouteNode f3294e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private RouteNode f3295f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f3296g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f3297h;
        private String i;
        private String j;

        public WalkingStep() {
        }

        protected WalkingStep(Parcel parcel) {
            super(parcel);
            this.f3293d = parcel.readInt();
            this.f3294e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3295f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3296g = parcel.readString();
            this.f3297h = parcel.readString();
            this.i = parcel.readString();
            this.j = parcel.readString();
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3293d;
        }

        public RouteNode getEntrance() {
            return this.f3294e;
        }

        public String getEntranceInstructions() {
            return this.f3297h;
        }

        public RouteNode getExit() {
            return this.f3295f;
        }

        public String getExitInstructions() {
            return this.i;
        }

        public String getInstructions() {
            return this.j;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.f3296g);
            }
            return this.mWayPoints;
        }

        public void setDirection(int i) {
            this.f3293d = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3294e = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.f3297h = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3295f = routeNode;
        }

        public void setExitInstructions(String str) {
            this.i = str;
        }

        public void setInstructions(String str) {
            this.j = str;
        }

        public void setPathString(String str) {
            this.f3296g = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f3293d);
            parcel.writeParcelable(this.f3294e, 1);
            parcel.writeParcelable(this.f3295f, 1);
            parcel.writeString(this.f3296g);
            parcel.writeString(this.f3297h);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
        }
    }

    public WalkingRouteLine() {
    }

    protected WalkingRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<WalkingStep> getAllStep() {
        return super.getAllStep();
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.WALKSTEP);
        super.writeToParcel(parcel, 1);
    }
}