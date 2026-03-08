package com.google.android.gms.internal.fitness;

import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes.dex */
final class zzek extends zzbd {
    private final /* synthetic */ PendingIntent zzfv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzek(zzee zzeeVar, GoogleApiClient googleApiClient, PendingIntent pendingIntent) {
        super(googleApiClient);
        this.zzfv = pendingIntent;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        ((zzcf) ((zzay) anyClient).getService()).zza(new com.google.android.gms.fitness.request.zzbd(this.zzfv, (zzcq) new zzen(this)));
    }
}