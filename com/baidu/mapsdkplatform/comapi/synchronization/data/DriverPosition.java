package com.baidu.mapsdkplatform.comapi.synchronization.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.baidu.mapapi.model.LatLng;

/* JADX INFO: loaded from: classes.dex */
public final class DriverPosition implements Parcelable {
    public static final Parcelable.Creator<DriverPosition> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f3702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private LatLng f3703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private double f3704c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private double f3705d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3706e;

    public DriverPosition() {
        this.f3702a = null;
        this.f3703b = null;
        this.f3704c = 0.0d;
        this.f3705d = 0.0d;
        this.f3706e = 0;
    }

    protected DriverPosition(Parcel parcel) {
        this.f3702a = parcel.readString();
        this.f3703b = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.f3704c = parcel.readDouble();
        this.f3705d = parcel.readDouble();
        this.f3706e = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double getAngle() {
        return this.f3704c;
    }

    public int getOrderStateInPosition() {
        return this.f3706e;
    }

    public LatLng getPoint() {
        return this.f3703b;
    }

    public double getSpeed() {
        return this.f3705d;
    }

    public String getTimeStamp() {
        return this.f3702a;
    }

    public void setAngle(double d2) {
        double d3 = 0.0d;
        if (d2 >= 0.0d) {
            d3 = 360.0d;
            if (d2 < 360.0d) {
                this.f3704c = d2;
                return;
            }
        }
        this.f3704c = d3;
    }

    public void setOrderStateInPosition(int i) {
        this.f3706e = i;
    }

    public void setPoint(LatLng latLng) {
        this.f3703b = latLng;
    }

    public void setSpeed(double d2) {
        this.f3705d = d2;
    }

    public void setTimeStamp(String str) {
        this.f3702a = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3702a);
        parcel.writeParcelable(this.f3703b, i);
        parcel.writeDouble(this.f3704c);
        parcel.writeDouble(this.f3705d);
        parcel.writeInt(this.f3706e);
    }
}