package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.maps.zzaf;
import com.google.android.gms.internal.maps.zzag;

/* JADX INFO: loaded from: classes2.dex */
public final class TileOverlayOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
    private float zzcr;
    private boolean zzcs;
    private float zzcz;
    private zzaf zzeh;
    private TileProvider zzei;
    private boolean zzej;

    public TileOverlayOptions() {
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0.0f;
    }

    TileOverlayOptions(IBinder iBinder, boolean z, float f2, boolean z2, float f3) {
        this.zzcs = true;
        this.zzej = true;
        this.zzcz = 0.0f;
        this.zzeh = zzag.zzk(iBinder);
        this.zzei = this.zzeh == null ? null : new zzs(this);
        this.zzcs = z;
        this.zzcr = f2;
        this.zzej = z2;
        this.zzcz = f3;
    }

    public final TileOverlayOptions fadeIn(boolean z) {
        this.zzej = z;
        return this;
    }

    public final boolean getFadeIn() {
        return this.zzej;
    }

    public final TileProvider getTileProvider() {
        return this.zzei;
    }

    public final float getTransparency() {
        return this.zzcz;
    }

    public final float getZIndex() {
        return this.zzcr;
    }

    public final boolean isVisible() {
        return this.zzcs;
    }

    public final TileOverlayOptions tileProvider(TileProvider tileProvider) {
        this.zzei = tileProvider;
        this.zzeh = this.zzei == null ? null : new zzt(this, tileProvider);
        return this;
    }

    public final TileOverlayOptions transparency(float f2) {
        Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Transparency must be in the range [0..1]");
        this.zzcz = f2;
        return this;
    }

    public final TileOverlayOptions visible(boolean z) {
        this.zzcs = z;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zzeh.asBinder(), false);
        SafeParcelWriter.writeBoolean(parcel, 3, isVisible());
        SafeParcelWriter.writeFloat(parcel, 4, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 5, getFadeIn());
        SafeParcelWriter.writeFloat(parcel, 6, getTransparency());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final TileOverlayOptions zIndex(float f2) {
        this.zzcr = f2;
        return this;
    }
}