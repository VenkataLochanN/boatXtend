package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzad implements Parcelable.Creator<Session> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Session createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        long j = 0;
        long j2 = 0;
        String strCreateString = null;
        String strCreateString2 = null;
        String strCreateString3 = null;
        zzb zzbVar = null;
        Long longObject = null;
        int i = 0;
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
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 4:
                    strCreateString2 = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    strCreateString3 = SafeParcelReader.createString(parcel, header);
                    break;
                case 6:
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
                case 7:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 8:
                    zzbVar = (zzb) SafeParcelReader.createParcelable(parcel, header, zzb.CREATOR);
                    break;
                case 9:
                    longObject = SafeParcelReader.readLongObject(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Session(j, j2, strCreateString, strCreateString2, strCreateString3, i, zzbVar, longObject);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Session[] newArray(int i) {
        return new Session[i];
    }
}