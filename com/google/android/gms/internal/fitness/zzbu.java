package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.request.StartBleScanRequest;

/* JADX INFO: loaded from: classes.dex */
public final class zzbu extends zza implements zzbt {
    zzbu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitBleApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(StartBleScanRequest startBleScanRequest) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, startBleScanRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzah zzahVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzahVar);
        transactAndReadExceptionReturnVoid(5, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzbh zzbhVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzbhVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zzbl zzblVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzblVar);
        transactAndReadExceptionReturnVoid(4, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzbt
    public final void zza(com.google.android.gms.fitness.request.zze zzeVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzeVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }
}