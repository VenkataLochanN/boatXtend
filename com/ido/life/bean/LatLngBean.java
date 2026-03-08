package com.ido.life.bean;

import android.os.Parcel;
import android.os.Parcelable;
import java.io.Serializable;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class LatLngBean implements Serializable, Cloneable, Parcelable {
    public static final Parcelable.Creator<LatLngBean> CREATOR = new Parcelable.Creator<LatLngBean>() { // from class: com.ido.life.bean.LatLngBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLngBean createFromParcel(Parcel parcel) {
            return new LatLngBean(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public LatLngBean[] newArray(int i) {
            return new LatLngBean[i];
        }
    };
    public double altitude;
    private int color;
    public String currentTimeMillis;
    public boolean isGps;
    public double latitude;
    public double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected LatLngBean(Parcel parcel) {
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.altitude = parcel.readDouble();
        this.isGps = parcel.readByte() != 0;
        this.currentTimeMillis = parcel.readString();
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LatLngBean m22clone() {
        try {
            return (LatLngBean) super.clone();
        } catch (Exception unused) {
            return null;
        }
    }

    public double getAltitude() {
        return this.altitude;
    }

    public void setAltitude(double d2) {
        this.altitude = d2;
    }

    public LatLngBean(double d2, double d3) {
        this.latitude = d2;
        this.longitude = d3;
    }

    public LatLngBean() {
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d2) {
        this.latitude = d2;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d2) {
        this.longitude = d2;
    }

    public String getCurrentTimeMillis() {
        return this.currentTimeMillis;
    }

    public void setCurrentTimeMillis(String str) {
        this.currentTimeMillis = str;
    }

    public int getColor() {
        return this.color;
    }

    public void setColor(int i) {
        this.color = i;
    }

    public String toString() {
        return "LatLngBean{latitude=" + this.latitude + ", longitude=" + this.longitude + ", currentTimeMillis=" + this.currentTimeMillis + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.altitude);
        parcel.writeByte(this.isGps ? (byte) 1 : (byte) 0);
        parcel.writeString(this.currentTimeMillis);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLngBean latLngBean = (LatLngBean) obj;
        return Double.compare(latLngBean.latitude, this.latitude) == 0 && Double.compare(latLngBean.longitude, this.longitude) == 0 && Double.compare(latLngBean.altitude, this.altitude) == 0 && this.isGps == latLngBean.isGps && Objects.equals(this.currentTimeMillis, latLngBean.currentTimeMillis);
    }

    public int hashCode() {
        return Objects.hash(Double.valueOf(this.latitude), Double.valueOf(this.longitude), Double.valueOf(this.altitude), Boolean.valueOf(this.isGps), this.currentTimeMillis);
    }
}