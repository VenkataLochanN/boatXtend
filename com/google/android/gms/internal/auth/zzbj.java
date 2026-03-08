package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyResponse;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzbj extends zze implements zzbi {
    public zzbj() {
        super("com.google.android.gms.auth.api.internal.IAuthCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zze
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzd((ProxyResponse) zzf.zzd(parcel, ProxyResponse.CREATOR));
        } else {
            if (i != 2) {
                return false;
            }
            zzf(parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}