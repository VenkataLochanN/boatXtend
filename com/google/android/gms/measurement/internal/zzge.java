package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.lang.Thread;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzge implements Thread.UncaughtExceptionHandler {
    private final String zza;
    private final /* synthetic */ zzgc zzb;

    public zzge(zzgc zzgcVar, String str) {
        this.zzb = zzgcVar;
        Preconditions.checkNotNull(str);
        this.zza = str;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        this.zzb.zzr().zzf().zza(this.zza, th);
    }
}