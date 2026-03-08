package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzin extends zze {
    protected zzio zza;
    private volatile zzio zzb;
    private zzio zzc;
    private final Map<Activity, zzio> zzd;
    private zzio zze;
    private String zzf;

    public zzin(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzd = new ArrayMap();
    }

    @Override // com.google.android.gms.measurement.internal.zze
    protected final boolean zzz() {
        return false;
    }

    public final zzio zzab() {
        zzw();
        zzd();
        return this.zza;
    }

    public final void zza(Activity activity, String str, String str2) {
        if (this.zzb == null) {
            zzr().zzk().zza("setCurrentScreen cannot be called while no activity active");
            return;
        }
        if (this.zzd.get(activity) == null) {
            zzr().zzk().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
            return;
        }
        if (str2 == null) {
            str2 = zza(activity.getClass().getCanonicalName());
        }
        boolean zEquals = this.zzb.zzb.equals(str2);
        boolean zZzc = zzkv.zzc(this.zzb.zza, str);
        if (zEquals && zZzc) {
            zzr().zzk().zza("setCurrentScreen cannot be called with the same class and name");
            return;
        }
        if (str != null && (str.length() <= 0 || str.length() > 100)) {
            zzr().zzk().zza("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(str.length()));
            return;
        }
        if (str2 != null && (str2.length() <= 0 || str2.length() > 100)) {
            zzr().zzk().zza("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(str2.length()));
            return;
        }
        zzr().zzx().zza("Setting current screen to name, class", str == null ? "null" : str, str2);
        zzio zzioVar = new zzio(str, str2, zzp().zzg());
        this.zzd.put(activity, zzioVar);
        zza(activity, zzioVar, true);
    }

    public final zzio zzac() {
        zzb();
        return this.zzb;
    }

    private final void zza(Activity activity, zzio zzioVar, boolean z) {
        zzio zzioVar2 = this.zzb == null ? this.zzc : this.zzb;
        zzio zzioVar3 = zzioVar.zzb == null ? new zzio(zzioVar.zza, zza(activity.getClass().getCanonicalName()), zzioVar.zzc) : zzioVar;
        this.zzc = this.zzb;
        this.zzb = zzioVar3;
        zzq().zza(new zziq(this, z, zzm().elapsedRealtime(), zzioVar2, zzioVar3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzio zzioVar, boolean z, long j) {
        zze().zza(zzm().elapsedRealtime());
        if (zzk().zza(zzioVar.zzd, z, j)) {
            zzioVar.zzd = false;
        }
    }

    public static void zza(zzio zzioVar, Bundle bundle, boolean z) {
        if (bundle != null && zzioVar != null && (!bundle.containsKey("_sc") || z)) {
            if (zzioVar.zza != null) {
                bundle.putString("_sn", zzioVar.zza);
            } else {
                bundle.remove("_sn");
            }
            bundle.putString("_sc", zzioVar.zzb);
            bundle.putLong("_si", zzioVar.zzc);
            return;
        }
        if (bundle != null && zzioVar == null && z) {
            bundle.remove("_sn");
            bundle.remove("_sc");
            bundle.remove("_si");
        }
    }

    public final void zza(String str, zzio zzioVar) {
        zzd();
        synchronized (this) {
            if (this.zzf == null || this.zzf.equals(str) || zzioVar != null) {
                this.zzf = str;
                this.zze = zzioVar;
            }
        }
    }

    private static String zza(String str) {
        String[] strArrSplit = str.split("\\.");
        String str2 = strArrSplit.length > 0 ? strArrSplit[strArrSplit.length - 1] : "";
        return str2.length() > 100 ? str2.substring(0, 100) : str2;
    }

    private final zzio zzd(Activity activity) {
        Preconditions.checkNotNull(activity);
        zzio zzioVar = this.zzd.get(activity);
        if (zzioVar != null) {
            return zzioVar;
        }
        zzio zzioVar2 = new zzio(null, zza(activity.getClass().getCanonicalName()), zzp().zzg());
        this.zzd.put(activity, zzioVar2);
        return zzioVar2;
    }

    public final void zza(Activity activity, Bundle bundle) {
        Bundle bundle2;
        if (bundle == null || (bundle2 = bundle.getBundle("com.google.app_measurement.screen_service")) == null) {
            return;
        }
        this.zzd.put(activity, new zzio(bundle2.getString(AppMeasurementSdk.ConditionalUserProperty.NAME), bundle2.getString("referrer_name"), bundle2.getLong("id")));
    }

    public final void zza(Activity activity) {
        zza(activity, zzd(activity), false);
        zzb zzbVarZze = zze();
        zzbVarZze.zzq().zza(new zzc(zzbVarZze, zzbVarZze.zzm().elapsedRealtime()));
    }

    public final void zzb(Activity activity) {
        zzio zzioVarZzd = zzd(activity);
        this.zzc = this.zzb;
        this.zzb = null;
        zzq().zza(new zzip(this, zzioVarZzd, zzm().elapsedRealtime()));
    }

    public final void zzb(Activity activity, Bundle bundle) {
        zzio zzioVar;
        if (bundle == null || (zzioVar = this.zzd.get(activity)) == null) {
            return;
        }
        Bundle bundle2 = new Bundle();
        bundle2.putLong("id", zzioVar.zzc);
        bundle2.putString(AppMeasurementSdk.ConditionalUserProperty.NAME, zzioVar.zza);
        bundle2.putString("referrer_name", zzioVar.zzb);
        bundle.putBundle("com.google.app_measurement.screen_service", bundle2);
    }

    public final void zzc(Activity activity) {
        this.zzd.remove(activity);
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zza() {
        super.zza();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzb() {
        super.zzb();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzc() {
        super.zzc();
    }

    @Override // com.google.android.gms.measurement.internal.zzf, com.google.android.gms.measurement.internal.zzha
    public final /* bridge */ /* synthetic */ void zzd() {
        super.zzd();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzb zze() {
        return super.zze();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzhk zzf() {
        return super.zzf();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzey zzg() {
        return super.zzg();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzis zzh() {
        return super.zzh();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzin zzi() {
        return super.zzi();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzex zzj() {
        return super.zzj();
    }

    @Override // com.google.android.gms.measurement.internal.zzf
    public final /* bridge */ /* synthetic */ zzjt zzk() {
        return super.zzk();
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