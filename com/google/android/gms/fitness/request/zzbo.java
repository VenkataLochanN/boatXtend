package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;

/* JADX INFO: loaded from: classes.dex */
public final class zzbo implements Parcelable.Creator<zzbn> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbn createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                dataType = (DataType) SafeParcelReader.createParcelable(parcel, header, DataType.CREATOR);
            } else if (fieldId == 2) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzbn(dataType, dataSource, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzbn[] newArray(int i) {
        return new zzbn[i];
    }
}