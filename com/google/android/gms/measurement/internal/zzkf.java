package com.google.android.gms.measurement.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.PersistableBundle;
import com.google.android.gms.common.util.Clock;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzkf extends zzkk {
    private final AlarmManager zzb;
    private final zzaf zzc;
    private Integer zzd;

    protected zzkf(zzkj zzkjVar) {
        super(zzkjVar);
        this.zzb = (AlarmManager) zzn().getSystemService("alarm");
        this.zzc = new zzki(this, zzkjVar.zzs(), zzkjVar);
    }

    @Override // com.google.android.gms.measurement.internal.zzkk
    protected final boolean zze() {
        this.zzb.cancel(zzw());
        if (Build.VERSION.SDK_INT < 24) {
            return false;
        }
        zzk();
        return false;
    }

    private final void zzk() {
        JobScheduler jobScheduler = (JobScheduler) zzn().getSystemService("jobscheduler");
        int iZzv = zzv();
        if (!zzx()) {
            zzr().zzx().zza("Cancelling job. JobID", Integer.valueOf(iZzv));
        }
        jobScheduler.cancel(iZzv);
    }

    public final void zza(long j) {
        zzak();
        zzu();
        Context contextZzn = zzn();
        if (!zzfv.zza(contextZzn)) {
            zzr().zzw().zza("Receiver not registered/enabled");
        }
        if (!zzkv.zza(contextZzn, false)) {
            zzr().zzw().zza("Service not registered/enabled");
        }
        zzf();
        if (zzx()) {
            zzr().zzx().zza("Scheduling upload, millis", Long.valueOf(j));
        }
        long jElapsedRealtime = zzm().elapsedRealtime() + j;
        if (j < Math.max(0L, zzap.zzw.zza(null).longValue()) && !this.zzc.zzb()) {
            if (!zzx()) {
                zzr().zzx().zza("Scheduling upload with DelayedRunnable");
            }
            this.zzc.zza(j);
        }
        zzu();
        if (Build.VERSION.SDK_INT >= 24) {
            if (!zzx()) {
                zzr().zzx().zza("Scheduling upload with JobScheduler");
            }
            Context contextZzn2 = zzn();
            ComponentName componentName = new ComponentName(contextZzn2, "com.google.android.gms.measurement.AppMeasurementJobService");
            int iZzv = zzv();
            PersistableBundle persistableBundle = new PersistableBundle();
            persistableBundle.putString("action", "com.google.android.gms.measurement.UPLOAD");
            JobInfo jobInfoBuild = new JobInfo.Builder(iZzv, componentName).setMinimumLatency(j).setOverrideDeadline(j << 1).setExtras(persistableBundle).build();
            if (!zzx()) {
                zzr().zzx().zza("Scheduling job. JobID", Integer.valueOf(iZzv));
            }
            com.google.android.gms.internal.measurement.zzh.zza(contextZzn2, jobInfoBuild, "com.google.android.gms", "UploadAlarm");
            return;
        }
        if (!zzx()) {
            zzr().zzx().zza("Scheduling upload with AlarmManager");
        }
        this.zzb.setInexactRepeating(2, jElapsedRealtime, Math.max(zzap.zzr.zza(null).longValue(), j), zzw());
    }

    private final int zzv() {
        if (this.zzd == null) {
            String strValueOf = String.valueOf(zzn().getPackageName());
            this.zzd = Integer.valueOf((strValueOf.length() != 0 ? "measurement".concat(strValueOf) : new String("measurement")).hashCode());
        }
        return this.zzd.intValue();
    }

    public final void zzf() {
        zzak();
        if (zzx()) {
            zzr().zzx().zza("Unscheduling upload");
        }
        this.zzb.cancel(zzw());
        this.zzc.zzc();
        if (Build.VERSION.SDK_INT >= 24) {
            zzk();
        }
    }

    private final PendingIntent zzw() {
        Context contextZzn = zzn();
        return PendingIntent.getBroadcast(contextZzn, 0, new Intent().setClassName(contextZzn, "com.google.android.gms.measurement.AppMeasurementReceiver").setAction("com.google.android.gms.measurement.UPLOAD"), 0);
    }

    private final boolean zzx() {
        return com.google.android.gms.internal.measurement.zzkw.zzb() && zzt().zza(zzap.zzcx);
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzkr zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzn e_() {
        return super.e_();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzac zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzkh
    public final /* bridge */ /* synthetic */ zzfz zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzah zzl() {
        return super.zzl();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Clock zzm() {
        return super.zzm();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ Context zzn() {
        return super.zzn();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzez zzo() {
        return super.zzo();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzkv zzp() {
        return super.zzp();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzgc zzq() {
        return super.zzq();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzfb zzr() {
        return super.zzr();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzfo zzs() {
        return super.zzs();
    }

    @Override // com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ zzx zzt() {
        return super.zzt();
    }

    @Override // com.google.android.gms.measurement.internal.zzha, com.google.android.gms.measurement.internal.zzhc
    public final /* bridge */ /* synthetic */ zzw zzu() {
        return super.zzu();
    }
}