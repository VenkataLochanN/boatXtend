package com.google.android.gms.internal.fitness;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes.dex */
public interface zzcq extends IInterface {
    void onResult(Status status) throws RemoteException;
}