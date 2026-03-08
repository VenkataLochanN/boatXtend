package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgi implements zzgj {
    zzgi() {
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final Map<?, ?> zza(Object obj) {
        return (zzgg) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final zzgh<?, ?> zzb(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final Map<?, ?> zzc(Object obj) {
        return (zzgg) obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final boolean zzd(Object obj) {
        return !((zzgg) obj).zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final Object zze(Object obj) {
        ((zzgg) obj).zzc();
        return obj;
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final Object zzf(Object obj) {
        return zzgg.zza().zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final Object zza(Object obj, Object obj2) {
        zzgg zzggVarZzb = (zzgg) obj;
        zzgg zzggVar = (zzgg) obj2;
        if (!zzggVar.isEmpty()) {
            if (!zzggVarZzb.zzd()) {
                zzggVarZzb = zzggVarZzb.zzb();
            }
            zzggVarZzb.zza(zzggVar);
        }
        return zzggVarZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzgj
    public final int zza(int i, Object obj, Object obj2) {
        zzgg zzggVar = (zzgg) obj;
        if (zzggVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzggVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}