package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public final class zzad extends zzd implements zzac {
    zzad(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferService");
    }

    @Override // com.google.android.gms.internal.auth.zzac
    public final void zzd(zzaa zzaaVar, zzae zzaeVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaeVar);
        transactAndReadExceptionReturnVoid(9, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzac
    public final void zzd(zzaa zzaaVar, zzag zzagVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzagVar);
        transactAndReadExceptionReturnVoid(6, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzac
    public final void zzd(zzaa zzaaVar, zzai zzaiVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaiVar);
        transactAndReadExceptionReturnVoid(5, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzac
    public final void zzd(zzaa zzaaVar, zzak zzakVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzakVar);
        transactAndReadExceptionReturnVoid(8, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzac
    public final void zzd(zzaa zzaaVar, zzy zzyVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzaaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzyVar);
        transactAndReadExceptionReturnVoid(7, parcelObtainAndWriteInterfaceToken);
    }
}