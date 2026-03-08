package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.VehicleInfo;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class TransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Parcelable.Creator<TransitRouteLine> CREATOR = new o();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private TaxiInfo f3282b;

    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<TransitStep> CREATOR = new p();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private VehicleInfo f3283d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private RouteNode f3284e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private RouteNode f3285f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private TransitRouteStepType f3286g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f3287h;
        private String i;

        public enum TransitRouteStepType {
            BUSLINE,
            SUBWAY,
            WAKLING
        }

        public TransitStep() {
        }

        protected TransitStep(Parcel parcel) {
            super(parcel);
            this.f3283d = (VehicleInfo) parcel.readParcelable(VehicleInfo.class.getClassLoader());
            this.f3284e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3285f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            int i = parcel.readInt();
            this.f3286g = i == -1 ? null : TransitRouteStepType.values()[i];
            this.f3287h = parcel.readString();
            this.i = parcel.readString();
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public RouteNode getEntrance() {
            return this.f3284e;
        }

        public RouteNode getExit() {
            return this.f3285f;
        }

        public String getInstructions() {
            return this.f3287h;
        }

        public TransitRouteStepType getStepType() {
            return this.f3286g;
        }

        public VehicleInfo getVehicleInfo() {
            return this.f3283d;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.i);
            }
            return this.mWayPoints;
        }

        public void setEntrace(RouteNode routeNode) {
            this.f3284e = routeNode;
        }

        public void setExit(RouteNode routeNode) {
            this.f3285f = routeNode;
        }

        public void setInstructions(String str) {
            this.f3287h = str;
        }

        public void setPathString(String str) {
            this.i = str;
        }

        public void setStepType(TransitRouteStepType transitRouteStepType) {
            this.f3286g = transitRouteStepType;
        }

        public void setVehicleInfo(VehicleInfo vehicleInfo) {
            this.f3283d = vehicleInfo;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeParcelable(this.f3283d, 1);
            parcel.writeParcelable(this.f3284e, 1);
            parcel.writeParcelable(this.f3285f, 1);
            TransitRouteStepType transitRouteStepType = this.f3286g;
            parcel.writeInt(transitRouteStepType == null ? -1 : transitRouteStepType.ordinal());
            parcel.writeString(this.f3287h);
            parcel.writeString(this.i);
        }
    }

    public TransitRouteLine() {
    }

    protected TransitRouteLine(Parcel parcel) {
        super(parcel);
        this.f3282b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public TaxiInfo getTaxitInfo() {
        return this.f3282b;
    }

    public void setTaxitInfo(TaxiInfo taxiInfo) {
        this.f3282b = taxiInfo;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.TRANSITSTEP);
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f3282b, 1);
    }
}