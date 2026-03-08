package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzi implements Parcelable.Creator<DataSet> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSet createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        ArrayList arrayList = new ArrayList();
        int i = 0;
        DataSource dataSource = null;
        ArrayList arrayListCreateTypedList = null;
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 1) {
                dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
            } else if (fieldId == 1000) {
                i = SafeParcelReader.readInt(parcel, header);
            } else if (fieldId == 3) {
                SafeParcelReader.readList(parcel, header, arrayList, getClass().getClassLoader());
            } else if (fieldId == 4) {
                arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
            } else if (fieldId != 5) {
                SafeParcelReader.skipUnknownField(parcel, header);
            } else {
                z = SafeParcelReader.readBoolean(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataSet(i, dataSource, arrayList, arrayListCreateTypedList, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSet[] newArray(int i) {
        return new DataSet[i];
    }
}