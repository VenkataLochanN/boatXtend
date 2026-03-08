package com.google.android.gms.measurement.internal;

import android.os.RemoteException;
import android.text.TextUtils;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzji implements Runnable {
    private final /* synthetic */ AtomicReference zza;
    private final /* synthetic */ String zzb;
    private final /* synthetic */ String zzc;
    private final /* synthetic */ String zzd;
    private final /* synthetic */ boolean zze;
    private final /* synthetic */ zzm zzf;
    private final /* synthetic */ zzis zzg;

    zzji(zzis zzisVar, AtomicReference atomicReference, String str, String str2, String str3, boolean z, zzm zzmVar) {
        this.zzg = zzisVar;
        this.zza = atomicReference;
        this.zzb = str;
        this.zzc = str2;
        this.zzd = str3;
        this.zze = z;
        this.zzf = zzmVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzet zzetVar;
        synchronized (this.zza) {
            try {
                try {
                    zzetVar = this.zzg.zzb;
                } finally {
                    this.zza.notify();
                }
            } catch (RemoteException e2) {
                this.zzg.zzr().zzf().zza("(legacy) Failed to get user properties; remote exception", zzfb.zza(this.zzb), this.zzc, e2);
                this.zza.set(Collections.emptyList());
            }
            if (zzetVar == null) {
                this.zzg.zzr().zzf().zza("(legacy) Failed to get user properties; not connected to service", zzfb.zza(this.zzb), this.zzc, this.zzd);
                this.zza.set(Collections.emptyList());
                return;
            }
            if (TextUtils.isEmpty(this.zzb)) {
                this.zza.set(zzetVar.zza(this.zzc, this.zzd, this.zze, this.zzf));
            } else {
                this.zza.set(zzetVar.zza(this.zzb, this.zzc, this.zzd, this.zze));
            }
            this.zzg.zzaj();
            this.zza.notify();
        }
    }
}