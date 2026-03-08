package com.google.android.gms.auth.api.signin.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/* JADX INFO: loaded from: classes.dex */
public interface zzv extends IInterface {
    void zzd(zzt zztVar, GoogleSignInOptions googleSignInOptions) throws RemoteException;

    void zze(zzt zztVar, GoogleSignInOptions googleSignInOptions) throws RemoteException;

    void zzf(zzt zztVar, GoogleSignInOptions googleSignInOptions) throws RemoteException;
}