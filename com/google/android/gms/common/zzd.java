package com.google.android.gms.common;

import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class zzd implements Callable {
    private final boolean zzq;
    private final String zzr;
    private final zze zzs;

    zzd(boolean z, String str, zze zzeVar) {
        this.zzq = z;
        this.zzr = str;
        this.zzs = zzeVar;
    }

    /* JADX WARN: Failed to inline method: com.google.android.gms.common.zzc.zza(boolean, java.lang.String, com.google.android.gms.common.zze):java.lang.String */
    /* JADX WARN: Not passed register '(r0v0 'z3' boolean)' in method call: com.google.android.gms.common.zzc.zza(boolean, java.lang.String, com.google.android.gms.common.zze):java.lang.String */
    @Override // java.util.concurrent.Callable
    public final Object call() {
        return zzc.zza(this.zzq, this.zzr, this.zzs);
    }
}