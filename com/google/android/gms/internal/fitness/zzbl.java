package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DataSourcesResult;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzbl extends zzb implements zzbk {
    public zzbl() {
        super("com.google.android.gms.fitness.internal.IDataSourcesCallback");
    }

    public static zzbk zzd(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDataSourcesCallback");
        return iInterfaceQueryLocalInterface instanceof zzbk ? (zzbk) iInterfaceQueryLocalInterface : new zzbm(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((DataSourcesResult) zzc.zza(parcel, DataSourcesResult.CREATOR));
        return true;
    }
}