package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.AMapException;
import com.amazon.identity.auth.device.dataobject.AppInfo;

/* JADX INFO: loaded from: classes.dex */
public final class LatLng implements Parcelable, Cloneable {
    public static final LatLngCreator CREATOR = new LatLngCreator();
    public final double latitude;
    public final double longitude;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng(double d2, double d3) {
        this(d2, d3, true);
    }

    public LatLng(double d2, double d3, boolean z) {
        if (z) {
            if (-180.0d <= d3 && d3 < 180.0d) {
                this.longitude = d3;
            } else {
                this.longitude = ((((d3 - 180.0d) % 360.0d) + 360.0d) % 360.0d) - 180.0d;
            }
            if (d2 < -90.0d || d2 > 90.0d) {
                try {
                    throw new AMapException(AMapException.ERROR_ILLEGAL_VALUE);
                } catch (AMapException e2) {
                    e2.printStackTrace();
                }
            }
            this.latitude = Math.max(-90.0d, Math.min(90.0d, d2));
            return;
        }
        this.latitude = d2;
        this.longitude = d3;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LatLng m11clone() {
        return new LatLng(this.latitude, this.longitude);
    }

    public int hashCode() {
        long jDoubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32))) + 31;
        long jDoubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        return (i * 31) + ((int) ((jDoubleToLongBits2 >>> 32) ^ jDoubleToLongBits2));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLng)) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        return Double.doubleToLongBits(this.latitude) == Double.doubleToLongBits(latLng.latitude) && Double.doubleToLongBits(this.longitude) == Double.doubleToLongBits(latLng.longitude);
    }

    public String toString() {
        return "lat/lng: (" + this.latitude + AppInfo.DELIM + this.longitude + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.latitude);
    }
}