package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public class zzfv {
    private static final zzer zza = zzer.zza();
    private zzdw zzb;
    private volatile zzgm zzc;
    private volatile zzdw zzd;

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfv)) {
            return false;
        }
        zzfv zzfvVar = (zzfv) obj;
        zzgm zzgmVar = this.zzc;
        zzgm zzgmVar2 = zzfvVar.zzc;
        if (zzgmVar == null && zzgmVar2 == null) {
            return zzc().equals(zzfvVar.zzc());
        }
        if (zzgmVar != null && zzgmVar2 != null) {
            return zzgmVar.equals(zzgmVar2);
        }
        if (zzgmVar != null) {
            return zzgmVar.equals(zzfvVar.zzb(zzgmVar.h_()));
        }
        return zzb(zzgmVar2.h_()).equals(zzgmVar2);
    }

    private final zzgm zzb(zzgm zzgmVar) {
        if (this.zzc == null) {
            synchronized (this) {
                if (this.zzc == null) {
                    try {
                        this.zzc = zzgmVar;
                        this.zzd = zzdw.zza;
                    } catch (zzfm unused) {
                        this.zzc = zzgmVar;
                        this.zzd = zzdw.zza;
                    }
                }
            }
        }
        return this.zzc;
    }

    public final zzgm zza(zzgm zzgmVar) {
        zzgm zzgmVar2 = this.zzc;
        this.zzb = null;
        this.zzd = null;
        this.zzc = zzgmVar;
        return zzgmVar2;
    }

    public final int zzb() {
        if (this.zzd != null) {
            return this.zzd.zza();
        }
        if (this.zzc != null) {
            return this.zzc.zzbm();
        }
        return 0;
    }

    public final zzdw zzc() {
        if (this.zzd != null) {
            return this.zzd;
        }
        synchronized (this) {
            if (this.zzd != null) {
                return this.zzd;
            }
            if (this.zzc == null) {
                this.zzd = zzdw.zza;
            } else {
                this.zzd = this.zzc.zzbh();
            }
            return this.zzd;
        }
    }
}