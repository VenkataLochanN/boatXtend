package com.google.firebase.iid;

import android.content.Intent;
import android.util.Log;

/* JADX INFO: compiled from: com.google.firebase:firebase-iid@@20.0.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzbc implements Runnable {
    private final zzbd zza;
    private final Intent zzb;

    zzbc(zzbd zzbdVar, Intent intent) {
        this.zza = zzbdVar;
        this.zzb = intent;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzbd zzbdVar = this.zza;
        String action = this.zzb.getAction();
        StringBuilder sb = new StringBuilder(String.valueOf(action).length() + 61);
        sb.append("Service took too long to process intent: ");
        sb.append(action);
        sb.append(" App may get closed.");
        Log.w("FirebaseInstanceId", sb.toString());
        zzbdVar.zza();
    }
}