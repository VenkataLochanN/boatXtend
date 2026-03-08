package com.google.android.gms.measurement.internal;

import android.app.job.JobParameters;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.internal.zzju;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzjq<T extends Context & zzju> {
    private final T zza;

    public zzjq(T t) {
        Preconditions.checkNotNull(t);
        this.zza = t;
    }

    public final void zza() {
        zzgf zzgfVarZza = zzgf.zza(this.zza, (com.google.android.gms.internal.measurement.zzv) null);
        zzfb zzfbVarZzr = zzgfVarZza.zzr();
        zzgfVarZza.zzu();
        zzfbVarZzr.zzx().zza("Local AppMeasurementService is starting up");
    }

    public final void zzb() {
        zzgf zzgfVarZza = zzgf.zza(this.zza, (com.google.android.gms.internal.measurement.zzv) null);
        zzfb zzfbVarZzr = zzgfVarZza.zzr();
        zzgfVarZza.zzu();
        zzfbVarZzr.zzx().zza("Local AppMeasurementService is shutting down");
    }

    public final int zza(final Intent intent, int i, final int i2) {
        zzgf zzgfVarZza = zzgf.zza(this.zza, (com.google.android.gms.internal.measurement.zzv) null);
        final zzfb zzfbVarZzr = zzgfVarZza.zzr();
        if (intent == null) {
            zzfbVarZzr.zzi().zza("AppMeasurementService started with null intent");
            return 2;
        }
        String action = intent.getAction();
        zzgfVarZza.zzu();
        zzfbVarZzr.zzx().zza("Local AppMeasurementService called. startId, action", Integer.valueOf(i2), action);
        if ("com.google.android.gms.measurement.UPLOAD".equals(action)) {
            zza(new Runnable(this, i2, zzfbVarZzr, intent) { // from class: com.google.android.gms.measurement.internal.zzjp
                private final zzjq zza;
                private final int zzb;
                private final zzfb zzc;
                private final Intent zzd;

                {
                    this.zza = this;
                    this.zzb = i2;
                    this.zzc = zzfbVarZzr;
                    this.zzd = intent;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zza.zza(this.zzb, this.zzc, this.zzd);
                }
            });
        }
        return 2;
    }

    private final void zza(Runnable runnable) {
        zzkj zzkjVarZza = zzkj.zza(this.zza);
        zzkjVarZza.zzq().zza(new zzjr(this, zzkjVarZza, runnable));
    }

    public final IBinder zza(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onBind called with null intent");
            return null;
        }
        String action = intent.getAction();
        if ("com.google.android.gms.measurement.START".equals(action)) {
            return new zzgk(zzkj.zza(this.zza));
        }
        zzc().zzi().zza("onBind received unknown action", action);
        return null;
    }

    public final boolean zzb(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onUnbind called with null intent");
            return true;
        }
        zzc().zzx().zza("onUnbind called for intent. action", intent.getAction());
        return true;
    }

    public final boolean zza(final JobParameters jobParameters) {
        zzgf zzgfVarZza = zzgf.zza(this.zza, (com.google.android.gms.internal.measurement.zzv) null);
        final zzfb zzfbVarZzr = zzgfVarZza.zzr();
        String string = jobParameters.getExtras().getString("action");
        zzgfVarZza.zzu();
        zzfbVarZzr.zzx().zza("Local AppMeasurementJobService called. action", string);
        if (!"com.google.android.gms.measurement.UPLOAD".equals(string)) {
            return true;
        }
        zza(new Runnable(this, zzfbVarZzr, jobParameters) { // from class: com.google.android.gms.measurement.internal.zzjs
            private final zzjq zza;
            private final zzfb zzb;
            private final JobParameters zzc;

            {
                this.zza = this;
                this.zzb = zzfbVarZzr;
                this.zzc = jobParameters;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zza.zza(this.zzb, this.zzc);
            }
        });
        return true;
    }

    public final void zzc(Intent intent) {
        if (intent == null) {
            zzc().zzf().zza("onRebind called with null intent");
        } else {
            zzc().zzx().zza("onRebind called. action", intent.getAction());
        }
    }

    private final zzfb zzc() {
        return zzgf.zza(this.zza, (com.google.android.gms.internal.measurement.zzv) null).zzr();
    }

    final /* synthetic */ void zza(zzfb zzfbVar, JobParameters jobParameters) {
        zzfbVar.zzx().zza("AppMeasurementJobService processed last upload request.");
        this.zza.zza(jobParameters, false);
    }

    final /* synthetic */ void zza(int i, zzfb zzfbVar, Intent intent) {
        if (this.zza.zza(i)) {
            zzfbVar.zzx().zza("Local AppMeasurementService processed last upload request. StartId", Integer.valueOf(i));
            zzc().zzx().zza("Completed wakeful intent.");
            this.zza.zza(intent);
        }
    }
}