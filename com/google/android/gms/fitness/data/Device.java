package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.Settings;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public final class Device extends AbstractSafeParcelable {
    public static final Parcelable.Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    private final int type;
    private final String zzce;
    private final String zzcf;
    private final String zzcg;
    private final int zzch;

    public Device(String str, String str2, String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    public Device(String str, String str2, String str3, int i, int i2) {
        this.zzce = (String) Preconditions.checkNotNull(str);
        this.zzcf = (String) Preconditions.checkNotNull(str2);
        if (str3 == null) {
            throw new IllegalStateException("Device UID is null.");
        }
        this.zzcg = str3;
        this.type = i;
        this.zzch = i2;
    }

    @Deprecated
    private Device(String str, String str2, String str3, String str4, int i, int i2) {
        this(str, str2, str4, i, 2);
    }

    public static Device getLocalDevice(Context context) {
        int iZza = com.google.android.gms.internal.fitness.zzi.zza(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, Build.VERSION.RELEASE, Settings.Secure.getString(context.getContentResolver(), "android_id"), iZza, 2);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Device)) {
            return false;
        }
        Device device = (Device) obj;
        return Objects.equal(this.zzce, device.zzce) && Objects.equal(this.zzcf, device.zzcf) && Objects.equal(this.zzcg, device.zzcg) && this.type == device.type && this.zzch == device.zzch;
    }

    public final String getManufacturer() {
        return this.zzce;
    }

    public final String getModel() {
        return this.zzcf;
    }

    final String getStreamIdentifier() {
        return String.format("%s:%s:%s", this.zzce, this.zzcf, this.zzcg);
    }

    public final int getType() {
        return this.type;
    }

    public final String getUid() {
        return this.zzcg;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzce, this.zzcf, this.zzcg, Integer.valueOf(this.type));
    }

    public final String toString() {
        return String.format("Device{%s:%s:%s}", getStreamIdentifier(), Integer.valueOf(this.type), Integer.valueOf(this.zzch));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.zzch);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}