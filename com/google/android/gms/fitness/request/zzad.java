package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzad implements Parcelable.Creator<GoalsReadRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoalsReadRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 2) {
                SafeParcelReader.readList(parcel, header, arrayList, getClass().getClassLoader());
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, header, arrayList2, getClass().getClassLoader());
            } else if (fieldId != 4) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                SafeParcelReader.readList(parcel, header, arrayList3, getClass().getClassLoader());
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new GoalsReadRequest(iBinder, arrayList, arrayList2, arrayList3);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ GoalsReadRequest[] newArray(int i) {
        return new GoalsReadRequest[i];
    }
}