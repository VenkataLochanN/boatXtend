package com.google.android.gms.fitness.data;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzy implements Parcelable.Creator<RawBucket> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawBucket createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        Session session = null;
        ArrayList arrayListCreateTypedList = null;
        long j = 0;
        long j2 = 0;
        int i = 0;
        int i2 = 0;
        boolean z = false;
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
                    session = (Session) SafeParcelReader.createParcelable(parcel, header, Session.CREATOR);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, RawDataSet.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new RawBucket(j, j2, session, i, arrayListCreateTypedList, i2, z);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ RawBucket[] newArray(int i) {
        return new RawBucket[i];
    }
}