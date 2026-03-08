package com.google.firebase.iid;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzac implements Runnable {
    private final zzw zza;
    private final zzah zzb;

    zzac(zzw zzwVar2, zzah zzahVarPoll2) {
        this.zza = zzwVar2;
        this.zzb = zzahVarPoll2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb.zza);
    }
}