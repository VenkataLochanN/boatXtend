package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzge<K, V> {
    static <K, V> void zza(zzel zzelVar, zzgh<K, V> zzghVar, K k, V v) throws IOException {
        zzeu.zza(zzelVar, zzghVar.zza, 1, k);
        zzeu.zza(zzelVar, zzghVar.zzc, 2, v);
    }

    static <K, V> int zza(zzgh<K, V> zzghVar, K k, V v) {
        return zzeu.zza(zzghVar.zza, 1, k) + zzeu.zza(zzghVar.zzc, 2, v);
    }
}