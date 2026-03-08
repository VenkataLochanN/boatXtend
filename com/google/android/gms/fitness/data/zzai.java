package com.google.android.gms.fitness.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes.dex */
public final class zzai implements Parcelable.Creator<Value> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Value createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        boolean z = false;
        String strCreateString = null;
        Bundle bundleCreateBundle = null;
        int[] iArrCreateIntArray = null;
        float[] fArrCreateFloatArray = null;
        byte[] bArrCreateByteArray = null;
        float f2 = 0.0f;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 2:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 3:
                    f2 = SafeParcelReader.readFloat(parcel, header);
                    break;
                case 4:
                    strCreateString = SafeParcelReader.createString(parcel, header);
                    break;
                case 5:
                    bundleCreateBundle = SafeParcelReader.createBundle(parcel, header);
                    break;
                case 6:
                    iArrCreateIntArray = SafeParcelReader.createIntArray(parcel, header);
                    break;
                case 7:
                    fArrCreateFloatArray = SafeParcelReader.createFloatArray(parcel, header);
                    break;
                case 8:
                    bArrCreateByteArray = SafeParcelReader.createByteArray(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new Value(i, z, f2, strCreateString, bundleCreateBundle, iArrCreateIntArray, fArrCreateFloatArray, bArrCreateByteArray);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ Value[] newArray(int i) {
        return new Value[i];
    }
}