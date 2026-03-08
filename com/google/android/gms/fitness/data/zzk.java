package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzk implements Parcelable.Creator<DataSource> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        DataType dataType = null;
        String strCreateString = null;
        Device device = null;
        zzb zzbVar = null;
        String strCreateString2 = null;
        int[] iArrCreateIntArray = null;
        int i = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    dataType = (DataType) SafeParcelReader.createParcelable(parcel, header, DataType.CREATOR);
                    break;
                case 2:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 3:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 4:
                    device = (Device) SafeParcelReader.createParcelable(parcel, header, Device.CREATOR);
                    break;
                case 5:
                    zzbVar = (zzb) SafeParcelReader.createParcelable(parcel, header, zzb.CREATOR);
                    break;
                case 6:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 7:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 8:
                    iArrCreateIntArray = SafeParcelReader.createIntArray(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new DataSource(dataType, strCreateString, i, device, zzbVar, strCreateString2, iArrCreateIntArray);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ DataSource[] newArray(int i) {
        return new DataSource[i];
    }
}