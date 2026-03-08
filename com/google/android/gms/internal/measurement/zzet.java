package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzew;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzet<T extends zzew<T>> {
    zzet() {
    }

    abstract int zza(Map.Entry<?, ?> entry);

    abstract zzeu<T> zza(Object obj);

    abstract Object zza(zzer zzerVar, zzgm zzgmVar, int i);

    abstract <UT, UB> UB zza(zzhc zzhcVar, Object obj, zzer zzerVar, zzeu<T> zzeuVar, UB ub, zzhx<UT, UB> zzhxVar) throws IOException;

    abstract void zza(zzdw zzdwVar, Object obj, zzer zzerVar, zzeu<T> zzeuVar) throws IOException;

    abstract void zza(zzhc zzhcVar, Object obj, zzer zzerVar, zzeu<T> zzeuVar) throws IOException;

    abstract void zza(zziq zziqVar, Map.Entry<?, ?> entry) throws IOException;

    abstract boolean zza(zzgm zzgmVar);

    abstract zzeu<T> zzb(Object obj);

    abstract void zzc(Object obj);
}