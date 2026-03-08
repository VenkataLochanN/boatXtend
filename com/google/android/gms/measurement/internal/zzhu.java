package com.google.android.gms.measurement.internal;

import android.os.Bundle;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzhu implements Runnable {
    private final /* synthetic */ Bundle zza;
    private final /* synthetic */ zzhk zzb;

    zzhu(zzhk zzhkVar, Bundle bundle) {
        this.zzb = zzhkVar;
        this.zza = bundle;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzd(this.zza);
    }
}