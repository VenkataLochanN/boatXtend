package com.google.android.gms.measurement.internal;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfv {
    private final zzga zza;

    public zzfv(zzga zzgaVar) {
        Preconditions.checkNotNull(zzgaVar);
        this.zza = zzgaVar;
    }

    public static boolean zza(Context context) {
        ActivityInfo receiverInfo;
        Preconditions.checkNotNull(context);
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager != null && (receiverInfo = packageManager.getReceiverInfo(new ComponentName(context, "com.google.android.gms.measurement.AppMeasurementReceiver"), 0)) != null) {
                if (receiverInfo.enabled) {
                    return true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public final void zza(Context context, Intent intent) {
        zzgf zzgfVarZza = zzgf.zza(context, (com.google.android.gms.internal.measurement.zzv) null);
        zzfb zzfbVarZzr = zzgfVarZza.zzr();
        if (intent == null) {
            zzfbVarZzr.zzi().zza("Receiver called with null intent");
            return;
        }
        zzgfVarZza.zzu();
        String action = intent.getAction();
        zzfbVarZzr.zzx().zza("Local receiver got", action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            Intent className = new Intent().setClassName(context, "com.google.android.gms.measurement.AppMeasurementService");
            className.setAction("com.google.android.gms.measurement.UPLOAD");
            zzfbVarZzr.zzx().zza("Starting wakeful intent.");
            this.zza.doStartService(context, className);
            return;
        }
        if ("com.android.vending.INSTALL_REFERRER".equals(action)) {
            try {
                zzgfVarZza.zzq().zza(new zzfy(this, zzgfVarZza, zzfbVarZzr));
            } catch (Exception e2) {
                zzfbVarZzr.zzi().zza("Install Referrer Reporter encountered a problem", e2);
            }
            BroadcastReceiver.PendingResult pendingResultDoGoAsync = this.zza.doGoAsync();
            String stringExtra = intent.getStringExtra("referrer");
            if (stringExtra == null) {
                zzfbVarZzr.zzx().zza("Install referrer extras are null");
                if (pendingResultDoGoAsync != null) {
                    pendingResultDoGoAsync.finish();
                    return;
                }
                return;
            }
            zzfbVarZzr.zzv().zza("Install referrer extras are", stringExtra);
            if (!stringExtra.contains("?")) {
                String strValueOf = String.valueOf(stringExtra);
                stringExtra = strValueOf.length() != 0 ? "?".concat(strValueOf) : new String("?");
            }
            Bundle bundleZza = zzgfVarZza.zzi().zza(Uri.parse(stringExtra));
            if (bundleZza == null) {
                zzfbVarZzr.zzx().zza("No campaign defined in install referrer broadcast");
                if (pendingResultDoGoAsync != null) {
                    pendingResultDoGoAsync.finish();
                    return;
                }
                return;
            }
            long longExtra = intent.getLongExtra("referrer_timestamp_seconds", 0L) * 1000;
            if (longExtra == 0) {
                zzfbVarZzr.zzi().zza("Install referrer is missing timestamp");
            }
            zzgfVarZza.zzq().zza(new zzfx(this, zzgfVarZza, longExtra, bundleZza, context, zzfbVarZzr, pendingResultDoGoAsync));
        }
    }
}