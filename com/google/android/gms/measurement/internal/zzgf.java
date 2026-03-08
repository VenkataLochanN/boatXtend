package com.google.android.gms.measurement.internal;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public class zzgf implements zzhc {
    private static volatile zzgf zza;
    private long zzaa;
    private volatile Boolean zzab;
    private Boolean zzac;
    private Boolean zzad;
    private int zzae;
    private final long zzag;
    private final Context zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final boolean zzf;
    private final zzw zzg;
    private final zzx zzh;
    private final zzfo zzi;
    private final zzfb zzj;
    private final zzgc zzk;
    private final zzjt zzl;
    private final zzkv zzm;
    private final zzez zzn;
    private final Clock zzo;
    private final zzin zzp;
    private final zzhk zzq;
    private final zzb zzr;
    private final zzii zzs;
    private zzex zzt;
    private zzis zzu;
    private zzah zzv;
    private zzey zzw;
    private zzfu zzx;
    private Boolean zzz;
    private boolean zzy = false;
    private AtomicInteger zzaf = new AtomicInteger(0);

    private zzgf(zzhh zzhhVar) {
        boolean z = false;
        Preconditions.checkNotNull(zzhhVar);
        this.zzg = new zzw(zzhhVar.zza);
        zzer.zza = this.zzg;
        this.zzb = zzhhVar.zza;
        this.zzc = zzhhVar.zzb;
        this.zzd = zzhhVar.zzc;
        this.zze = zzhhVar.zzd;
        this.zzf = zzhhVar.zzh;
        this.zzab = zzhhVar.zze;
        com.google.android.gms.internal.measurement.zzv zzvVar = zzhhVar.zzg;
        if (zzvVar != null && zzvVar.zzg != null) {
            Object obj = zzvVar.zzg.get("measurementEnabled");
            if (obj instanceof Boolean) {
                this.zzac = (Boolean) obj;
            }
            Object obj2 = zzvVar.zzg.get("measurementDeactivated");
            if (obj2 instanceof Boolean) {
                this.zzad = (Boolean) obj2;
            }
        }
        com.google.android.gms.internal.measurement.zzcn.zza(this.zzb);
        this.zzo = DefaultClock.getInstance();
        this.zzag = this.zzo.currentTimeMillis();
        this.zzh = new zzx(this);
        zzfo zzfoVar = new zzfo(this);
        zzfoVar.zzab();
        this.zzi = zzfoVar;
        zzfb zzfbVar = new zzfb(this);
        zzfbVar.zzab();
        this.zzj = zzfbVar;
        zzkv zzkvVar = new zzkv(this);
        zzkvVar.zzab();
        this.zzm = zzkvVar;
        zzez zzezVar = new zzez(this);
        zzezVar.zzab();
        this.zzn = zzezVar;
        this.zzr = new zzb(this);
        zzin zzinVar = new zzin(this);
        zzinVar.zzx();
        this.zzp = zzinVar;
        zzhk zzhkVar = new zzhk(this);
        zzhkVar.zzx();
        this.zzq = zzhkVar;
        zzjt zzjtVar = new zzjt(this);
        zzjtVar.zzx();
        this.zzl = zzjtVar;
        zzii zziiVar = new zzii(this);
        zziiVar.zzab();
        this.zzs = zziiVar;
        zzgc zzgcVar = new zzgc(this);
        zzgcVar.zzab();
        this.zzk = zzgcVar;
        if (zzhhVar.zzg != null && zzhhVar.zzg.zzb != 0) {
            z = true;
        }
        boolean z2 = !z;
        zzw zzwVar = this.zzg;
        if (this.zzb.getApplicationContext() instanceof Application) {
            zzhk zzhkVarZzh = zzh();
            if (zzhkVarZzh.zzn().getApplicationContext() instanceof Application) {
                Application application = (Application) zzhkVarZzh.zzn().getApplicationContext();
                if (zzhkVarZzh.zza == null) {
                    zzhkVarZzh.zza = new zzid(zzhkVarZzh, null);
                }
                if (z2) {
                    application.unregisterActivityLifecycleCallbacks(zzhkVarZzh.zza);
                    application.registerActivityLifecycleCallbacks(zzhkVarZzh.zza);
                    zzhkVarZzh.zzr().zzx().zza("Registered activity lifecycle callback");
                }
            }
        } else {
            zzr().zzi().zza("Application context is not an Application");
        }
        this.zzk.zza(new zzgh(this, zzhhVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzhh zzhhVar) {
        zzfd zzfdVarZzv;
        String strConcat;
        zzq().zzd();
        zzah zzahVar = new zzah(this);
        zzahVar.zzab();
        this.zzv = zzahVar;
        zzey zzeyVar = new zzey(this, zzhhVar.zzf);
        zzeyVar.zzx();
        this.zzw = zzeyVar;
        zzex zzexVar = new zzex(this);
        zzexVar.zzx();
        this.zzt = zzexVar;
        zzis zzisVar = new zzis(this);
        zzisVar.zzx();
        this.zzu = zzisVar;
        this.zzm.zzac();
        this.zzi.zzac();
        this.zzx = new zzfu(this);
        this.zzw.zzy();
        zzr().zzv().zza("App measurement initialized, version", Long.valueOf(this.zzh.zze()));
        zzw zzwVar = this.zzg;
        zzr().zzv().zza("To enable debug logging run: adb shell setprop log.tag.FA VERBOSE");
        zzw zzwVar2 = this.zzg;
        String strZzab = zzeyVar.zzab();
        if (TextUtils.isEmpty(this.zzc)) {
            if (zzi().zzf(strZzab)) {
                zzfdVarZzv = zzr().zzv();
                strConcat = "Faster debug mode event logging enabled. To disable, run:\n  adb shell setprop debug.firebase.analytics.app .none.";
            } else {
                zzfdVarZzv = zzr().zzv();
                String strValueOf = String.valueOf(strZzab);
                strConcat = strValueOf.length() != 0 ? "To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ".concat(strValueOf) : new String("To enable faster debug mode event logging run:\n  adb shell setprop debug.firebase.analytics.app ");
            }
            zzfdVarZzv.zza(strConcat);
        }
        zzr().zzw().zza("Debug-level message logging enabled");
        if (this.zzae != this.zzaf.get()) {
            zzr().zzf().zza("Not all components initialized", Integer.valueOf(this.zzae), Integer.valueOf(this.zzaf.get()));
        }
        this.zzy = true;
    }

    protected final void zza() {
        zzq().zzd();
        if (zzc().zzc.zza() == 0) {
            zzc().zzc.zza(this.zzo.currentTimeMillis());
        }
        if (Long.valueOf(zzc().zzh.zza()).longValue() == 0) {
            zzr().zzx().zza("Persisting first open", Long.valueOf(this.zzag));
            zzc().zzh.zza(this.zzag);
        }
        if (!zzah()) {
            if (zzab()) {
                if (!zzi().zzd("android.permission.INTERNET")) {
                    zzr().zzf().zza("App is missing INTERNET permission");
                }
                if (!zzi().zzd("android.permission.ACCESS_NETWORK_STATE")) {
                    zzr().zzf().zza("App is missing ACCESS_NETWORK_STATE permission");
                }
                zzw zzwVar = this.zzg;
                if (!Wrappers.packageManager(this.zzb).isCallerInstantApp() && !this.zzh.zzx()) {
                    if (!zzfv.zza(this.zzb)) {
                        zzr().zzf().zza("AppMeasurementReceiver not registered/enabled");
                    }
                    if (!zzkv.zza(this.zzb, false)) {
                        zzr().zzf().zza("AppMeasurementService not registered/enabled");
                    }
                }
                zzr().zzf().zza("Uploading is not possible. App measurement disabled");
            }
        } else {
            zzw zzwVar2 = this.zzg;
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                zzi();
                if (zzkv.zza(zzy().zzac(), zzc().zzh(), zzy().zzad(), zzc().zzi())) {
                    zzr().zzv().zza("Rechecking which service to use due to a GMP App Id change");
                    zzc().zzk();
                    zzk().zzab();
                    this.zzu.zzah();
                    this.zzu.zzaf();
                    zzc().zzh.zza(this.zzag);
                    zzc().zzj.zza(null);
                }
                zzc().zzc(zzy().zzac());
                zzc().zzd(zzy().zzad());
            }
            zzh().zza(zzc().zzj.zza());
            zzw zzwVar3 = this.zzg;
            if (com.google.android.gms.internal.measurement.zzjy.zzb() && this.zzh.zza(zzap.zzco) && !zzi().zzv() && !TextUtils.isEmpty(zzc().zzw.zza())) {
                zzr().zzi().zza("Remote config removed with active feature rollouts");
                zzc().zzw.zza(null);
            }
            if (!TextUtils.isEmpty(zzy().zzac()) || !TextUtils.isEmpty(zzy().zzad())) {
                boolean zZzab = zzab();
                if (!zzc().zzx() && !this.zzh.zzg()) {
                    zzc().zzc(!zZzab);
                }
                if (zZzab) {
                    zzh().zzai();
                }
                zze().zza.zza();
                zzw().zza(new AtomicReference<>());
            }
        }
        zzc().zzo.zza(this.zzh.zza(zzap.zzbg));
        zzc().zzp.zza(this.zzh.zza(zzap.zzbh));
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzw zzu() {
        return this.zzg;
    }

    public final zzx zzb() {
        return this.zzh;
    }

    public final zzfo zzc() {
        zza((zzha) this.zzi);
        return this.zzi;
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzfb zzr() {
        zzb(this.zzj);
        return this.zzj;
    }

    public final zzfb zzd() {
        zzfb zzfbVar = this.zzj;
        if (zzfbVar == null || !zzfbVar.zzz()) {
            return null;
        }
        return this.zzj;
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final zzgc zzq() {
        zzb(this.zzk);
        return this.zzk;
    }

    public final zzjt zze() {
        zzb(this.zzl);
        return this.zzl;
    }

    public final zzfu zzf() {
        return this.zzx;
    }

    final zzgc zzg() {
        return this.zzk;
    }

    public final zzhk zzh() {
        zzb(this.zzq);
        return this.zzq;
    }

    public final zzkv zzi() {
        zza((zzha) this.zzm);
        return this.zzm;
    }

    public final zzez zzj() {
        zza((zzha) this.zzn);
        return this.zzn;
    }

    public final zzex zzk() {
        zzb(this.zzt);
        return this.zzt;
    }

    private final zzii zzaj() {
        zzb(this.zzs);
        return this.zzs;
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final Context zzn() {
        return this.zzb;
    }

    public final boolean zzl() {
        return TextUtils.isEmpty(this.zzc);
    }

    public final String zzo() {
        return this.zzc;
    }

    public final String zzp() {
        return this.zzd;
    }

    public final String zzs() {
        return this.zze;
    }

    public final boolean zzt() {
        return this.zzf;
    }

    @Override // com.google.android.gms.measurement.internal.zzhc
    public final Clock zzm() {
        return this.zzo;
    }

    public final zzin zzv() {
        zzb(this.zzp);
        return this.zzp;
    }

    public final zzis zzw() {
        zzb(this.zzu);
        return this.zzu;
    }

    public final zzah zzx() {
        zzb(this.zzv);
        return this.zzv;
    }

    public final zzey zzy() {
        zzb(this.zzw);
        return this.zzw;
    }

    public final zzb zzz() {
        zzb zzbVar = this.zzr;
        if (zzbVar != null) {
            return zzbVar;
        }
        throw new IllegalStateException("Component not created");
    }

    public static zzgf zza(Context context, String str, String str2, Bundle bundle) {
        return zza(context, new com.google.android.gms.internal.measurement.zzv(0L, 0L, true, null, null, null, bundle));
    }

    public static zzgf zza(Context context, com.google.android.gms.internal.measurement.zzv zzvVar) {
        if (zzvVar != null && (zzvVar.zze == null || zzvVar.zzf == null)) {
            zzvVar = new com.google.android.gms.internal.measurement.zzv(zzvVar.zza, zzvVar.zzb, zzvVar.zzc, zzvVar.zzd, null, null, zzvVar.zzg);
        }
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zza == null) {
            synchronized (zzgf.class) {
                if (zza == null) {
                    zza = new zzgf(new zzhh(context, zzvVar));
                }
            }
        } else if (zzvVar != null && zzvVar.zzg != null && zzvVar.zzg.containsKey("dataCollectionDefaultEnabled")) {
            zza.zza(zzvVar.zzg.getBoolean("dataCollectionDefaultEnabled"));
        }
        return zza;
    }

    private final void zzak() {
        if (!this.zzy) {
            throw new IllegalStateException("AppMeasurement is not initialized");
        }
    }

    private static void zzb(zzgz zzgzVar) {
        if (zzgzVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzgzVar.zzz()) {
            return;
        }
        String strValueOf = String.valueOf(zzgzVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(strValueOf);
        throw new IllegalStateException(sb.toString());
    }

    private static void zzb(zze zzeVar) {
        if (zzeVar == null) {
            throw new IllegalStateException("Component not created");
        }
        if (zzeVar.zzv()) {
            return;
        }
        String strValueOf = String.valueOf(zzeVar.getClass());
        StringBuilder sb = new StringBuilder(String.valueOf(strValueOf).length() + 27);
        sb.append("Component not initialized: ");
        sb.append(strValueOf);
        throw new IllegalStateException(sb.toString());
    }

    private static void zza(zzha zzhaVar) {
        if (zzhaVar == null) {
            throw new IllegalStateException("Component not created");
        }
    }

    final void zza(boolean z) {
        this.zzab = Boolean.valueOf(z);
    }

    public final boolean zzaa() {
        return this.zzab != null && this.zzab.booleanValue();
    }

    public final boolean zzab() {
        if (com.google.android.gms.internal.measurement.zzkv.zzb() && this.zzh.zza(zzap.zzcw)) {
            return zzac() == 0;
        }
        zzq().zzd();
        zzak();
        if (this.zzh.zzg()) {
            return false;
        }
        Boolean bool = this.zzad;
        if (bool != null && bool.booleanValue()) {
            return false;
        }
        Boolean boolZzv = zzc().zzv();
        if (boolZzv != null) {
            return boolZzv.booleanValue();
        }
        Boolean boolZzh = this.zzh.zzh();
        if (boolZzh != null) {
            return boolZzh.booleanValue();
        }
        Boolean bool2 = this.zzac;
        if (bool2 != null) {
            return bool2.booleanValue();
        }
        if (GoogleServices.isMeasurementExplicitlyDisabled()) {
            return false;
        }
        if (!this.zzh.zza(zzap.zzaw) || this.zzab == null) {
            return true;
        }
        return this.zzab.booleanValue();
    }

    public final int zzac() {
        zzq().zzd();
        if (this.zzh.zzg()) {
            return 1;
        }
        Boolean bool = this.zzad;
        if (bool != null && bool.booleanValue()) {
            return 2;
        }
        Boolean boolZzv = zzc().zzv();
        if (boolZzv != null) {
            return boolZzv.booleanValue() ? 0 : 3;
        }
        Boolean boolZzh = this.zzh.zzh();
        if (boolZzh != null) {
            return boolZzh.booleanValue() ? 0 : 4;
        }
        Boolean bool2 = this.zzac;
        if (bool2 != null) {
            return bool2.booleanValue() ? 0 : 5;
        }
        if (GoogleServices.isMeasurementExplicitlyDisabled()) {
            return 6;
        }
        return (!this.zzh.zza(zzap.zzaw) || this.zzab == null || this.zzab.booleanValue()) ? 0 : 7;
    }

    final long zzad() {
        Long lValueOf = Long.valueOf(zzc().zzh.zza());
        if (lValueOf.longValue() == 0) {
            return this.zzag;
        }
        return Math.min(this.zzag, lValueOf.longValue());
    }

    final void zzae() {
        zzw zzwVar = this.zzg;
    }

    final void zzaf() {
        zzw zzwVar = this.zzg;
        throw new IllegalStateException("Unexpected call on client side");
    }

    final void zza(zzgz zzgzVar) {
        this.zzae++;
    }

    final void zza(zze zzeVar) {
        this.zzae++;
    }

    final void zzag() {
        this.zzaf.incrementAndGet();
    }

    protected final boolean zzah() {
        zzak();
        zzq().zzd();
        Boolean bool = this.zzz;
        if (bool == null || this.zzaa == 0 || (bool != null && !bool.booleanValue() && Math.abs(this.zzo.elapsedRealtime() - this.zzaa) > 1000)) {
            this.zzaa = this.zzo.elapsedRealtime();
            zzw zzwVar = this.zzg;
            boolean z = true;
            this.zzz = Boolean.valueOf(zzi().zzd("android.permission.INTERNET") && zzi().zzd("android.permission.ACCESS_NETWORK_STATE") && (Wrappers.packageManager(this.zzb).isCallerInstantApp() || this.zzh.zzx() || (zzfv.zza(this.zzb) && zzkv.zza(this.zzb, false))));
            if (this.zzz.booleanValue()) {
                if (!zzi().zza(zzy().zzac(), zzy().zzad(), zzy().zzae()) && TextUtils.isEmpty(zzy().zzad())) {
                    z = false;
                }
                this.zzz = Boolean.valueOf(z);
            }
        }
        return this.zzz.booleanValue();
    }

    public final void zzai() {
        zzq().zzd();
        zzb(zzaj());
        String strZzab = zzy().zzab();
        Pair<String, Boolean> pairZza = zzc().zza(strZzab);
        if (!this.zzh.zzi().booleanValue() || ((Boolean) pairZza.second).booleanValue() || TextUtils.isEmpty((CharSequence) pairZza.first)) {
            zzr().zzw().zza("ADID unavailable to retrieve Deferred Deep Link. Skipping");
            return;
        }
        if (!zzaj().zzg()) {
            zzr().zzi().zza("Network is not available for Deferred Deep Link request. Skipping");
            return;
        }
        URL urlZza = zzi().zza(zzy().zzt().zze(), strZzab, (String) pairZza.first, zzc().zzv.zza() - 1);
        zzii zziiVarZzaj = zzaj();
        zzih zzihVar = new zzih(this) { // from class: com.google.android.gms.measurement.internal.zzgi
            private final zzgf zza;

            {
                this.zza = this;
            }

            @Override // com.google.android.gms.measurement.internal.zzih
            public final void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
                this.zza.zza(str, i, th, bArr, map);
            }
        };
        zziiVarZzaj.zzd();
        zziiVarZzaj.zzaa();
        Preconditions.checkNotNull(urlZza);
        Preconditions.checkNotNull(zzihVar);
        zziiVarZzaj.zzq().zzb(new zzik(zziiVarZzaj, strZzab, urlZza, null, null, zzihVar));
    }

    final /* synthetic */ void zza(String str, int i, Throwable th, byte[] bArr, Map map) {
        List<ResolveInfo> listQueryIntentActivities;
        boolean z = true;
        if (!((i == 200 || i == 204 || i == 304) && th == null)) {
            zzr().zzi().zza("Network Request for Deferred Deep Link failed. response, exception", Integer.valueOf(i), th);
            return;
        }
        zzc().zzu.zza(true);
        if (bArr.length == 0) {
            zzr().zzw().zza("Deferred Deep Link response empty.");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(bArr));
            String strOptString = jSONObject.optString("deeplink", "");
            String strOptString2 = jSONObject.optString("gclid", "");
            double dOptDouble = jSONObject.optDouble("timestamp", 0.0d);
            if (TextUtils.isEmpty(strOptString)) {
                zzr().zzw().zza("Deferred Deep Link is empty.");
                return;
            }
            zzkv zzkvVarZzi = zzi();
            zzkvVarZzi.zzb();
            if (TextUtils.isEmpty(strOptString) || (listQueryIntentActivities = zzkvVarZzi.zzn().getPackageManager().queryIntentActivities(new Intent("android.intent.action.VIEW", Uri.parse(strOptString)), 0)) == null || listQueryIntentActivities.isEmpty()) {
                z = false;
            }
            if (!z) {
                zzr().zzi().zza("Deferred Deep Link validation failed. gclid, deep link", strOptString2, strOptString);
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("gclid", strOptString2);
            bundle.putString("_cis", "ddp");
            this.zzq.zza("auto", "_cmp", bundle);
            zzkv zzkvVarZzi2 = zzi();
            if (TextUtils.isEmpty(strOptString) || !zzkvVarZzi2.zza(strOptString, dOptDouble)) {
                return;
            }
            zzkvVarZzi2.zzn().sendBroadcast(new Intent("android.google.analytics.action.DEEPLINK_ACTION"));
        } catch (JSONException e2) {
            zzr().zzf().zza("Failed to parse the Deferred Deep Link response. exception", e2);
        }
    }
}