package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class zzp implements Parcelable.Creator<zzo> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        HashSet hashSet = new HashSet();
        int i = 0;
        ArrayList arrayListCreateTypedList = null;
        zzr zzrVar = null;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            int i3 = 1;
            if (fieldId != 1) {
                i3 = 2;
                if (fieldId != 2) {
                    i3 = 3;
                    if (fieldId != 3) {
                        i3 = 4;
                        if (fieldId != 4) {
                            SafeParcelReader.skipUnknownField(parcel, header);
                        } else {
                            zzrVar = (zzr) SafeParcelReader.createParcelable(parcel, header, zzr.CREATOR);
                        }
                    } else {
                        i2 = SafeParcelReader.readInt(parcel, header);
                    }
                } else {
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, zzu.CREATOR);
                }
            } else {
                i = SafeParcelReader.readInt(parcel, header);
            }
            hashSet.add(Integer.valueOf(i3));
        }
        if (parcel.dataPosition() == iValidateObjectHeader) {
            return new zzo(hashSet, i, arrayListCreateTypedList, i2, zzrVar);
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Overread allowed size end=");
        sb.append(iValidateObjectHeader);
        throw new SafeParcelReader.ParseException(sb.toString(), parcel);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzo[] newArray(int i) {
        return new zzo[i];
    }
}