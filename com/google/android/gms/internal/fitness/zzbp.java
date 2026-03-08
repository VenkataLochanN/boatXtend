package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataTypeResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzbp extends zza implements zzbn {
    zzbp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataTypeCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbn
    public final void zza(DataTypeResult dataTypeResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, dataTypeResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}