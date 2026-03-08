package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.GoalsResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzbs extends zza implements zzbq {
    zzbs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.IGoalsReadCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzbq
    public final void zza(GoalsResult goalsResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, goalsResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}