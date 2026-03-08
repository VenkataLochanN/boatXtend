package com.amap.api.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.amap.api.mapcore.util.er;

/* JADX INFO: loaded from: classes.dex */
public final class LatLngBounds implements Parcelable {
    private static final String CLASSNAME = "LatLngBounds";
    public static final LatLngBoundsCreator CREATOR = new LatLngBoundsCreator();
    private final int mVersionCode;
    public final LatLng northeast;
    public final LatLng southwest;

    /* JADX INFO: Access modifiers changed from: private */
    public static double c(double d2, double d3) {
        return ((d2 - d3) + 360.0d) % 360.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double d(double d2, double d3) {
        return ((d3 - d2) + 360.0d) % 360.0d;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    LatLngBounds(int i, LatLng latLng, LatLng latLng2) {
        boolean z;
        try {
        } catch (Throwable th) {
            Log.e(CLASSNAME, "the structure parameters are illegal!");
            th.printStackTrace();
            z = false;
        }
        if (latLng == null) {
            throw new RuntimeRemoteException("null southwest");
        }
        if (latLng2 == null) {
            throw new RuntimeRemoteException("null northeast");
        }
        if (latLng2.latitude < latLng.latitude) {
            throw new RuntimeRemoteException("southern latitude exceeds northern latitude (" + latLng.latitude + " > " + latLng2.latitude + ")");
        }
        z = true;
        this.mVersionCode = z ? i : 0;
        this.southwest = z ? latLng : null;
        this.northeast = z ? latLng2 : null;
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        this(1, latLng, latLng2);
    }

    int a() {
        return this.mVersionCode;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean contains(LatLng latLng) {
        if (latLng == null) {
            return false;
        }
        if (this.northeast != null && this.southwest != null) {
            return a(latLng.latitude) && b(latLng.longitude);
        }
        Log.e(CLASSNAME, "current LatLngBounds is invalid, please check the structure parameters are legal");
        return false;
    }

    public boolean contains(LatLngBounds latLngBounds) {
        return latLngBounds != null && contains(latLngBounds.southwest) && contains(latLngBounds.northeast);
    }

    public boolean intersects(LatLngBounds latLngBounds) {
        if (latLngBounds == null) {
            return false;
        }
        if (this.northeast != null && this.southwest != null) {
            return a(latLngBounds) || latLngBounds.a(this);
        }
        Log.e(CLASSNAME, "current LatLngBounds is invalid, please check the structure parameters are legal");
        return false;
    }

    private boolean a(LatLngBounds latLngBounds) {
        LatLng latLng;
        if (latLngBounds == null || (latLng = latLngBounds.northeast) == null || latLngBounds.southwest == null) {
            return false;
        }
        return Math.abs(((latLng.longitude + latLngBounds.southwest.longitude) - this.northeast.longitude) - this.southwest.longitude) < ((this.northeast.longitude - this.southwest.longitude) + latLngBounds.northeast.longitude) - this.southwest.longitude && Math.abs(((latLngBounds.northeast.latitude + latLngBounds.southwest.latitude) - this.northeast.latitude) - this.southwest.latitude) < ((this.northeast.latitude - this.southwest.latitude) + latLngBounds.northeast.latitude) - latLngBounds.southwest.latitude;
    }

    public LatLngBounds including(LatLng latLng) {
        LatLng latLng2;
        double d2;
        if (latLng == null) {
            return this;
        }
        if (this.northeast == null || (latLng2 = this.southwest) == null) {
            Log.e(CLASSNAME, "current LatLngBounds is invalid, please check the structure parameters are legal");
            return this;
        }
        double dMin = Math.min(latLng2.latitude, latLng.latitude);
        double dMax = Math.max(this.northeast.latitude, latLng.latitude);
        double d3 = this.northeast.longitude;
        double d4 = this.southwest.longitude;
        double d5 = latLng.longitude;
        if (b(d5)) {
            d2 = d3;
        } else if (c(d4, d5) < d(d3, d5)) {
            d4 = d5;
            d2 = d3;
        } else {
            d2 = d5;
        }
        try {
            return new LatLngBounds(new LatLng(dMin, d4, false), new LatLng(dMax, d2, false));
        } catch (Throwable th) {
            th.printStackTrace();
            return this;
        }
    }

    private boolean a(double d2) {
        return this.southwest.latitude <= d2 && d2 <= this.northeast.latitude;
    }

    private boolean b(double d2) {
        return this.southwest.longitude <= this.northeast.longitude ? this.southwest.longitude <= d2 && d2 <= this.northeast.longitude : this.southwest.longitude <= d2 || d2 <= this.northeast.longitude;
    }

    public int hashCode() {
        return er.a(new Object[]{this.southwest, this.northeast});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public String toString() {
        return er.a(er.a("southwest", this.southwest), er.a("northeast", this.northeast));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        LatLngBoundsCreator.a(this, parcel, i);
    }

    public static final class Builder {
        private double mSouth = Double.POSITIVE_INFINITY;
        private double mNorth = Double.NEGATIVE_INFINITY;
        private double mWest = Double.NaN;
        private double mEast = Double.NaN;

        public Builder include(LatLng latLng) {
            if (latLng == null) {
                return this;
            }
            this.mSouth = Math.min(this.mSouth, latLng.latitude);
            this.mNorth = Math.max(this.mNorth, latLng.latitude);
            double d2 = latLng.longitude;
            if (Double.isNaN(this.mWest)) {
                this.mWest = d2;
                this.mEast = d2;
            } else if (!a(d2)) {
                if (LatLngBounds.c(this.mWest, d2) < LatLngBounds.d(this.mEast, d2)) {
                    this.mWest = d2;
                } else {
                    this.mEast = d2;
                }
            }
            return this;
        }

        private boolean a(double d2) {
            double d3 = this.mWest;
            double d4 = this.mEast;
            return d3 <= d4 ? d3 <= d2 && d2 <= d4 : d3 <= d2 || d2 <= d4;
        }

        public LatLngBounds build() {
            if (Double.isNaN(this.mWest)) {
                Log.w(LatLngBounds.CLASSNAME, "no included points");
                return null;
            }
            double d2 = this.mWest;
            double d3 = this.mEast;
            if (d2 > d3) {
                this.mWest = d3;
                this.mEast = d2;
            }
            double d4 = this.mSouth;
            double d5 = this.mNorth;
            if (d4 > d5) {
                this.mSouth = d5;
                this.mNorth = d4;
            }
            return new LatLngBounds(new LatLng(this.mSouth, this.mWest, false), new LatLng(this.mNorth, this.mEast, false));
        }
    }
}