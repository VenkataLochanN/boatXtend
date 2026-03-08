package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataSourcesRequest;

/* JADX INFO: loaded from: classes.dex */
public final class zzce extends zza implements zzcd {
    zzce(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitSensorsApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(DataSourcesRequest dataSourcesRequest) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, dataSourcesRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(com.google.android.gms.fitness.request.zzao zzaoVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzaoVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcd
    public final void zza(com.google.android.gms.fitness.request.zzar zzarVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzarVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }
}