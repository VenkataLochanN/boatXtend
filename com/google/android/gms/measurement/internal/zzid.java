package com.google.android.gms.measurement.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.internal.measurement.zzlc;
import com.google.android.gms.internal.measurement.zzlt;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzid implements Application.ActivityLifecycleCallbacks {
    private final /* synthetic */ zzhk zza;

    private zzid(zzhk zzhkVar) {
        this.zza = zzhkVar;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        try {
            this.zza.zzr().zzx().zza("onActivityCreated");
            Intent intent = activity.getIntent();
            if (intent == null) {
                return;
            }
            Uri data = intent.getData();
            if (data != null && data.isHierarchical()) {
                this.zza.zzp();
                String str = zzkv.zza(intent) ? "gs" : "auto";
                String queryParameter = data.getQueryParameter("referrer");
                boolean z = bundle == null;
                if (zzlt.zzb() && zzap.zzcb.zza(null).booleanValue()) {
                    this.zza.zzq().zza(new zzig(this, z, data, str, queryParameter));
                } else {
                    zza(z, data, str, queryParameter);
                }
            }
        } catch (Exception e2) {
            this.zza.zzr().zzf().zza("Throwable caught in onActivityCreated", e2);
        } finally {
            this.zza.zzi().zza(activity, bundle);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(boolean z, Uri uri, String str, String str2) {
        Bundle bundleZza;
        Bundle bundleZza2;
        try {
            if (this.zza.zzt().zza(zzap.zzby) || this.zza.zzt().zza(zzap.zzca) || this.zza.zzt().zza(zzap.zzbz)) {
                zzkv zzkvVarZzp = this.zza.zzp();
                if (!TextUtils.isEmpty(str2)) {
                    if (!str2.contains("gclid") && !str2.contains("utm_campaign") && !str2.contains("utm_source") && !str2.contains("utm_medium")) {
                        zzkvVarZzp.zzr().zzw().zza("Activity created with data 'referrer' without required params");
                    } else {
                        String strValueOf = String.valueOf(str2);
                        bundleZza = zzkvVarZzp.zza(Uri.parse(strValueOf.length() != 0 ? "https://google.com/search?".concat(strValueOf) : new String("https://google.com/search?")));
                        if (bundleZza != null) {
                            bundleZza.putString("_cis", "referrer");
                        }
                    }
                }
                bundleZza = null;
            } else {
                bundleZza = null;
            }
            boolean z2 = false;
            if (z) {
                bundleZza2 = this.zza.zzp().zza(uri);
                if (bundleZza2 != null) {
                    bundleZza2.putString("_cis", "intent");
                    if (this.zza.zzt().zza(zzap.zzby) && !bundleZza2.containsKey("gclid") && bundleZza != null && bundleZza.containsKey("gclid")) {
                        bundleZza2.putString("_cer", String.format("gclid=%s", bundleZza.getString("gclid")));
                    }
                    this.zza.zza(str, "_cmp", bundleZza2);
                }
            } else {
                bundleZza2 = null;
            }
            if (this.zza.zzt().zza(zzap.zzca) && !this.zza.zzt().zza(zzap.zzbz) && bundleZza != null && bundleZza.containsKey("gclid") && (bundleZza2 == null || !bundleZza2.containsKey("gclid"))) {
                this.zza.zza("auto", "_lgclid", (Object) bundleZza.getString("gclid"), true);
            }
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            this.zza.zzr().zzw().zza("Activity created with referrer", str2);
            if (this.zza.zzt().zza(zzap.zzbz)) {
                if (bundleZza != null) {
                    this.zza.zza(str, "_cmp", bundleZza);
                } else {
                    this.zza.zzr().zzw().zza("Referrer does not contain valid parameters", str2);
                }
                this.zza.zza("auto", "_ldl", (Object) null, true);
                return;
            }
            if (str2.contains("gclid") && (str2.contains("utm_campaign") || str2.contains("utm_source") || str2.contains("utm_medium") || str2.contains("utm_term") || str2.contains("utm_content"))) {
                z2 = true;
            }
            if (!z2) {
                this.zza.zzr().zzw().zza("Activity created with data 'referrer' without required params");
            } else {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                this.zza.zza("auto", "_ldl", (Object) str2, true);
            }
        } catch (Exception e2) {
            this.zza.zzr().zzf().zza("Throwable caught in handleReferrerForOnActivityCreated", e2);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        this.zza.zzi().zzc(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.zza.zzi().zzb(activity);
        zzjt zzjtVarZzk = this.zza.zzk();
        zzjtVarZzk.zzq().zza(new zzjv(zzjtVarZzk, zzjtVarZzk.zzm().elapsedRealtime()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        if (zzlc.zzb() && zzap.zzau.zza(null).booleanValue()) {
            this.zza.zzk().zzab();
            this.zza.zzi().zza(activity);
        } else {
            this.zza.zzi().zza(activity);
            this.zza.zzk().zzab();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        this.zza.zzi().zzb(activity, bundle);
    }

    /* synthetic */ zzid(zzhk zzhkVar, zzhm zzhmVar) {
        this(zzhkVar);
    }
}