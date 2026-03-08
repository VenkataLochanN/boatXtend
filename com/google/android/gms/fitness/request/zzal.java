package com.google.android.gms.fitness.request;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.fitness.data.DataPoint;

/* JADX INFO: loaded from: classes.dex */
public final class zzal extends com.google.android.gms.fitness.data.zzu {
    private final ListenerHolder<OnDataPointListener> zzhl;

    private zzal(ListenerHolder<OnDataPointListener> listenerHolder) {
        this.zzhl = (ListenerHolder) Preconditions.checkNotNull(listenerHolder);
    }

    /* synthetic */ zzal(ListenerHolder listenerHolder, zzam zzamVar) {
        this(listenerHolder);
    }

    public final void release() {
        this.zzhl.clear();
    }

    @Override // com.google.android.gms.fitness.data.zzt
    public final void zzc(DataPoint dataPoint) throws RemoteException {
        this.zzhl.notifyListener(new zzam(this, dataPoint));
    }
}