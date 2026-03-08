package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzbg implements Parcelable.Creator<StartBleScanRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StartBleScanRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayListCreateTypedList = null;
        IBinder iBinder = null;
        int i = 0;
        IBinder iBinder2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
            } else if (fieldId == 2) {
                iBinder2 = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 3) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new StartBleScanRequest(arrayListCreateTypedList, iBinder2, i, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ StartBleScanRequest[] newArray(int i) {
        return new StartBleScanRequest[i];
    }
}