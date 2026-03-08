package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Session;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzj implements Parcelable.Creator<DataDeleteRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataDeleteRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        boolean z = false;
        boolean z2 = false;
        long j = 0;
        long j2 = 0;
        ArrayList arrayListCreateTypedList = null;
        ArrayList arrayListCreateTypedList2 = null;
        ArrayList arrayListCreateTypedList3 = null;
        IBinder iBinder = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 2:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 3:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
                    break;
                case 4:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
                    break;
                case 5:
                    arrayListCreateTypedList3 = SafeParcelReader.createTypedList(parcel, header, Session.CREATOR);
                    break;
                case 6:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 7:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 8:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataDeleteRequest(j, j2, arrayListCreateTypedList, arrayListCreateTypedList2, arrayListCreateTypedList3, z, z2, iBinder);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataDeleteRequest[] newArray(int i) {
        return new DataDeleteRequest[i];
    }
}