package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.Field;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzr implements Parcelable.Creator<DataTypeCreateRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataTypeCreateRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        ArrayList arrayListCreateTypedList = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 2) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, Field.CREATOR);
            } else if (fieldId != 3) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataTypeCreateRequest(strCreateString, arrayListCreateTypedList, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataTypeCreateRequest[] newArray(int i) {
        return new DataTypeCreateRequest[i];
    }
}