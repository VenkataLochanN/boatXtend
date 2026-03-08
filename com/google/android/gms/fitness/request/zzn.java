package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Device;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzn implements Parcelable.Creator<DataReadRequest> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadRequest createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
        boolean z2 = false;
        ArrayList arrayListCreateTypedList = null;
        ArrayList arrayListCreateTypedList2 = null;
        ArrayList arrayListCreateTypedList3 = null;
        ArrayList arrayListCreateTypedList4 = null;
        DataSource dataSource = null;
        IBinder iBinder = null;
        ArrayList arrayListCreateTypedList5 = null;
        ArrayList<Integer> arrayListCreateIntegerList = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
                    break;
                case 2:
                    arrayListCreateTypedList2 = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
                    break;
                case 3:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 4:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 5:
                    arrayListCreateTypedList3 = SafeParcelReader.createTypedList(parcel, header, DataType.CREATOR);
                    break;
                case 6:
                    arrayListCreateTypedList4 = SafeParcelReader.createTypedList(parcel, header, DataSource.CREATOR);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 9:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
                    break;
                case 10:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 11:
                case 15:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 12:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 13:
                    z2 = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 14:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 16:
                    arrayListCreateTypedList5 = SafeParcelReader.createTypedList(parcel, header, Device.CREATOR);
                    break;
                case 17:
                    arrayListCreateIntegerList = SafeParcelReader.createIntegerList(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataReadRequest(arrayListCreateTypedList, arrayListCreateTypedList2, j, j2, arrayListCreateTypedList3, arrayListCreateTypedList4, i, j3, dataSource, i2, z, z2, iBinder, arrayListCreateTypedList5, arrayListCreateIntegerList);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataReadRequest[] newArray(int i) {
        return new DataReadRequest[i];
    }
}