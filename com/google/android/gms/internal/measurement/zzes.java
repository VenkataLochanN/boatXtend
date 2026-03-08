package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzes extends zzet<zzfe.zze> {
    zzes() {
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final boolean zza(zzgm zzgmVar) {
        return zzgmVar instanceof zzfe.zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final zzeu<zzfe.zze> zza(Object obj) {
        return ((zzfe.zzb) obj).zzc;
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final zzeu<zzfe.zze> zzb(Object obj) {
        return ((zzfe.zzb) obj).zza();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final void zzc(Object obj) {
        zza(obj).zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final <UT, UB> UB zza(zzhc zzhcVar, Object obj, zzer zzerVar, zzeu<zzfe.zze> zzeuVar, UB ub, zzhx<UT, UB> zzhxVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final int zza(Map.Entry<?, ?> entry) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final void zza(zziq zziqVar, Map.Entry<?, ?> entry) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final Object zza(zzer zzerVar, zzgm zzgmVar, int i) {
        return zzerVar.zza(zzgmVar, i);
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final void zza(zzhc zzhcVar, Object obj, zzer zzerVar, zzeu<zzfe.zze> zzeuVar) throws IOException {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.measurement.zzet
    final void zza(zzdw zzdwVar, Object obj, zzer zzerVar, zzeu<zzfe.zze> zzeuVar) throws IOException {
        throw new NoSuchMethodError();
    }
}