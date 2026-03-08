package com.baidu.mapapi.synchronization.histroytrace;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.synchronization.SyncCoordinateConverter;
import java.util.List;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class HistoryTraceData implements Parcelable {
    public static final Parcelable.Creator<HistoryTraceData> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f3331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f3332b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f3333c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f3334d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private LatLng f3335e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private LatLng f3336f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private SyncCoordinateConverter.CoordType f3337g = SyncCoordinateConverter.CoordType.BD09LL;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private List<HistoryTracePoint> f3338h;
    private int i;

    public static class HistoryTracePoint implements Parcelable {
        public static final Parcelable.Creator<HistoryTracePoint> CREATOR = new b();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private LatLng f3339a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private long f3340b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f3341c;

        public HistoryTracePoint() {
        }

        protected HistoryTracePoint(Parcel parcel) {
            this.f3339a = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
            this.f3340b = parcel.readLong();
            this.f3341c = parcel.readString();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public String getCreateTime() {
            return this.f3341c;
        }

        public long getLocationTime() {
            return this.f3340b;
        }

        public LatLng getPoint() {
            return this.f3339a;
        }

        public void setCreateTime(String str) {
            this.f3341c = str;
        }

        public void setLocationTime(long j) {
            this.f3340b = j;
        }

        public void setPoint(LatLng latLng) {
            this.f3339a = latLng;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.f3339a, i);
            parcel.writeLong(this.f3340b);
            parcel.writeString(this.f3341c);
        }
    }

    public HistoryTraceData() {
    }

    protected HistoryTraceData(Parcel parcel) {
        this.f3331a = parcel.readInt();
        this.f3332b = parcel.readDouble();
        this.f3333c = parcel.readDouble();
        this.f3334d = parcel.readInt();
        this.f3335e = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f3336f = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f3338h = parcel.createTypedArrayList(HistoryTracePoint.CREATOR);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SyncCoordinateConverter.CoordType getCoordType() {
        return this.f3337g;
    }

    public int getCurrentOrderState() {
        return this.f3334d;
    }

    public int getCurrentPageIndex() {
        return this.i;
    }

    public double getDistance() {
        return this.f3332b;
    }

    public LatLng getOrderEndPosition() {
        return this.f3336f;
    }

    public LatLng getOrderStartPosition() {
        return this.f3335e;
    }

    public List<HistoryTracePoint> getPointsList() {
        return this.f3338h;
    }

    public double getTollDiatance() {
        return this.f3333c;
    }

    public int getTotalPoints() {
        return this.f3331a;
    }

    public void setCoordType(SyncCoordinateConverter.CoordType coordType) {
        this.f3337g = coordType;
    }

    public void setCurrentOrderState(int i) {
        this.f3334d = i;
    }

    public void setCurrentPageIndex(int i) {
        this.i = i;
    }

    public void setDistance(double d2) {
        this.f3332b = d2;
    }

    public void setOrderEndPosition(LatLng latLng) {
        this.f3336f = latLng;
    }

    public void setOrderStartPosition(LatLng latLng) {
        this.f3335e = latLng;
    }

    public void setPointsList(List<HistoryTracePoint> list) {
        this.f3338h = list;
    }

    public void setTollDiatance(double d2) {
        this.f3333c = d2;
    }

    public void setTotalPoints(int i) {
        this.f3331a = i;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("HistoryTraceData: \n");
        stringBuffer.append("TotalPoints = ");
        stringBuffer.append(this.f3331a);
        stringBuffer.append("; Distance = ");
        stringBuffer.append(this.f3332b);
        stringBuffer.append("; TollDistance = ");
        stringBuffer.append(this.f3333c);
        stringBuffer.append("; CurrentOrderState = ");
        stringBuffer.append(this.f3334d);
        stringBuffer.append("; OrderStartPosition = ");
        stringBuffer.append(this.f3335e);
        stringBuffer.append("; OrderEndPosition = ");
        stringBuffer.append(this.f3336f);
        List<HistoryTracePoint> list = this.f3338h;
        if (list != null && !list.isEmpty()) {
            stringBuffer.append("\n#History Trace Points Info BEGIN# \n");
            for (int i = 0; i < this.f3338h.size(); i++) {
                HistoryTracePoint historyTracePoint = this.f3338h.get(i);
                if (historyTracePoint != null) {
                    stringBuffer.append("The ");
                    stringBuffer.append(i);
                    stringBuffer.append(" Point Info: ");
                    stringBuffer.append("Point = ");
                    stringBuffer.append(historyTracePoint.getPoint().toString());
                    stringBuffer.append("; LocationTime = ");
                    stringBuffer.append(historyTracePoint.getLocationTime());
                    stringBuffer.append("; CreateTime = ");
                    stringBuffer.append(historyTracePoint.getCreateTime());
                    stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
                }
            }
        }
        return stringBuffer.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3331a);
        parcel.writeDouble(this.f3332b);
        parcel.writeDouble(this.f3333c);
        parcel.writeInt(this.f3334d);
        parcel.writeParcelable(this.f3335e, i);
        parcel.writeParcelable(this.f3336f, i);
        parcel.writeTypedList(this.f3338h);
    }
}