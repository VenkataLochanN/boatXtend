package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzcr extends zzb implements zzcq {
    public zzcr() {
        super("com.google.android.gms.fitness.internal.IStatusCallback");
    }

    public static zzcq zzj(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IStatusCallback");
        return iInterfaceQueryLocalInterface instanceof zzcq ? (zzcq) iInterfaceQueryLocalInterface : new zzcs(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        onResult((Status) zzc.zza(parcel, Status.CREATOR));
        return true;
    }
}