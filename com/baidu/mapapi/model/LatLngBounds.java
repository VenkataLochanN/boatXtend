package com.baidu.mapapi.model;

import android.os.Parcel;
import android.os.Parcelable;
import org.apache.commons.io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public final class LatLngBounds implements Parcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new b();
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private double f3080a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private double f3081b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private double f3082c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private double f3083d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private boolean f3084e = true;

        public LatLngBounds build() {
            return new LatLngBounds(new LatLng(this.f3081b, this.f3083d), new LatLng(this.f3080a, this.f3082c));
        }

        public Builder include(LatLng latLng) {
            if (latLng == null) {
                return this;
            }
            if (this.f3084e) {
                this.f3084e = false;
                double d2 = latLng.latitude;
                this.f3080a = d2;
                this.f3081b = d2;
                double d3 = latLng.longitude;
                this.f3082c = d3;
                this.f3083d = d3;
            }
            double d4 = latLng.latitude;
            double d5 = latLng.longitude;
            if (d4 < this.f3080a) {
                this.f3080a = d4;
            }
            if (d4 > this.f3081b) {
                this.f3081b = d4;
            }
            if (d5 < this.f3082c) {
                this.f3082c = d5;
            }
            if (d5 > this.f3083d) {
                this.f3083d = d5;
            }
            return this;
        }
    }

    protected LatLngBounds(Parcel parcel) {
        this.northeast = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        this.southwest = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
    }

    LatLngBounds(LatLng latLng, LatLng latLng2) {
        this.northeast = latLng;
        this.southwest = latLng2;
    }

    public boolean contains(LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        double d2 = this.southwest.latitude;
        double d3 = this.northeast.latitude;
        double d4 = this.southwest.longitude;
        double d5 = this.northeast.longitude;
        double d6 = latLng.latitude;
        double d7 = latLng.longitude;
        return d6 >= d2 && d6 <= d3 && d7 >= d4 && d7 <= d5;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public LatLng getCenter() {
        return new LatLng(((this.northeast.latitude - this.southwest.latitude) / 2.0d) + this.southwest.latitude, ((this.northeast.longitude - this.southwest.longitude) / 2.0d) + this.southwest.longitude);
    }

    public String toString() {
        return "southwest: " + this.southwest.latitude + ", " + this.southwest.longitude + IOUtils.LINE_SEPARATOR_UNIX + "northeast: " + this.northeast.latitude + ", " + this.northeast.longitude;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.northeast, i);
        parcel.writeParcelable(this.southwest, i);
    }
}