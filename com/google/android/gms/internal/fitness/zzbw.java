package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.DataTypeCreateRequest;

/* JADX INFO: loaded from: classes.dex */
public final class zzbw extends zza implements zzbv {
    zzbw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(DataTypeCreateRequest dataTypeCreateRequest) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, dataTypeCreateRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(com.google.android.gms.fitness.request.zzaa zzaaVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzaaVar);
        transactAndReadExceptionReturnVoid(22, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbv
    public final void zza(com.google.android.gms.fitness.request.zzs zzsVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzsVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }
}