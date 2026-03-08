package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import com.amazon.identity.auth.device.dataobject.AppInfo;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.RouteStep;
import com.baidu.mapapi.search.core.TrainInfo;
import com.baidu.mapsdkplatform.comapi.util.CoordTrans;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MassTransitRouteLine extends RouteLine<TransitStep> implements Parcelable {
    public static final Parcelable.Creator<MassTransitRouteLine> CREATOR = new i();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f3247b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f3248c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private List<PriceInfo> f3249d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private List<List<TransitStep>> f3250e;

    public static class TransitStep extends RouteStep implements Parcelable {
        public static final Parcelable.Creator<TransitStep> CREATOR = new j();

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private List<TrafficCondition> f3251d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private LatLng f3252e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        private LatLng f3253f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private TrainInfo f3254g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private PlaneInfo f3255h;
        private CoachInfo i;
        private BusInfo j;
        private StepVehicleInfoType k;
        private String l;
        private String m;

        public enum StepVehicleInfoType {
            ESTEP_TRAIN(1),
            ESTEP_PLANE(2),
            ESTEP_BUS(3),
            ESTEP_DRIVING(4),
            ESTEP_WALK(5),
            ESTEP_COACH(6);


            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private int f3256a;

            StepVehicleInfoType(int i) {
                this.f3256a = 0;
                this.f3256a = i;
            }

            public int getInt() {
                return this.f3256a;
            }
        }

        public static class TrafficCondition implements Parcelable {
            public static final Parcelable.Creator<TrafficCondition> CREATOR = new k();

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            private int f3257a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            private int f3258b;

            public TrafficCondition() {
            }

            protected TrafficCondition(Parcel parcel) {
                this.f3257a = parcel.readInt();
                this.f3258b = parcel.readInt();
            }

            @Override // android.os.Parcelable
            public int describeContents() {
                return 0;
            }

            public int getTrafficGeoCnt() {
                return this.f3258b;
            }

            public int getTrafficStatus() {
                return this.f3257a;
            }

            public void setTrafficGeoCnt(int i) {
                this.f3258b = i;
            }

            public void setTrafficStatus(int i) {
                this.f3257a = i;
            }

            @Override // android.os.Parcelable
            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeInt(this.f3257a);
                parcel.writeInt(this.f3258b);
            }
        }

        public TransitStep() {
        }

        protected TransitStep(Parcel parcel) {
            StepVehicleInfoType stepVehicleInfoType;
            super(parcel);
            this.f3251d = parcel.createTypedArrayList(TrafficCondition.CREATOR);
            this.f3252e = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3253f = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3254g = (TrainInfo) parcel.readParcelable(TrainInfo.class.getClassLoader());
            this.f3255h = (PlaneInfo) parcel.readParcelable(PlaneInfo.class.getClassLoader());
            this.i = (CoachInfo) parcel.readParcelable(CoachInfo.class.getClassLoader());
            this.j = (BusInfo) parcel.readParcelable(BusInfo.class.getClassLoader());
            switch (parcel.readInt()) {
                case 1:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_TRAIN;
                    break;
                case 2:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_PLANE;
                    break;
                case 3:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_BUS;
                    break;
                case 4:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_DRIVING;
                    break;
                case 5:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_WALK;
                    break;
                case 6:
                    stepVehicleInfoType = StepVehicleInfoType.ESTEP_COACH;
                    break;
                default:
                    this.l = parcel.readString();
                    this.m = parcel.readString();
            }
            this.k = stepVehicleInfoType;
            this.l = parcel.readString();
            this.m = parcel.readString();
        }

        private List<LatLng> a(String str) {
            String[] strArrSplit;
            ArrayList arrayList = new ArrayList();
            String[] strArrSplit2 = str.split(";");
            if (strArrSplit2 != null) {
                for (int i = 0; i < strArrSplit2.length; i++) {
                    if (strArrSplit2[i] != null && strArrSplit2[i] != "" && (strArrSplit = strArrSplit2[i].split(AppInfo.DELIM)) != null && strArrSplit[1] != "" && strArrSplit[0] != "") {
                        LatLng latLng = new LatLng(Double.parseDouble(strArrSplit[1]), Double.parseDouble(strArrSplit[0]));
                        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                            latLng = CoordTrans.baiduToGcj(latLng);
                        }
                        arrayList.add(latLng);
                    }
                }
            }
            return arrayList;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public BusInfo getBusInfo() {
            return this.j;
        }

        public CoachInfo getCoachInfo() {
            return this.i;
        }

        public LatLng getEndLocation() {
            return this.f3253f;
        }

        public String getInstructions() {
            return this.l;
        }

        public PlaneInfo getPlaneInfo() {
            return this.f3255h;
        }

        public LatLng getStartLocation() {
            return this.f3252e;
        }

        public List<TrafficCondition> getTrafficConditions() {
            return this.f3251d;
        }

        public TrainInfo getTrainInfo() {
            return this.f3254g;
        }

        public StepVehicleInfoType getVehileType() {
            return this.k;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep
        public List<LatLng> getWayPoints() {
            if (this.mWayPoints == null) {
                this.mWayPoints = a(this.m);
            }
            return this.mWayPoints;
        }

        public void setBusInfo(BusInfo busInfo) {
            this.j = busInfo;
        }

        public void setCoachInfo(CoachInfo coachInfo) {
            this.i = coachInfo;
        }

        public void setEndLocation(LatLng latLng) {
            this.f3253f = latLng;
        }

        public void setInstructions(String str) {
            this.l = str;
        }

        public void setPathString(String str) {
            this.m = str;
        }

        public void setPlaneInfo(PlaneInfo planeInfo) {
            this.f3255h = planeInfo;
        }

        public void setStartLocation(LatLng latLng) {
            this.f3252e = latLng;
        }

        public void setTrafficConditions(List<TrafficCondition> list) {
            this.f3251d = list;
        }

        public void setTrainInfo(TrainInfo trainInfo) {
            this.f3254g = trainInfo;
        }

        public void setVehileType(StepVehicleInfoType stepVehicleInfoType) {
            this.k = stepVehicleInfoType;
        }

        @Override // com.baidu.mapapi.search.core.RouteStep, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeTypedList(this.f3251d);
            parcel.writeParcelable(this.f3252e, i);
            parcel.writeParcelable(this.f3253f, i);
            parcel.writeParcelable(this.f3254g, i);
            parcel.writeParcelable(this.f3255h, i);
            parcel.writeParcelable(this.i, i);
            parcel.writeParcelable(this.j, i);
            parcel.writeInt(this.k.getInt());
            parcel.writeString(this.l);
            parcel.writeString(this.m);
        }
    }

    public MassTransitRouteLine() {
        this.f3250e = null;
    }

    protected MassTransitRouteLine(Parcel parcel) {
        super(parcel);
        this.f3250e = null;
        int i = parcel.readInt();
        this.f3247b = parcel.readString();
        this.f3248c = parcel.readDouble();
        this.f3249d = parcel.createTypedArrayList(PriceInfo.CREATOR);
        if (i > 0) {
            this.f3250e = new ArrayList();
            for (int i2 = 0; i2 < i; i2++) {
                this.f3250e.add(parcel.createTypedArrayList(TransitStep.CREATOR));
            }
        }
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getArriveTime() {
        return this.f3247b;
    }

    public List<List<TransitStep>> getNewSteps() {
        return this.f3250e;
    }

    public double getPrice() {
        return this.f3248c;
    }

    public List<PriceInfo> getPriceInfo() {
        return this.f3249d;
    }

    public void setArriveTime(String str) {
        this.f3247b = str;
    }

    public void setNewSteps(List<List<TransitStep>> list) {
        this.f3250e = list;
    }

    public void setPrice(double d2) {
        this.f3248c = d2;
    }

    public void setPriceInfo(List<PriceInfo> list) {
        this.f3249d = list;
    }

    @Override // com.baidu.mapapi.search.core.RouteLine, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        List<List<TransitStep>> list = this.f3250e;
        parcel.writeInt(list == null ? 0 : list.size());
        parcel.writeString(this.f3247b);
        parcel.writeDouble(this.f3248c);
        parcel.writeTypedList(this.f3249d);
        Iterator<List<TransitStep>> it = this.f3250e.iterator();
        while (it.hasNext()) {
            parcel.writeTypedList(it.next());
        }
    }
}