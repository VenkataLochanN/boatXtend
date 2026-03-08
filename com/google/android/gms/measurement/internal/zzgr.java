package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgr implements Runnable {
    private final /* synthetic */ zzan zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ zzgk zzc;

    zzgr(zzgk zzgkVar, zzan zzanVar, String str) {
        this.zzc = zzgkVar;
        this.zza = zzanVar;
        this.zzb = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzc.zza.zzo();
        this.zzc.zza.zza(this.zza, this.zzb);
    }
}