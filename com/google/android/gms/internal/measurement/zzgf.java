package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgf implements zzgn {
    private zzgn[] zza;

    zzgf(zzgn... zzgnVarArr) {
        this.zza = zzgnVarArr;
    }

    @Override // com.google.android.gms.internal.measurement.zzgn
    public final boolean zza(Class<?> cls) {
        for (zzgn zzgnVar : this.zza) {
            if (zzgnVar.zza(cls)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.gms.internal.measurement.zzgn
    public final zzgk zzb(Class<?> cls) {
        for (zzgn zzgnVar : this.zza) {
            if (zzgnVar.zza(cls)) {
                return zzgnVar.zzb(cls);
            }
        }
        String strValueOf = String.valueOf(cls.getName());
        throw new UnsupportedOperationException(strValueOf.length() != 0 ? "No factory is available for message type: ".concat(strValueOf) : new String("No factory is available for message type: "));
    }
}