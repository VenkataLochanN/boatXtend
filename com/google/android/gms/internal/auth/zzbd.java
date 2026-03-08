package com.google.android.gms.internal.auth;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* JADX INFO: loaded from: classes.dex */
public final class zzbd extends zzd implements zzbc {
    zzbd(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.auth.api.credentials.internal.ICredentialsService");
    }

    @Override // com.google.android.gms.internal.auth.zzbc
    public final void zzd(zzba zzbaVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzbaVar);
        transactAndReadExceptionReturnVoid(4, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzbc
    public final void zzd(zzba zzbaVar, CredentialRequest credentialRequest) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzbaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, credentialRequest);
        transactAndReadExceptionReturnVoid(1, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzbc
    public final void zzd(zzba zzbaVar, zzay zzayVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzbaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzayVar);
        transactAndReadExceptionReturnVoid(3, parcelObtainAndWriteInterfaceToken);
    }

    @Override // com.google.android.gms.internal.auth.zzbc
    public final void zzd(zzba zzbaVar, zzbe zzbeVar) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzbaVar);
        zzf.zzd(parcelObtainAndWriteInterfaceToken, zzbeVar);
        transactAndReadExceptionReturnVoid(2, parcelObtainAndWriteInterfaceToken);
    }
}