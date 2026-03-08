package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import kotlin.jvm.internal.LongCompanionObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgd<V> extends FutureTask<V> implements Comparable<zzgd<V>> {
    final boolean zza;
    private final long zzb;
    private final String zzc;
    private final /* synthetic */ zzgc zzd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzgd(zzgc zzgcVar, Callable<V> callable, boolean z, String str) {
        super(callable);
        this.zzd = zzgcVar;
        Preconditions.checkNotNull(str);
        this.zzb = zzgc.zzj.getAndIncrement();
        this.zzc = str;
        this.zza = z;
        if (this.zzb == LongCompanionObject.MAX_VALUE) {
            zzgcVar.zzr().zzf().zza("Tasks index overflow");
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzgd(zzgc zzgcVar, Runnable runnable, boolean z, String str) {
        super(runnable, null);
        this.zzd = zzgcVar;
        Preconditions.checkNotNull(str);
        this.zzb = zzgc.zzj.getAndIncrement();
        this.zzc = str;
        this.zza = false;
        if (this.zzb == LongCompanionObject.MAX_VALUE) {
            zzgcVar.zzr().zzf().zza("Tasks index overflow");
        }
    }

    @Override // java.util.concurrent.FutureTask
    protected final void setException(Throwable th) {
        this.zzd.zzr().zzf().zza(this.zzc, th);
        if (th instanceof zzgb) {
            Thread.getDefaultUncaughtExceptionHandler().uncaughtException(Thread.currentThread(), th);
        }
        super.setException(th);
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(Object obj) {
        zzgd zzgdVar = (zzgd) obj;
        boolean z = this.zza;
        if (z != zzgdVar.zza) {
            return z ? -1 : 1;
        }
        long j = this.zzb;
        long j2 = zzgdVar.zzb;
        if (j < j2) {
            return -1;
        }
        if (j > j2) {
            return 1;
        }
        this.zzd.zzr().zzg().zza("Two tasks share the same index. index", Long.valueOf(this.zzb));
        return 0;
    }
}