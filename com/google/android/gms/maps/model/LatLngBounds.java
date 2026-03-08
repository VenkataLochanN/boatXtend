package com.google.android.gms.maps.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.GoogleMapOptions;

/* JADX INFO: loaded from: classes2.dex */
public final class LatLngBounds extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LatLngBounds> CREATOR = new zze();
    public final LatLng northeast;
    public final LatLng southwest;

    public static final class Builder {
        private double zzdg = Double.POSITIVE_INFINITY;
        private double zzdh = Double.NEGATIVE_INFINITY;
        private double zzdi = Double.NaN;
        private double zzdj = Double.NaN;

        public final LatLngBounds build() {
            Preconditions.checkState(!Double.isNaN(this.zzdi), "no included points");
            return new LatLngBounds(new LatLng(this.zzdg, this.zzdi), new LatLng(this.zzdh, this.zzdj));
        }

        public final Builder include(LatLng latLng) {
            this.zzdg = Math.min(this.zzdg, latLng.latitude);
            this.zzdh = Math.max(this.zzdh, latLng.latitude);
            double d2 = latLng.longitude;
            if (!Double.isNaN(this.zzdi)) {
                double d3 = this.zzdi;
                double d4 = this.zzdj;
                boolean z = false;
                if (d3 > d4 ? d3 <= d2 || d2 <= d4 : d3 <= d2 && d2 <= d4) {
                    z = true;
                }
                if (!z) {
                    if (LatLngBounds.zza(this.zzdi, d2) < LatLngBounds.zzb(this.zzdj, d2)) {
                        this.zzdi = d2;
                    }
                }
                return this;
            }
            this.zzdi = d2;
            this.zzdj = d2;
            return this;
        }
    }

    public LatLngBounds(LatLng latLng, LatLng latLng2) {
        Preconditions.checkNotNull(latLng, "null southwest");
        Preconditions.checkNotNull(latLng2, "null northeast");
        Preconditions.checkArgument(latLng2.latitude >= latLng.latitude, "southern latitude exceeds northern latitude (%s > %s)", Double.valueOf(latLng.latitude), Double.valueOf(latLng2.latitude));
        this.southwest = latLng;
        this.northeast = latLng2;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static LatLngBounds createFromAttributes(Context context, AttributeSet attributeSet) {
        return GoogleMapOptions.zza(context, attributeSet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zza(double d2, double d3) {
        return ((d2 - d3) + 360.0d) % 360.0d;
    }

    private final boolean zza(double d2) {
        return this.southwest.longitude <= this.northeast.longitude ? this.southwest.longitude <= d2 && d2 <= this.northeast.longitude : this.southwest.longitude <= d2 || d2 <= this.northeast.longitude;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static double zzb(double d2, double d3) {
        return ((d3 - d2) + 360.0d) % 360.0d;
    }

    public final boolean contains(LatLng latLng) {
        double d2 = latLng.latitude;
        return ((this.southwest.latitude > d2 ? 1 : (this.southwest.latitude == d2 ? 0 : -1)) <= 0 && (d2 > this.northeast.latitude ? 1 : (d2 == this.northeast.latitude ? 0 : -1)) <= 0) && zza(latLng.longitude);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof LatLngBounds)) {
            return false;
        }
        LatLngBounds latLngBounds = (LatLngBounds) obj;
        return this.southwest.equals(latLngBounds.southwest) && this.northeast.equals(latLngBounds.northeast);
    }

    public final LatLng getCenter() {
        double d2 = (this.southwest.latitude + this.northeast.latitude) / 2.0d;
        double d3 = this.northeast.longitude;
        double d4 = this.southwest.longitude;
        if (d4 > d3) {
            d3 += 360.0d;
        }
        return new LatLng(d2, (d3 + d4) / 2.0d);
    }

    public final int hashCode() {
        return Objects.hashCode(this.southwest, this.northeast);
    }

    public final LatLngBounds including(LatLng latLng) {
        double dMin = Math.min(this.southwest.latitude, latLng.latitude);
        double dMax = Math.max(this.northeast.latitude, latLng.latitude);
        double d2 = this.northeast.longitude;
        double d3 = this.southwest.longitude;
        double d4 = latLng.longitude;
        if (!zza(d4)) {
            if (zza(d3, d4) < zzb(d2, d4)) {
                d3 = d4;
            } else {
                d2 = d4;
            }
        }
        return new LatLngBounds(new LatLng(dMin, d3), new LatLng(dMax, d2));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("southwest", this.southwest).add("northeast", this.northeast).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.southwest, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.northeast, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}