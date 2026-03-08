package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
abstract class zzhx<T, B> {
    zzhx() {
    }

    abstract B zza();

    abstract T zza(B b2);

    abstract void zza(B b2, int i, int i2);

    abstract void zza(B b2, int i, long j);

    abstract void zza(B b2, int i, zzdw zzdwVar);

    abstract void zza(B b2, int i, T t);

    abstract void zza(T t, zziq zziqVar) throws IOException;

    abstract void zza(Object obj, T t);

    abstract boolean zza(zzhc zzhcVar);

    abstract T zzb(Object obj);

    abstract void zzb(B b2, int i, long j);

    abstract void zzb(T t, zziq zziqVar) throws IOException;

    abstract void zzb(Object obj, B b2);

    abstract B zzc(Object obj);

    abstract T zzc(T t, T t2);

    abstract void zzd(Object obj);

    abstract int zze(T t);

    abstract int zzf(T t);

    final boolean zza(B b2, zzhc zzhcVar) throws IOException {
        int iZzb = zzhcVar.zzb();
        int i = iZzb >>> 3;
        int i2 = iZzb & 7;
        if (i2 == 0) {
            zza(b2, i, zzhcVar.zzg());
            return true;
        }
        if (i2 == 1) {
            zzb(b2, i, zzhcVar.zzi());
            return true;
        }
        if (i2 == 2) {
            zza((Object) b2, i, zzhcVar.zzn());
            return true;
        }
        if (i2 != 3) {
            if (i2 == 4) {
                return false;
            }
            if (i2 != 5) {
                throw zzfm.zzf();
            }
            zza((Object) b2, i, zzhcVar.zzj());
            return true;
        }
        B bZza = zza();
        int i3 = 4 | (i << 3);
        while (zzhcVar.zza() != Integer.MAX_VALUE && zza((Object) bZza, zzhcVar)) {
        }
        if (i3 != zzhcVar.zzb()) {
            throw zzfm.zze();
        }
        zza(b2, i, zza(bZza));
        return true;
    }
}