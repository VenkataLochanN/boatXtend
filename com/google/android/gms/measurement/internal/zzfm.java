package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
class zzfm extends BroadcastReceiver {
    private static final String zza = zzfm.class.getName();
    private final zzkj zzb;
    private boolean zzc;
    private boolean zzd;

    zzfm(zzkj zzkjVar) {
        Preconditions.checkNotNull(zzkjVar);
        this.zzb = zzkjVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        this.zzb.zzk();
        String action = intent.getAction();
        this.zzb.zzr().zzx().zza("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zZzf = this.zzb.zzd().zzf();
            if (this.zzd != zZzf) {
                this.zzd = zZzf;
                this.zzb.zzq().zza(new zzfl(this, zZzf));
                return;
            }
            return;
        }
        this.zzb.zzr().zzi().zza("NetworkBroadcastReceiver received unknown action", action);
    }

    public final void zza() {
        this.zzb.zzk();
        this.zzb.zzq().zzd();
        if (this.zzc) {
            return;
        }
        this.zzb.zzn().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        this.zzd = this.zzb.zzd().zzf();
        this.zzb.zzr().zzx().zza("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzd));
        this.zzc = true;
    }

    public final void zzb() {
        this.zzb.zzk();
        this.zzb.zzq().zzd();
        this.zzb.zzq().zzd();
        if (this.zzc) {
            this.zzb.zzr().zzx().zza("Unregistering connectivity change receiver");
            this.zzc = false;
            this.zzd = false;
            try {
                this.zzb.zzn().unregisterReceiver(this);
            } catch (IllegalArgumentException e2) {
                this.zzb.zzr().zzf().zza("Failed to unregister the network broadcast receiver", e2);
            }
        }
    }
}