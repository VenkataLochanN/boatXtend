package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class zzcc extends zza implements zzcb {
    zzcc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoogleFitRecordingApi");
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzaj zzajVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzajVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzbj zzbjVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzbjVar);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.fitness.zzcb
    public final void zza(com.google.android.gms.fitness.request.zzbn zzbnVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, zzbnVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }
}