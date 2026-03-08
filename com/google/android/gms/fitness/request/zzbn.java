package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzcr;

/* JADX INFO: loaded from: classes.dex */
public final class zzbn extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbn> CREATOR = new zzbo();
    private final zzcq zzgj;
    private final DataType zzq;
    private final DataSource zzr;

    zzbn(DataType dataType, DataSource dataSource, IBinder iBinder) {
        this.zzq = dataType;
        this.zzr = dataSource;
        this.zzgj = zzcr.zzj(iBinder);
    }

    public zzbn(DataType dataType, DataSource dataSource, zzcq zzcqVar) {
        this.zzq = dataType;
        this.zzr = dataSource;
        this.zzgj = zzcqVar;
    }

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof zzbn) {
                zzbn zzbnVar = (zzbn) obj;
                if (Objects.equal(this.zzr, zzbnVar.zzr) && Objects.equal(this.zzq, zzbnVar.zzq)) {
                }
            }
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzr, this.zzq);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzq, i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzr, i, false);
        zzcq zzcqVar = this.zzgj;
        SafeParcelWriter.writeIBinder(parcel, 3, zzcqVar == null ? null : zzcqVar.asBinder(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}