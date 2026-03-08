package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzgw {
    private static final zzgu zza = zzc();
    private static final zzgu zzb = new zzgx();

    static zzgu zza() {
        return zza;
    }

    static zzgu zzb() {
        return zzb;
    }

    private static zzgu zzc() {
        try {
            return (zzgu) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}