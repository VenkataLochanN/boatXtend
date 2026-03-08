package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.DailyTotalResult;

/* JADX INFO: loaded from: classes.dex */
public abstract class zzbf extends zzb implements zzbe {
    public zzbf() {
        super("com.google.android.gms.fitness.internal.IDailyTotalCallback");
    }

    public static zzbe zzb(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IDailyTotalCallback");
        return iInterfaceQueryLocalInterface instanceof zzbe ? (zzbe) iInterfaceQueryLocalInterface : new zzbg(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        zza((DailyTotalResult) zzc.zza(parcel, DailyTotalResult.CREATOR));
        return true;
    }
}