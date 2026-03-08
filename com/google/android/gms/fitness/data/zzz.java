package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzz implements Parcelable.Creator<RawDataPoint> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawDataPoint createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        Value[] valueArr = null;
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
                    valueArr = (Value[]) SafeParcelReader.createTypedArray(parcel, header, Value.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 7:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new RawDataPoint(j, j2, valueArr, i, i2, j3, j4);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawDataPoint[] newArray(int i) {
        return new RawDataPoint[i];
    }
}