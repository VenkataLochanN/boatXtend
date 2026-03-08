package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzev {
    private static final zzet<?> zza = new zzes();
    private static final zzet<?> zzb = zzc();

    private static zzet<?> zzc() {
        try {
            return (zzet) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzet<?> zza() {
        return zza;
    }

    static zzet<?> zzb() {
        zzet<?> zzetVar = zzb;
        if (zzetVar != null) {
            return zzetVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}