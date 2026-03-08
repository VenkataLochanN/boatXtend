package com.google.android.gms.internal.measurement;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzhb {
    private static final zzhb zza = new zzhb();
    private final ConcurrentMap<Class<?>, zzhf<?>> zzc = new ConcurrentHashMap();
    private final zzhe zzb = new zzgd();

    public static zzhb zza() {
        return zza;
    }

    public final <T> zzhf<T> zza(Class<T> cls) {
        zzfh.zza(cls, "messageType");
        zzhf<T> zzhfVar = (zzhf) this.zzc.get(cls);
        if (zzhfVar != null) {
            return zzhfVar;
        }
        zzhf<T> zzhfVarZza = this.zzb.zza(cls);
        zzfh.zza(cls, "messageType");
        zzfh.zza(zzhfVarZza, "schema");
        zzhf<T> zzhfVar2 = (zzhf) this.zzc.putIfAbsent(cls, zzhfVarZza);
        return zzhfVar2 != null ? zzhfVar2 : zzhfVarZza;
    }

    public final <T> zzhf<T> zza(T t) {
        return zza((Class) t.getClass());
    }

    private zzhb() {
    }
}