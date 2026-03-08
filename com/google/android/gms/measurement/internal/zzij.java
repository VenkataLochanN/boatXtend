package com.google.android.gms.measurement.internal;

import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzij implements Runnable {
    private final zzik zza;
    private final int zzb;
    private final Exception zzc;
    private final byte[] zzd;
    private final Map zze;

    zzij(zzik zzikVar, int i, Exception exc, byte[] bArr, Map map) {
        this.zza = zzikVar;
        this.zzb = i;
        this.zzc = exc;
        this.zzd = bArr;
        this.zze = map;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb, this.zzc, this.zzd, this.zze);
    }
}