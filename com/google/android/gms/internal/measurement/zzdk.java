package com.google.android.gms.internal.measurement;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
final class zzdk extends zzdh {
    zzdk() {
    }

    @Override // com.google.android.gms.internal.measurement.zzdh
    public final void zza(Throwable th, Throwable th2) {
        th.addSuppressed(th2);
    }
}