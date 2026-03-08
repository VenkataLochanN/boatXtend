package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzdc<T> implements zzdb<T> {
    private volatile zzdb<T> zza;
    private volatile boolean zzb;

    @NullableDecl
    private T zzc;

    zzdc(zzdb<T> zzdbVar) {
        this.zza = (zzdb) zzcz.zza(zzdbVar);
    }

    @Override // com.google.android.gms.internal.measurement.zzdb
    public final T zza() {
        if (!this.zzb) {
            synchronized (this) {
                if (!this.zzb) {
                    T tZza = this.zza.zza();
                    this.zzc = tZza;
                    this.zzb = true;
                    this.zza = null;
                    return tZza;
                }
            }
        }
        return this.zzc;
    }

    public final String toString() {
        Object string = this.zza;
        if (string == null) {
            String strValueOf = String.valueOf(this.zzc);
            StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 25);
            sb.append("<supplier that returned ");
            sb.append(strValueOf);
            sb.append(">");
            string = sb.toString();
        }
        String strValueOf2 = String.valueOf(string);
        StringBuilder sb2 = new StringBuilder(String.valueOf(strValueOf2).length() + 19);
        sb2.append("Suppliers.memoize(");
        sb2.append(strValueOf2);
        sb2.append(")");
        return sb2.toString();
    }
}