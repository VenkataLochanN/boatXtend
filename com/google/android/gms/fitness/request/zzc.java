package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;

/* JADX INFO: loaded from: classes.dex */
final class zzc implements ListenerHolder.Notifier<BleScanCallback> {
    zzc(zza zzaVar) {
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(BleScanCallback bleScanCallback) {
        bleScanCallback.onScanStopped();
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}