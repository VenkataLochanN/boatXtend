package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzhz extends zzhx<zzhw, zzhw> {
    zzhz() {
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final boolean zza(zzhc zzhcVar) {
        return false;
    }

    /* JADX INFO: renamed from: zza, reason: avoid collision after fix types in other method */
    private static void zza2(Object obj, zzhw zzhwVar) {
        ((zzfe) obj).zzb = zzhwVar;
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final void zzd(Object obj) {
        ((zzfe) obj).zzb.zzc();
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ int zzf(zzhw zzhwVar) {
        return zzhwVar.zze();
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ int zze(zzhw zzhwVar) {
        return zzhwVar.zzd();
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ zzhw zzc(zzhw zzhwVar, zzhw zzhwVar2) {
        zzhw zzhwVar3 = zzhwVar;
        zzhw zzhwVar4 = zzhwVar2;
        return zzhwVar4.equals(zzhw.zza()) ? zzhwVar3 : zzhw.zza(zzhwVar3, zzhwVar4);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zzb(zzhw zzhwVar, zziq zziqVar) throws IOException {
        zzhwVar.zza(zziqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zza(zzhw zzhwVar, zziq zziqVar) throws IOException {
        zzhwVar.zzb(zziqVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zzb(Object obj, zzhw zzhwVar) {
        zza2(obj, zzhwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ zzhw zzc(Object obj) {
        zzhw zzhwVar = ((zzfe) obj).zzb;
        if (zzhwVar != zzhw.zza()) {
            return zzhwVar;
        }
        zzhw zzhwVarZzb = zzhw.zzb();
        zza2(obj, zzhwVarZzb);
        return zzhwVarZzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ zzhw zzb(Object obj) {
        return ((zzfe) obj).zzb;
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* bridge */ /* synthetic */ void zza(Object obj, zzhw zzhwVar) {
        zza2(obj, zzhwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ zzhw zza(zzhw zzhwVar) {
        zzhw zzhwVar2 = zzhwVar;
        zzhwVar2.zzc();
        return zzhwVar2;
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ zzhw zza() {
        return zzhw.zzb();
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zza(zzhw zzhwVar, int i, zzhw zzhwVar2) {
        zzhwVar.zza((i << 3) | 3, zzhwVar2);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zza(zzhw zzhwVar, int i, zzdw zzdwVar) {
        zzhwVar.zza((i << 3) | 2, zzdwVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zzb(zzhw zzhwVar, int i, long j) {
        zzhwVar.zza((i << 3) | 1, Long.valueOf(j));
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zza(zzhw zzhwVar, int i, int i2) {
        zzhwVar.zza((i << 3) | 5, Integer.valueOf(i2));
    }

    @Override // com.google.android.gms.internal.measurement.zzhx
    final /* synthetic */ void zza(zzhw zzhwVar, int i, long j) {
        zzhwVar.zza(i << 3, Long.valueOf(j));
    }
}