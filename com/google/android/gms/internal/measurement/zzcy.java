package com.google.android.gms.internal.measurement;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzcy<T> extends zzcw<T> {
    private final T zza;

    zzcy(T t) {
        this.zza = t;
    }

    @Override // com.google.android.gms.internal.measurement.zzcw
    public final boolean zza() {
        return true;
    }

    @Override // com.google.android.gms.internal.measurement.zzcw
    public final T zzb() {
        return this.zza;
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (obj instanceof zzcy) {
            return this.zza.equals(((zzcy) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode() + 1502476572;
    }

    public final String toString() {
        String strValueOf = String.valueOf(this.zza);
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 13);
        sb.append("Optional.of(");
        sb.append(strValueOf);
        sb.append(")");
        return sb.toString();
    }
}