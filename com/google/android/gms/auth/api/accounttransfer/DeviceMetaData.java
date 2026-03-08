package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes.dex */
public class DeviceMetaData extends AbstractSafeParcelable {
    public static final Parcelable.Creator<DeviceMetaData> CREATOR = new zzy();
    private boolean zzcc;
    private long zzcd;
    private final boolean zzce;
    private final int zzy;

    DeviceMetaData(int i, boolean z, long j, boolean z2) {
        this.zzy = i;
        this.zzcc = z;
        this.zzcd = j;
        this.zzce = z2;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzcd;
    }

    public boolean isChallengeAllowed() {
        return this.zzce;
    }

    public boolean isLockScreenSolved() {
        return this.zzcc;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzy);
        SafeParcelWriter.writeBoolean(parcel, 2, isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel, 3, getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel, 4, isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}