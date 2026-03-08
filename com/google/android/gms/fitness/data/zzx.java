package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Goal;

/* JADX INFO: loaded from: classes.dex */
public final class zzx implements Parcelable.Creator<Goal.MetricObjective> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.MetricObjective createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        double d2 = 0.0d;
        double d3 = 0.0d;
        String strCreateString = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 2) {
                d2 = SafeParcelReader.readDouble(parcel, header);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                d3 = SafeParcelReader.readDouble(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Goal.MetricObjective(strCreateString, d2, d3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Goal.MetricObjective[] newArray(int i) {
        return new Goal.MetricObjective[i];
    }
}