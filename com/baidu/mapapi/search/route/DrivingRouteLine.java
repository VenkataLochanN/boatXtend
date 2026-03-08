package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DrivingRouteLine extends RouteLine<DrivingStep> implements Parcelable {
    public static final Parcelable.Creator<DrivingRouteLine> CREATOR = new d();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f3218b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<RouteNode> f3219c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3220d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3221e;

    public static class DrivingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<DrivingStep> CREATOR = new e();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        List<LatLng> f3222d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int[] f3223e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private int f3224f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private RouteNode f3225g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private RouteNode f3226h;
        private String i;
        private String j;
        private String k;
        private String l;
        private int m;

        public DrivingStep() {
        }

        protected DrivingStep(Parcel parcel) {
            super(parcel);
            this.f3224f = parcel.readInt();
            this.f3225g = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3226h = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.i = parcel.readString();
            this.j = parcel.readString();
            this.k = parcel.readString();
            this.l = parcel.readString();
            this.m = parcel.readInt();
            this.f3222d = parcel.createTypedArrayList(LatLng.CREATOR);
            this.f3223e = parcel.createIntArray();
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3224f;
        }

        public RouteNode getEntrance() {
            return this.f3225g;
        }

        public String getEntranceInstructions() {
            return this.j;
        }

        public RouteNode getExit() {
            return this.f3226h;
        }

        public String getExitInstructions() {
            return this.k;
        }

        public String getInstructions() {
            return this.l;
        }

        public int getNumTurns() {
            return this.m;
        }

        public int[] getTrafficList() {
            return this.f3223e;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = CoordUtil.decodeLocationList(this.i);
            }
            return this.f3222d;
        }

        public void setDirection(int i) {
            this.f3224f = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3225g = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.j = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3226h = routeNode;
        }

        public void setExitInstructions(String str) {
            this.k = str;
        }

        public void setInstructions(String str) {
            this.l = str;
        }

        public void setNumTurns(int i) {
            this.m = i;
        }

        public void setPathList(List<LatLng> list) {
            this.f3222d = list;
        }

        public void setPathString(String str) {
            this.i = str;
        }

        public void setTrafficList(int[] iArr) {
            this.f3223e = iArr;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.f3224f);
            parcel.writeParcelable(this.f3225g, 1);
            parcel.writeParcelable(this.f3226h, 1);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
            parcel.writeString(this.k);
            parcel.writeString(this.l);
            parcel.writeInt(this.m);
            parcel.writeTypedList(this.f3222d);
            parcel.writeIntArray(this.f3223e);
        }
    }

    public DrivingRouteLine() {
    }

    protected DrivingRouteLine(Parcel parcel) {
        super(parcel);
        this.f3218b = parcel.readByte() != 0;
        this.f3219c = new ArrayList();
        parcel.readList(this.f3219c, RouteNode.class.getClassLoader());
        this.f3220d = parcel.readInt();
        this.f3221e = parcel.readInt();
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getCongestionDistance() {
        return this.f3220d;
    }

    public int getLightNum() {
        return this.f3221e;
    }

    public List<RouteNode> getWayPoints() {
        return this.f3219c;
    }

    @Deprecated
    public boolean isSupportTraffic() {
        return this.f3218b;
    }

    public void setCongestionDistance(int i) {
        this.f3220d = i;
    }

    public void setLightNum(int i) {
        this.f3221e = i;
    }

    public void setSupportTraffic(boolean z) {
        this.f3218b = z;
    }

    public void setWayPoints(List<RouteNode> list) {
        this.f3219c = list;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.DRIVESTEP);
        super.writeToParcel(parcel, i);
        parcel.writeByte(this.f3218b ? (byte) 1 : (byte) 0);
        parcel.writeList(this.f3219c);
        parcel.writeInt(this.f3220d);
        parcel.writeInt(this.f3221e);
    }
}