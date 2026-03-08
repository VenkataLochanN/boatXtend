package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzip implements Runnable {
    private final /* synthetic */ zzio zza;
    private final /* synthetic */ long zzb;
    private final /* synthetic */ zzin zzc;

    zzip(zzin zzinVar, zzio zzioVar, long j) {
        this.zzc = zzinVar;
        this.zza = zzioVar;
        this.zzb = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza(this.zza, false, this.zzb);
        zzin zzinVar = this.zzc;
        zzinVar.zza = null;
        zzinVar.zzh().zza((zzio) null);
    }
}