package com.google.android.gms.auth.account;

import android.accounts.Account;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public abstract class zze extends com.google.android.gms.internal.auth.zze implements zzd {
    public zze() {
        super("com.google.android.gms.auth.account.IWorkAccountCallback");
    }

    @Override // com.google.android.gms.internal.auth.zze
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzf((Account) com.google.android.gms.internal.auth.zzf.zzd(parcel, Account.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zzd(com.google.android.gms.internal.auth.zzf.zzd(parcel));
        }
        return true;
    }
}