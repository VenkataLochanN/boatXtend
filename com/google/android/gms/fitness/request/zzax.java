package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* JADX INFO: loaded from: classes.dex */
public final class zzax extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzax> CREATOR = new zzay();
    private final zzcq zzgj;
    private final PendingIntent zzhg;
    private final int zzig;

    zzax(PendingIntent pendingIntent, IBinder iBinder, int i) {
        this.zzhg = pendingIntent;
        this.zzgj = iBinder == null ? null : zzcr.zzj(iBinder);
        this.zzig = i;
    }

    public zzax(PendingIntent pendingIntent, zzcq zzcqVar, int i) {
        this.zzhg = pendingIntent;
        this.zzgj = zzcqVar;
        this.zzig = i;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzax) {
                zzax zzaxVar = (zzax) obj;
                if (this.zzig == zzaxVar.zzig && Objects.equal(this.zzhg, zzaxVar.zzhg)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzhg, Integer.valueOf(this.zzig));
    }

    public final String toString() {
        return Objects.toStringHelper(this).add(BaseGmsClient.KEY_PENDING_INTENT, this.zzhg).add("sessionRegistrationOption", Integer.valueOf(this.zzig)).toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhg, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 2, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzig);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}