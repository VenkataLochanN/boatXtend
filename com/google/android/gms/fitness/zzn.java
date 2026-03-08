package com.google.android.gms.fitness;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.fitness.request.OnDataPointListener;
import com.google.android.gms.fitness.request.SensorRequest;
import com.google.android.gms.fitness.request.zzan;
import com.google.android.gms.fitness.request.zzao;
import com.google.android.gms.internal.fitness.zzas;
import com.google.android.gms.internal.fitness.zzcd;
import com.google.android.gms.internal.fitness.zzen;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes.dex */
final class zzn extends RegisterListenerMethod<zzas, OnDataPointListener> {
    private final /* synthetic */ ListenerHolder zzg;
    private final /* synthetic */ SensorRequest zzy;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzn(SensorsClient sensorsClient, ListenerHolder listenerHolder, ListenerHolder listenerHolder2, SensorRequest sensorRequest) {
        super(listenerHolder);
        this.zzg = listenerHolder2;
        this.zzy = sensorRequest;
    }

    @Override // com.google.android.gms.common.api.internal.RegisterListenerMethod
    protected final /* synthetic */ void registerListener(Api.AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        ((zzcd) ((zzas) anyClient).getService()).zza(new zzao(this.zzy, zzan.zzw().zzc(this.zzg), null, zzen.zza(taskCompletionSource)));
    }
}