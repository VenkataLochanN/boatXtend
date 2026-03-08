package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.fitness.data.DataSource;

/* JADX INFO: loaded from: classes.dex */
public final class zzew extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzew> CREATOR = new zzex();
    private final DataSource zzr;

    public zzew(DataSource dataSource) {
        this.zzr = dataSource;
    }

    public final DataSource getDataSource() {
        return this.zzr;
    }

    public final String toString() {
        return String.format("ApplicationUnregistrationRequest{%s}", this.zzr);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzr, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}