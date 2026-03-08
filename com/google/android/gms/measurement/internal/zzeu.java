package com.google.android.gms.measurement.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzeu<V> {
    private static final Object zzf = new Object();
    private final String zza;
    private final zzes<V> zzb;
    private final V zzc;
    private final V zzd;
    private final Object zze;
    private volatile V zzg;
    private volatile V zzh;

    private zzeu(String str, V v, V v2, zzes<V> zzesVar) {
        this.zze = new Object();
        this.zzg = null;
        this.zzh = null;
        this.zza = str;
        this.zzc = v;
        this.zzd = v2;
        this.zzb = zzesVar;
    }

    public final String zza() {
        return this.zza;
    }

    public final V zza(V v) {
        synchronized (this.zze) {
            V v2 = this.zzg;
        }
        if (v != null) {
            return v;
        }
        if (zzer.zza == null) {
            return this.zzc;
        }
        zzw zzwVar = zzer.zza;
        synchronized (zzf) {
            if (zzw.zza()) {
                return this.zzh == null ? this.zzc : this.zzh;
            }
            try {
                for (zzeu zzeuVar : zzap.zzcz) {
                    if (zzw.zza()) {
                        throw new IllegalStateException("Refreshing flag cache must be done on a worker thread.");
                    }
                    V vZza = null;
                    try {
                        if (zzeuVar.zzb != null) {
                            vZza = zzeuVar.zzb.zza();
                        }
                    } catch (IllegalStateException unused) {
                    }
                    synchronized (zzf) {
                        zzeuVar.zzh = vZza;
                    }
                }
            } catch (SecurityException unused2) {
            }
            zzes<V> zzesVar = this.zzb;
            if (zzesVar == null) {
                zzw zzwVar2 = zzer.zza;
                return this.zzc;
            }
            try {
                return zzesVar.zza();
            } catch (IllegalStateException unused3) {
                zzw zzwVar3 = zzer.zza;
                return this.zzc;
            } catch (SecurityException unused4) {
                zzw zzwVar4 = zzer.zza;
                return this.zzc;
            }
        }
    }
}