package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public final class zzda {
    public static <T> zzdb<T> zza(zzdb<T> zzdbVar) {
        return ((zzdbVar instanceof zzdc) || (zzdbVar instanceof zzdd)) ? zzdbVar : zzdbVar instanceof Serializable ? new zzdd(zzdbVar) : new zzdc(zzdbVar);
    }

    public static <T> zzdb<T> zza(@NullableDecl T t) {
        return new zzdf(t);
    }
}