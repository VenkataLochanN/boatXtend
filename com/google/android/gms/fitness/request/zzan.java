package com.google.android.gms.fitness.request;

import android.os.Looper;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class zzan {
    private static final zzan zzhn = new zzan();
    private final Map<ListenerHolder.ListenerKey<OnDataPointListener>, zzal> zzho = new HashMap();

    private zzan() {
    }

    private static ListenerHolder<OnDataPointListener> zzc(OnDataPointListener onDataPointListener) {
        return ListenerHolders.createListenerHolder(onDataPointListener, Looper.getMainLooper(), OnDataPointListener.class.getSimpleName());
    }

    public static zzan zzw() {
        return zzhn;
    }

    public final zzal zza(OnDataPointListener onDataPointListener) {
        return zzc(zzc(onDataPointListener));
    }

    public final zzal zzb(OnDataPointListener onDataPointListener) {
        return zzd(zzc(onDataPointListener));
    }

    public final zzal zzc(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzal zzalVar;
        synchronized (this.zzho) {
            zzalVar = this.zzho.get(listenerHolder.getListenerKey());
            if (zzalVar == null) {
                zzalVar = new zzal(listenerHolder, null);
                this.zzho.put(listenerHolder.getListenerKey(), zzalVar);
            }
        }
        return zzalVar;
    }

    public final zzal zzd(ListenerHolder<OnDataPointListener> listenerHolder) {
        zzal zzalVarRemove;
        synchronized (this.zzho) {
            zzalVarRemove = this.zzho.remove(listenerHolder.getListenerKey());
            if (zzalVarRemove != null) {
                zzalVarRemove.release();
            }
        }
        return zzalVarRemove;
    }
}