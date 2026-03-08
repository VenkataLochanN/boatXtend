package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfe implements Runnable {
    private final /* synthetic */ int zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ Object zzc;
    private final /* synthetic */ Object zzd;
    private final /* synthetic */ Object zze;
    private final /* synthetic */ zzfb zzf;

    zzfe(zzfb zzfbVar, int i, String str, Object obj, Object obj2, Object obj3) {
        this.zzf = zzfbVar;
        this.zza = i;
        this.zzb = str;
        this.zzc = obj;
        this.zzd = obj2;
        this.zze = obj3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzfo zzfoVarZzc = this.zzf.zzx.zzc();
        if (!zzfoVarZzc.zzz()) {
            this.zzf.zza(6, "Persisted config not initialized. Not logging error/warn");
            return;
        }
        if (this.zzf.zza == 0) {
            if (this.zzf.zzt().zzf()) {
                zzfb zzfbVar = this.zzf;
                zzfbVar.zzu();
                zzfbVar.zza = 'C';
            } else {
                zzfb zzfbVar2 = this.zzf;
                zzfbVar2.zzu();
                zzfbVar2.zza = 'c';
            }
        }
        if (this.zzf.zzb < 0) {
            zzfb zzfbVar3 = this.zzf;
            zzfbVar3.zzb = zzfbVar3.zzt().zze();
        }
        char cCharAt = "01VDIWEA?".charAt(this.zza);
        char c2 = this.zzf.zza;
        long j = this.zzf.zzb;
        String strZza = zzfb.zza(true, this.zzb, this.zzc, this.zzd, this.zze);
        StringBuilder sb = new StringBuilder(String.valueOf(strZza).length() + 24);
        sb.append("2");
        sb.append(cCharAt);
        sb.append(c2);
        sb.append(j);
        sb.append(":");
        sb.append(strZza);
        String string = sb.toString();
        if (string.length() > 1024) {
            string = this.zzb.substring(0, 1024);
        }
        zzfoVarZzc.zzb.zza(string, 1L);
    }
}