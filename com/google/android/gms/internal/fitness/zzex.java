package com.google.android.gms.internal.fitness;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;

/* JADX INFO: loaded from: classes.dex */
public final class zzex implements Parcelable.Creator<zzew> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataSource dataSource = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzew(dataSource);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzew[] newArray(int i) {
        return new zzew[i];
    }
}