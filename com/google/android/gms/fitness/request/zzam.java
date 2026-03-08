package com.google.android.gms.fitness.request;

import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.fitness.data.DataPoint;

/* JADX INFO: loaded from: classes.dex */
final class zzam implements ListenerHolder.Notifier<OnDataPointListener> {
    private final /* synthetic */ DataPoint zzhm;

    zzam(zzal zzalVar, DataPoint dataPoint) {
        this.zzhm = dataPoint;
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final /* synthetic */ void notifyListener(OnDataPointListener onDataPointListener) {
        onDataPointListener.onDataPoint(this.zzhm);
    }

    @Override // com.google.android.gms.common.api.internal.ListenerHolder.Notifier
    public final void onNotifyListenerFailed() {
    }
}