package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.SessionReadResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzcm extends zza implements zzck {
    zzcm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzck
    public final void zza(SessionReadResult sessionReadResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, sessionReadResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}