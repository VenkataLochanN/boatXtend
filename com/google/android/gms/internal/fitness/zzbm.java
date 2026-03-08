package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzbm extends zza implements zzbk {
    zzbm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbk
    public final void zza(DataSourcesResult dataSourcesResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, dataSourcesResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}