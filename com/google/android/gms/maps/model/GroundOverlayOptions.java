package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes2.dex */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    private float bearing;
    private float height;
    private float width;
    private float zzcr;
    private boolean zzcs;
    private boolean zzct;
    private BitmapDescriptor zzcw;
    private LatLng zzcx;
    private LatLngBounds zzcy;
    private float zzcz;
    private float zzda;
    private float zzdb;

    public GroundOverlayOptions() {
        this.zzcs = true;
        this.zzcz = 0.0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
    }

    GroundOverlayOptions(IBinder iBinder, LatLng latLng, float f2, float f3, LatLngBounds latLngBounds, float f4, float f5, boolean z, float f6, float f7, float f8, boolean z2) {
        this.zzcs = true;
        this.zzcz = 0.0f;
        this.zzda = 0.5f;
        this.zzdb = 0.5f;
        this.zzct = false;
        this.zzcw = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.zzcx = latLng;
        this.width = f2;
        this.height = f3;
        this.zzcy = latLngBounds;
        this.bearing = f4;
        this.zzcr = f5;
        this.zzcs = z;
        this.zzcz = f6;
        this.zzda = f7;
        this.zzdb = f8;
        this.zzct = z2;
    }

    private final GroundOverlayOptions zza(LatLng latLng, float f2, float f3) {
        this.zzcx = latLng;
        this.width = f2;
        this.height = f3;
        return this;
    }

    public final GroundOverlayOptions anchor(float f2, float f3) {
        this.zzda = f2;
        this.zzdb = f3;
        return this;
    }

    public final GroundOverlayOptions bearing(float f2) {
        this.bearing = ((f2 % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    public final GroundOverlayOptions clickable(boolean z) {
        this.zzct = z;
        return this;
    }

    public final float getAnchorU() {
        return this.zzda;
    }

    public final float getAnchorV() {
        return this.zzdb;
    }

    public final float getBearing() {
        return this.bearing;
    }

    public final LatLngBounds getBounds() {
        return this.zzcy;
    }

    public final float getHeight() {
        return this.height;
    }

    public final BitmapDescriptor getImage() {
        return this.zzcw;
    }

    public final LatLng getLocation() {
        return this.zzcx;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final float getWidth() {
        return this.width;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final GroundOverlayOptions image(BitmapDescriptor bitmapDescriptor) {
        Preconditions.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.zzcw = bitmapDescriptor;
        return this;
    }

    public final boolean isClickable() {
        return this.zzct;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final GroundOverlayOptions position(LatLng latLng, float f2) {
        Preconditions.checkState(this.zzcy == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f2 >= 0.0f, "Width must be non-negative");
        return zza(latLng, f2, -1.0f);
    }

    public final GroundOverlayOptions position(LatLng latLng, float f2, float f3) {
        Preconditions.checkState(this.zzcy == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f2 >= 0.0f, "Width must be non-negative");
        Preconditions.checkArgument(f3 >= 0.0f, "Height must be non-negative");
        return zza(latLng, f2, f3);
    }

    public final GroundOverlayOptions positionFromBounds(LatLngBounds latLngBounds) {
        boolean z = this.zzcx == null;
        String strValueOf = String.valueOf(this.zzcx);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 46);
        sb.append("Position has already been set using position: ");
        sb.append(strValueOf);
        Preconditions.checkState(z, sb.toString());
        this.zzcy = latLngBounds;
        return this;
    }

    public final GroundOverlayOptions transparency(float f2) {
        Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzcz = f2;
        return this;
    }

    public final GroundOverlayOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzcw.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getLocation(), i, false);
        SafeParcelWriter.writeFloat(parcel, 4, getWidth());
        SafeParcelWriter.writeFloat(parcel, 5, getHeight());
        SafeParcelWriter.writeParcelable(parcel, 6, getBounds(), i, false);
        SafeParcelWriter.writeFloat(parcel, 7, getBearing());
        SafeParcelWriter.writeFloat(parcel, 8, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeFloat(parcel, 10, getTransparency());
        SafeParcelWriter.writeFloat(parcel, 11, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final GroundOverlayOptions zIndex(float f2) {
        this.zzcr = f2;
        return this;
    }
}