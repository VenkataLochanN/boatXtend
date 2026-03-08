package com.google.android.gms.internal.fitness;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzet extends zza implements zzer {
    zzet(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.fitness.internal.ble.IBleDevicesCallback");
    }

    @Override // com.google.android.gms.internal.fitness.zzer
    public final void zza(BleDevicesResult bleDevicesResult) throws RemoteException {
        Parcel parcelObtainAndWriteInterfaceToken = obtainAndWriteInterfaceToken();
        zzc.zza(parcelObtainAndWriteInterfaceToken, bleDevicesResult);
        transactOneway(1, parcelObtainAndWriteInterfaceToken);
    }
}