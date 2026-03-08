package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;

/* JADX INFO: loaded from: classes.dex */
public final class zzp implements Parcelable.Creator<Goal.DurationObjective> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.DurationObjective createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            if (SafeParcelReader.getFieldId(header) != 1) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                j = SafeParcelReader.readLong(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Goal.DurationObjective(j);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.DurationObjective[] newArray(int i) {
        return new Goal.DurationObjective[i];
    }
}