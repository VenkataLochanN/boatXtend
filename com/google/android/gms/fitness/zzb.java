package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.request.zzae;
import com.google.android.gms.internal.fitness.zzbt;
import com.google.android.gms.internal.fitness.zzcq;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
final class zzb extends RegisterListenerMethod<com.google.android.gms.internal.fitness.zzp, BleScanCallback> {
    private final /* synthetic */ ListenerHolder zzg;
    private final /* synthetic */ List zzh;
    private final /* synthetic */ int zzi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzb(BleClient bleClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, List list, int i) {
        super(listenerHolder);
        this.zzg = listenerHolder2;
        this.zzh = list;
        this.zzi = i;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final /* synthetic */ void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzbt) ((com.google.android.gms.internal.fitness.zzp) anyClient).getService()).zza(new StartBleScanRequest((List<DataType>) this.zzh, (zzae) com.google.android.gms.fitness.request.zzd.zzt().zza(this.zzg), this.zzi, (zzcq) zzen.zza(taskCompletionSource)));
    }
}