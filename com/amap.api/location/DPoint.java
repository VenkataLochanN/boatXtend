package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class DPoint implements Parcelable {
    public static final Parcelable.Creator<DPoint> CREATOR = new Parcelable.Creator<DPoint>() { // from class: com.amap.api.location.DPoint.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DPoint createFromParcel(Parcel parcel) {
            return new DPoint(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DPoint[] newArray(int i) {
            return new DPoint[i];
        }
    };

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private double f96a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private double f97b;

    public DPoint() {
        this.f96a = 0.0d;
        this.f97b = 0.0d;
    }

    public DPoint(double d2, double d3) {
        this.f96a = 0.0d;
        this.f97b = 0.0d;
        d3 = d3 > 180.0d ? 180.0d : d3;
        d3 = d3 < -180.0d ? -180.0d : d3;
        d2 = d2 > 90.0d ? 90.0d : d2;
        d2 = d2 < -90.0d ? -90.0d : d2;
        this.f96a = d3;
        this.f97b = d2;
    }

    protected DPoint(Parcel parcel) {
        this.f96a = 0.0d;
        this.f97b = 0.0d;
        this.f96a = parcel.readDouble();
        this.f97b = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DPoint)) {
            return false;
        }
        DPoint dPoint = (DPoint) obj;
        return this.f97b == dPoint.f97b && this.f96a == dPoint.f96a;
    }

    public double getLatitude() {
        return this.f97b;
    }

    public double getLongitude() {
        return this.f96a;
    }

    public int hashCode() {
        return Double.valueOf((this.f97b + this.f96a) * 1000000.0d).intValue();
    }

    public void setLatitude(double d2) {
        if (d2 > 90.0d) {
            d2 = 90.0d;
        }
        if (d2 < -90.0d) {
            d2 = -90.0d;
        }
        this.f97b = d2;
    }

    public void setLongitude(double d2) {
        if (d2 > 180.0d) {
            d2 = 180.0d;
        }
        if (d2 < -180.0d) {
            d2 = -180.0d;
        }
        this.f96a = d2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f96a);
        parcel.writeDouble(this.f97b);
    }
}