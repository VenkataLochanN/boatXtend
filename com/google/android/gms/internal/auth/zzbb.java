package com.google.android.gms.internal.auth;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.credentials.Credential;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzbb extends zze implements zzba {
    public zzbb() {
        super("com.google.android.gms.auth.api.credentials.internal.ICredentialsCallbacks");
    }

    @Override // com.google.android.gms.internal.auth.zze
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzd((Status) zzf.zzd(parcel, Status.CREATOR), (Credential) zzf.zzd(parcel, Credential.CREATOR));
        } else if (i == 2) {
            zze((Status) zzf.zzd(parcel, Status.CREATOR));
        } else {
            if (i != 3) {
                return false;
            }
            zzd((Status) zzf.zzd(parcel, Status.CREATOR), parcel.readString());
        }
        parcel2.writeNoException();
        return true;
    }
}