package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.fitness.request.zzbh;
import com.google.android.gms.internal.fitness.zzbt;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
final class zzc extends UnregisterListenerMethod<com.google.android.gms.internal.fitness.zzp, BleScanCallback> {
    private final /* synthetic */ ListenerHolder zzg;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzc(BleClient bleClient, ListenerHolder.ListenerKey listenerKey, ListenerHolder listenerHolder) {
        super(listenerKey);
        this.zzg = listenerHolder;
    }

    @Override // com.google.android.gms.common.api.internal.UnregisterListenerMethod
    protected final /* synthetic */ void unregisterListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        com.google.android.gms.internal.fitness.zzp zzpVar = (com.google.android.gms.internal.fitness.zzp) anyClient;
        com.google.android.gms.fitness.request.zza zzaVarZzb = com.google.android.gms.fitness.request.zzd.zzt().zzb(this.zzg);
        if (zzaVarZzb == null) {
            taskCompletionSource.setResult(false);
        } else {
            ((zzbt) zzpVar.getService()).zza(new zzbh((zzae) zzaVarZzb, (zzcq) zzen.zzb(taskCompletionSource)));
        }
    }
}