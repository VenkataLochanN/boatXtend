package com.google.android.gms.internal.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.data.BleDevice;

/* JADX INFO: loaded from: classes.dex */
final class zzcx extends zzu {
    private final /* synthetic */ BleDevice zzfa;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcx(zzct zzctVar, GoogleApiClient googleApiClient, BleDevice bleDevice) {
        super(googleApiClient);
        this.zzfa = bleDevice;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzbt) ((zzp) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zze(this.zzfa.getAddress(), this.zzfa, (zzcq) new zzen(this)));
    }
}