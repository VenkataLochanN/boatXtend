package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.ListSubscriptionsResult;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzci extends zzb implements zzch {
    public zzci() {
        super("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
    }

    public static zzch zzg(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IListSubscriptionsCallback");
        return iInterfaceQueryLocalInterface instanceof zzch ? (zzch) iInterfaceQueryLocalInterface : new zzcj(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((ListSubscriptionsResult) zzc.zza(parcel, ListSubscriptionsResult.CREATOR));
        return true;
    }
}