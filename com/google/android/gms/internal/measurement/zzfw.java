package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzfw {
    private static final zzfw zza;
    private static final zzfw zzb;

    private zzfw() {
    }

    abstract <L> List<L> zza(Object obj, long j);

    abstract <L> void zza(Object obj, Object obj2, long j);

    abstract void zzb(Object obj, long j);

    static zzfw zza() {
        return zza;
    }

    static zzfw zzb() {
        return zzb;
    }

    static {
        zzfz zzfzVar = null;
        zza = new zzfy();
        zzb = new zzgb();
    }
}