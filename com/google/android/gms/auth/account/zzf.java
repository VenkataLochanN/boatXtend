package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface zzf extends IInterface {
    void zzd(zzd zzdVar, Account account) throws RemoteException;

    void zzd(zzd zzdVar, String str) throws RemoteException;

    void zze(boolean z) throws RemoteException;
}