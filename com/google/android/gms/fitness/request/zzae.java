package com.google.android.gms.fitness.request;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.fitness.data.BleDevice;

/* JADX INFO: loaded from: classes.dex */
public interface zzae extends IInterface {
    void onDeviceFound(BleDevice bleDevice) throws RemoteException;

    void onScanStopped() throws RemoteException;
}