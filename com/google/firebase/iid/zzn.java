package com.google.firebase.iid;

import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzn implements Runnable {
    private final zzl zza;
    private final Bundle zzb;
    private final TaskCompletionSource zzc;

    zzn(zzl zzlVar, Bundle bundle, TaskCompletionSource taskCompletionSource) {
        this.zza = zzlVar;
        this.zzb = bundle;
        this.zzc = taskCompletionSource;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zza(this.zzb, this.zzc);
    }
}