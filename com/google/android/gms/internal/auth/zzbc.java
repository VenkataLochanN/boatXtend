package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.CredentialRequest;

/* JADX INFO: loaded from: classes.dex */
public interface zzbc extends IInterface {
    void zzd(zzba zzbaVar) throws RemoteException;

    void zzd(zzba zzbaVar, CredentialRequest credentialRequest) throws RemoteException;

    void zzd(zzba zzbaVar, zzay zzayVar) throws RemoteException;

    void zzd(zzba zzbaVar, zzbe zzbeVar) throws RemoteException;
}