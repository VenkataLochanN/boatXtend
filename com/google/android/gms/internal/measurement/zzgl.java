package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgl {
    private static final zzgj zza = zzc();
    private static final zzgj zzb = new zzgi();

    static zzgj zza() {
        return zza;
    }

    static zzgj zzb() {
        return zzb;
    }

    private static zzgj zzc() {
        try {
            return (zzgj) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}