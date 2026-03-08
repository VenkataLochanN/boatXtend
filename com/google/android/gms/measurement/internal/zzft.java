package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzft implements ServiceConnection {
    final /* synthetic */ zzfu zza;
    private final String zzb;

    zzft(zzfu zzfuVar, String str) {
        this.zza = zzfuVar;
        this.zzb = str;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zza.zza.zzr().zzi().zza("Install Referrer connection returned with null binder");
            return;
        }
        try {
            com.google.android.gms.internal.measurement.zzd zzdVarZza = com.google.android.gms.internal.measurement.zzg.zza(iBinder);
            if (zzdVarZza == null) {
                this.zza.zza.zzr().zzi().zza("Install Referrer Service implementation was not found");
            } else {
                this.zza.zza.zzr().zzx().zza("Install Referrer Service connected");
                this.zza.zza.zzq().zza(new zzfw(this, zzdVarZza, this));
            }
        } catch (Exception e2) {
            this.zza.zza.zzr().zzi().zza("Exception occurred while calling Install Referrer API", e2);
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.zza.zza.zzr().zzx().zza("Install Referrer Service disconnected");
    }
}