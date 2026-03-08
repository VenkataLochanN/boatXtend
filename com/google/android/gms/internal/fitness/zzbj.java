package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataReadResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzbj extends zza implements zzbh {
    zzbj(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbh
    public final void zza(DataReadResult dataReadResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, dataReadResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}