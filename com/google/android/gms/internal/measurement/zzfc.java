package com.google.android.gms.internal.measurement;

import com.google.android.gms.internal.measurement.zzfe;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzfc implements zzgn {
    private static final zzfc zza = new zzfc();

    private zzfc() {
    }

    public static zzfc zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.measurement.zzgn
    public final boolean zza(Class<?> cls) {
        return zzfe.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.measurement.zzgn
    public final zzgk zzb(Class<?> cls) {
        if (!zzfe.class.isAssignableFrom(cls)) {
            String strValueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(strValueOf.length() != 0 ? "Unsupported message type: ".concat(strValueOf) : new String("Unsupported message type: "));
        }
        try {
            return (zzgk) zzfe.zza(cls.asSubclass(zzfe.class)).zza(zzfe.zzf.zzc, (Object) null, (Object) null);
        } catch (Exception e2) {
            String strValueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(strValueOf2.length() != 0 ? "Unable to get message info for ".concat(strValueOf2) : new String("Unable to get message info for "), e2);
        }
    }
}