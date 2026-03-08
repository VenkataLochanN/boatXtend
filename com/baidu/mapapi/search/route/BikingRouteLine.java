package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BikingRouteLine extends RouteLine<BikingStep> implements Parcelable {
    public static final Parcelable.Creator<BikingRouteLine> CREATOR = new a();

    public static class BikingStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<BikingStep> CREATOR = new b();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f3211d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private RouteNode f3212e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private RouteNode f3213f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private String f3214g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private String f3215h;
        private String i;
        private String j;
        private String k;

        public BikingStep() {
        }

        protected BikingStep(Parcel parcel) {
            super(parcel);
            this.f3211d = parcel.readInt();
            this.f3212e = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3213f = (RouteNode) parcel.readParcelable(RouteNode.class.getClassLoader());
            this.f3214g = parcel.readString();
            this.f3215h = parcel.readString();
            this.i = parcel.readString();
            this.j = parcel.readString();
            this.k = parcel.readString();
        }

        private List<LatLng> a(String str) {
            if (str != null && str.length() != 0) {
                ArrayList arrayList = new ArrayList();
                String[] strArrSplit = str.split(";");
                if (strArrSplit != null && strArrSplit.length != 0) {
                    for (String str2 : strArrSplit) {
                        String[] strArrSplit2 = str2.split(AppInfo.DELIM);
                        if (strArrSplit2 != null && strArrSplit2.length >= 2) {
                            LatLng latLng = new LatLng(Double.valueOf(strArrSplit2[1]).doubleValue(), Double.valueOf(strArrSplit2[0]).doubleValue());
                            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                                latLng = CoordTrans.baiduToGcj(latLng);
                            }
                            arrayList.add(latLng);
                        }
                    }
                    return arrayList;
                }
            }
            return null;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public int getDirection() {
            return this.f3211d;
        }

        public RouteNode getEntrance() {
            return this.f3212e;
        }

        public String getEntranceInstructions() {
            return this.f3215h;
        }

        public RouteNode getExit() {
            return this.f3213f;
        }

        public String getExitInstructions() {
            return this.i;
        }

        public String getInstructions() {
            return this.j;
        }

        public String getTurnType() {
            return this.k;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = a(this.f3214g);
            }
            return this.mWayPoints;
        }

        public void setDirection(int i) {
            this.f3211d = i;
        }

        public void setEntrance(RouteNode routeNode) {
            this.f3212e = routeNode;
        }

        public void setEntranceInstructions(String str) {
            this.f3215h = str;
        }

        public void setExit(RouteNode routeNode) {
            this.f3213f = routeNode;
        }

        public void setExitInstructions(String str) {
            this.i = str;
        }

        public void setInstructions(String str) {
            this.j = str;
        }

        public void setPathString(String str) {
            this.f3214g = str;
        }

        public void setTurnType(String str) {
            this.k = str;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, 1);
            parcel.writeInt(this.f3211d);
            parcel.writeParcelable(this.f3212e, 1);
            parcel.writeParcelable(this.f3213f, 1);
            parcel.writeString(this.f3214g);
            parcel.writeString(this.f3215h);
            parcel.writeString(this.i);
            parcel.writeString(this.j);
            parcel.writeString(this.k);
        }
    }

    public BikingRouteLine() {
    }

    protected BikingRouteLine(Parcel parcel) {
        super(parcel);
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine
    public List<BikingStep> getAllStep() {
        return super.getAllStep();
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.setType(RouteLine.TYPE.BIKINGSTEP);
        super.writeToParcel(parcel, 1);
    }
}