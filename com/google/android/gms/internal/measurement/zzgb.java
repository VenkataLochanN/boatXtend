package com.google.android.gms.internal.measurement;

import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgb extends zzfw {
    private zzgb() {
        super();
    }

    @Override // com.google.android.gms.internal.measurement.zzfw
    final <L> List<L> zza(Object obj, long j) {
        zzfn zzfnVarZzc = zzc(obj, j);
        if (zzfnVarZzc.zza()) {
            return zzfnVarZzc;
        }
        int size = zzfnVarZzc.size();
        zzfn zzfnVarZza = zzfnVarZzc.zza(size == 0 ? 10 : size << 1);
        zzid.zza(obj, j, zzfnVarZza);
        return zzfnVarZza;
    }

    @Override // com.google.android.gms.internal.measurement.zzfw
    final void zzb(Object obj, long j) {
        zzc(obj, j).zzb();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.google.android.gms.internal.measurement.zzfn] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5 */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.android.gms.internal.measurement.zzfn, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r6v2, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v3 */
    @Override // com.google.android.gms.internal.measurement.zzfw
    final <E> void zza(Object obj, Object obj2, long j) {
        zzfn zzfnVarZzc = zzc(obj, j);
        ?? Zzc = zzc(obj2, j);
        int size = zzfnVarZzc.size();
        int size2 = Zzc.size();
        ?? r0 = zzfnVarZzc;
        r0 = zzfnVarZzc;
        if (size > 0 && size2 > 0) {
            boolean zZza = zzfnVarZzc.zza();
            ?? Zza = zzfnVarZzc;
            if (!zZza) {
                Zza = zzfnVarZzc.zza(size2 + size);
            }
            Zza.addAll(Zzc);
            r0 = Zza;
        }
        if (size > 0) {
            Zzc = r0;
        }
        zzid.zza(obj, j, (Object) Zzc);
    }

    private static <E> zzfn<E> zzc(Object obj, long j) {
        return (zzfn) zzid.zzf(obj, j);
    }
}