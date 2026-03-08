package com.google.android.gms.measurement.internal;

import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzkl implements zzfh {
    private final /* synthetic */ String zza;
    private final /* synthetic */ zzkj zzb;

    zzkl(zzkj zzkjVar, String str) {
        this.zzb = zzkjVar;
        this.zza = str;
    }

    @Override // com.google.android.gms.measurement.internal.zzfh
    public final void zza(String str, int i, Throwable th, byte[] bArr, Map<String, List<String>> map) {
        this.zzb.zza(i, th, bArr, this.zza);
    }
}