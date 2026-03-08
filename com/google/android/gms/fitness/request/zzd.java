package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzd {
    private static final zzd zzgg = new zzd();
    private final Map<ListenerHolder.ListenerKey<BleScanCallback>, zza> zzgh = new HashMap();

    private zzd() {
    }

    private static ListenerHolder<BleScanCallback> zzc(BleScanCallback bleScanCallback) {
        return ListenerHolders.createListenerHolder(bleScanCallback, Looper.getMainLooper(), BleScanCallback.class.getSimpleName());
    }

    public static zzd zzt() {
        return zzgg;
    }

    public final zza zza(ListenerHolder<BleScanCallback> listenerHolder) {
        zza zzaVar;
        synchronized (this.zzgh) {
            zzaVar = this.zzgh.get(listenerHolder.getListenerKey());
            if (zzaVar == null) {
                zzaVar = new zza(listenerHolder, null);
                this.zzgh.put(listenerHolder.getListenerKey(), zzaVar);
            }
        }
        return zzaVar;
    }

    public final zza zza(BleScanCallback bleScanCallback) {
        return zza(zzc(bleScanCallback));
    }

    public final zza zzb(ListenerHolder<BleScanCallback> listenerHolder) {
        zza zzaVar;
        synchronized (this.zzgh) {
            zzaVar = this.zzgh.get(listenerHolder.getListenerKey());
            if (zzaVar != null) {
                zzaVar.release();
            }
        }
        return zzaVar;
    }

    public final zza zzb(BleScanCallback bleScanCallback) {
        return zzb(zzc(bleScanCallback));
    }
}