package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class zzv implements Parcelable.Creator<zzu> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        zzw zzwVar = null;
        String strCreateString = null;
        String strCreateString2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i2 = 1;
            if (fieldId != 1) {
                i2 = 2;
                if (fieldId != 2) {
                    i2 = 3;
                    if (fieldId != 3) {
                        i2 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, header);
                        } else {
                            strCreateString2 = SafeParcelReader.createString(parcel, header);
                        }
                    } else {
                        strCreateString = SafeParcelReader.createString(parcel, header);
                    }
                } else {
                    zzwVar = (zzw) SafeParcelReader.createParcelable(parcel, header, zzw.CREATOR);
                }
            } else {
                i = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i2));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzu(hashSet, i, zzwVar, strCreateString, strCreateString2);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzu[] newArray(int i) {
        return new zzu[i];
    }
}