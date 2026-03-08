package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.measurement.internal.zzfv;
import com.google.android.gms.measurement.internal.zzga;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class AppMeasurementInstallReferrerReceiver extends BroadcastReceiver implements zzga {
    private zzfv zza;

    @Override // com.google.android.gms.measurement.internal.zzga
    public final void doStartService(Context context, Intent intent) {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if (this.zza == null) {
            this.zza = new zzfv(this);
        }
        this.zza.zza(context, intent);
    }

    @Override // com.google.android.gms.measurement.internal.zzga
    public final BroadcastReceiver.PendingResult doGoAsync() {
        return goAsync();
    }
}