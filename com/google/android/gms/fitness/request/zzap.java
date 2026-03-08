package com.google.android.gms.fitness.request;

import android.app.PendingIntent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class zzap implements Parcelable.Creator<zzao> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzao createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        long j = 0;
        long j2 = 0;
        long j3 = 0;
        long j4 = 0;
        DataSource dataSource = null;
        DataType dataType = null;
        IBinder iBinder = null;
        PendingIntent pendingIntent = null;
        ArrayList arrayListCreateTypedList = null;
        IBinder iBinder2 = null;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 1:
                    dataSource = (DataSource) SafeParcelReader.createParcelable(parcel, header, DataSource.CREATOR);
                    break;
                case 2:
                    dataType = (DataType) SafeParcelReader.createParcelable(parcel, header, DataType.CREATOR);
                    break;
                case 3:
                    iBinder = SafeParcelReader.readIBinder(parcel, header);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 6:
                    j = SafeParcelReader.readLong(parcel, header);
                    break;
                case 7:
                    j2 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 8:
                    pendingIntent = (PendingIntent) SafeParcelReader.createParcelable(parcel, header, PendingIntent.CREATOR);
                    break;
                case 9:
                    j3 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 10:
                    i3 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 11:
                    arrayListCreateTypedList = SafeParcelReader.createTypedList(parcel, header, LocationRequest.CREATOR);
                    break;
                case 12:
                    j4 = SafeParcelReader.readLong(parcel, header);
                    break;
                case 13:
                    iBinder2 = SafeParcelReader.readIBinder(parcel, header);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzao(dataSource, dataType, iBinder, i, i2, j, j2, pendingIntent, j3, i3, arrayListCreateTypedList, j4, iBinder2);
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzao[] newArray(int i) {
        return new zzao[i];
    }
}