package com.google.firebase.analytics;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-api@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zza implements Callable<String> {
    private final /* synthetic */ FirebaseAnalytics zza;

    zza(FirebaseAnalytics firebaseAnalytics) {
        this.zza = firebaseAnalytics;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ String call() throws Exception {
        String strZzc;
        String strZzb = this.zza.zzb();
        if (strZzb != null) {
            return strZzb;
        }
        if (this.zza.zzd) {
            strZzc = this.zza.zzc.zzh();
        } else {
            strZzc = this.zza.zzb.zzh().zzc(120000L);
        }
        if (strZzc == null) {
            throw new TimeoutException();
        }
        this.zza.zza(strZzc);
        return strZzc;
    }
}