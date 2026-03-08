package com.google.android.gms.internal.auth;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public interface zzaa extends IInterface {
    void onFailure(Status status) throws RemoteException;

    void zzd(DeviceMetaData deviceMetaData) throws RemoteException;

    void zzd(Status status, com.google.android.gms.auth.api.accounttransfer.zzo zzoVar) throws RemoteException;

    void zzd(Status status, com.google.android.gms.auth.api.accounttransfer.zzw zzwVar) throws RemoteException;

    void zzd(byte[] bArr) throws RemoteException;

    void zze(Status status) throws RemoteException;

    void zzi() throws RemoteException;
}