package com.google.android.gms.fitness.data;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class zzv extends com.google.android.gms.internal.fitness.zza implements zzt {
    zzv(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.data.IDataSourceListener");
    }

    @Override // com.google.android.gms.fitness.data.zzt
    public final void zzc(DataPoint dataPoint) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        com.google.android.gms.internal.fitness.zzc.zza(parcelObtainAndWriteInterfaceToken, dataPoint);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}