package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzp implements Parcelable.Creator<DataSourcesRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSourcesRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        IBinder iBinder = null;
        boolean z = false;
        ArrayList<Integer> arrayListCreateIntegerList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
            } else if (fieldId == 2) {
                arrayListCreateIntegerList = SafeParcelReader.createIntegerList(parcel, header);
            } else if (fieldId == 3) {
                z = SafeParcelReader.readBoolean(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataSourcesRequest(arrayListCreateTypedList, arrayListCreateIntegerList, z, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSourcesRequest[] newArray(int i) {
        return new DataSourcesRequest[i];
    }
}