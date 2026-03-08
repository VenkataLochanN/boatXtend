package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.BleDevice;

/* JADX INFO: loaded from: classes.dex */
final class zzb implements ListenerHolder.Notifier<BleScanCallback> {
    private final /* synthetic */ BleDevice zzgf;

    zzb(zza zzaVar, BleDevice bleDevice) {
        this.zzgf = bleDevice;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(BleScanCallback bleScanCallback) {
        bleScanCallback.onDeviceFound(this.zzgf);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}