package com.google.firebase.iid;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzad implements Runnable {
    private final zzw zza;

    zzad(zzw zzwVar) {
        this.zza = zzwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(2, "Service disconnected");
    }
}