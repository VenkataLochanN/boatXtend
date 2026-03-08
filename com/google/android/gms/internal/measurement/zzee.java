package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzee {
    private final zzel zza;
    private final byte[] zzb;

    private zzee(int i) {
        this.zzb = new byte[i];
        this.zza = zzel.zza(this.zzb);
    }

    public final zzdw zza() {
        this.zza.zzb();
        return new zzeg(this.zzb);
    }

    public final zzel zzb() {
        return this.zza;
    }

    /* synthetic */ zzee(int i, zzdv zzdvVar) {
        this(i);
    }
}