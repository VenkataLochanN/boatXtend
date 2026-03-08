package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzgy implements Runnable {
    private final /* synthetic */ String zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ long zzd;
    private final /* synthetic */ zzgk zze;

    zzgy(zzgk zzgkVar, String str, String str2, String str3, long j) {
        this.zze = zzgkVar;
        this.zza = str;
        this.zzb = str2;
        this.zzc = str3;
        this.zzd = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = this.zza;
        if (str == null) {
            this.zze.zza.zzs().zzv().zza(this.zzb, (zzio) null);
        } else {
            this.zze.zza.zzs().zzv().zza(this.zzb, new zzio(this.zzc, str, this.zzd));
        }
    }
}