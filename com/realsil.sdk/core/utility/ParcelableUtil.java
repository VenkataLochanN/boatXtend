package com.realsil.sdk.core.utility;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes3.dex */
public class ParcelableUtil {
    public static byte[] marshall(Parcelable parcelable) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.setDataPosition(0);
        parcelable.writeToParcel(parcelObtain, 0);
        byte[] bArrMarshall = parcelObtain.marshall();
        parcelObtain.recycle();
        return bArrMarshall;
    }

    public static Parcel unmarshall(byte[] bArr) {
        Parcel parcelObtain = Parcel.obtain();
        parcelObtain.unmarshall(bArr, 0, bArr.length);
        parcelObtain.setDataPosition(0);
        return parcelObtain;
    }
}