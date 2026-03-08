package com.google.android.gms.measurement.internal;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.realsil.sdk.dfu.model.DfuConfig;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final class zzfo extends zzgz {
    static final Pair<String, Long> zza = new Pair<>("", 0L);
    private boolean zzaa;
    private long zzab;
    public zzfs zzb;
    public final zzfp zzc;
    public final zzfp zzd;
    public final zzfp zze;
    public final zzfp zzf;
    public final zzfp zzg;
    public final zzfp zzh;
    public final zzfp zzi;
    public final zzfr zzj;
    public final zzfp zzk;
    public final zzfp zzl;
    public final zzfq zzm;
    public final zzfr zzn;
    public final zzfq zzo;
    public final zzfq zzp;
    public final zzfp zzq;
    public final zzfp zzr;
    public boolean zzs;
    public zzfq zzt;
    public zzfq zzu;
    public zzfp zzv;
    public final zzfr zzw;
    private SharedPreferences zzy;
    private String zzz;

    final Pair<String, Boolean> zza(String str) {
        zzd();
        long jElapsedRealtime = zzm().elapsedRealtime();
        String str2 = this.zzz;
        if (str2 != null && jElapsedRealtime < this.zzab) {
            return new Pair<>(str2, Boolean.valueOf(this.zzaa));
        }
        this.zzab = jElapsedRealtime + zzt().zza(str, zzap.zza);
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(true);
        try {
            AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(zzn());
            if (advertisingIdInfo != null) {
                this.zzz = advertisingIdInfo.getId();
                this.zzaa = advertisingIdInfo.isLimitAdTrackingEnabled();
            }
            if (this.zzz == null) {
                this.zzz = "";
            }
        } catch (Exception e2) {
            zzr().zzw().zza("Unable to get advertising id", e2);
            this.zzz = "";
        }
        AdvertisingIdClient.setShouldSkipGmsCoreVersionCheck(false);
        return new Pair<>(this.zzz, Boolean.valueOf(this.zzaa));
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final boolean zze() {
        return true;
    }

    final String zzb(String str) {
        zzd();
        String str2 = (String) zza(str).first;
        MessageDigest messageDigestZzi = zzkv.zzi();
        if (messageDigestZzi == null) {
            return null;
        }
        return String.format(Locale.US, "%032X", new BigInteger(1, messageDigestZzi.digest(str2.getBytes())));
    }

    zzfo(zzgf zzgfVar) {
        super(zzgfVar);
        this.zzc = new zzfp(this, "last_upload", 0L);
        this.zzd = new zzfp(this, "last_upload_attempt", 0L);
        this.zze = new zzfp(this, "backoff", 0L);
        this.zzf = new zzfp(this, "last_delete_stale", 0L);
        this.zzk = new zzfp(this, "time_before_start", DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT);
        this.zzl = new zzfp(this, "session_timeout", 1800000L);
        this.zzm = new zzfq(this, "start_new_session", true);
        this.zzq = new zzfp(this, "last_pause_time", 0L);
        this.zzr = new zzfp(this, "time_active", 0L);
        this.zzn = new zzfr(this, "non_personalized_ads", null);
        this.zzo = new zzfq(this, "use_dynamite_api", false);
        this.zzp = new zzfq(this, "allow_remote_dynamite", false);
        this.zzg = new zzfp(this, "midnight_offset", 0L);
        this.zzh = new zzfp(this, "first_open_time", 0L);
        this.zzi = new zzfp(this, "app_install_time", 0L);
        this.zzj = new zzfr(this, "app_instance_id", null);
        this.zzt = new zzfq(this, "app_backgrounded", false);
        this.zzu = new zzfq(this, "deep_link_retrieval_complete", false);
        this.zzv = new zzfp(this, "deep_link_retrieval_attempts", 0L);
        this.zzw = new zzfr(this, "firebase_feature_rollouts", null);
    }

    @Override // com.google.android.gms.measurement.internal.zzgz
    protected final void f_() {
        this.zzy = zzn().getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
        this.zzs = this.zzy.getBoolean("has_been_opened", false);
        if (!this.zzs) {
            SharedPreferences.Editor editorEdit = this.zzy.edit();
            editorEdit.putBoolean("has_been_opened", true);
            editorEdit.apply();
        }
        this.zzb = new zzfs(this, "health_monitor", Math.max(0L, zzap.zzb.zza(null).longValue()));
    }

    protected final SharedPreferences zzg() {
        zzd();
        zzaa();
        return this.zzy;
    }

    final void zzc(String str) {
        zzd();
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.putString("gmp_app_id", str);
        editorEdit.apply();
    }

    final String zzh() {
        zzd();
        return zzg().getString("gmp_app_id", null);
    }

    final void zzd(String str) {
        zzd();
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.putString("admob_app_id", str);
        editorEdit.apply();
    }

    final String zzi() {
        zzd();
        return zzg().getString("admob_app_id", null);
    }

    final Boolean zzj() {
        zzd();
        if (zzg().contains("use_service")) {
            return Boolean.valueOf(zzg().getBoolean("use_service", false));
        }
        return null;
    }

    final void zza(boolean z) {
        zzd();
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.putBoolean("use_service", z);
        editorEdit.apply();
    }

    final void zzk() {
        zzd();
        Boolean boolZzv = zzv();
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.clear();
        editorEdit.apply();
        if (boolZzv != null) {
            zzb(boolZzv.booleanValue());
        }
    }

    final void zzb(boolean z) {
        zzd();
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.putBoolean("measurement_enabled", z);
        editorEdit.apply();
    }

    final Boolean zzv() {
        zzd();
        if (zzg().contains("measurement_enabled")) {
            return Boolean.valueOf(zzg().getBoolean("measurement_enabled", true));
        }
        return null;
    }

    protected final String zzw() {
        zzd();
        String string = zzg().getString("previous_os_version", null);
        zzl().zzaa();
        String str = Build.VERSION.RELEASE;
        if (!TextUtils.isEmpty(str) && !str.equals(string)) {
            SharedPreferences.Editor editorEdit = zzg().edit();
            editorEdit.putString("previous_os_version", str);
            editorEdit.apply();
        }
        return string;
    }

    final void zzc(boolean z) {
        zzd();
        zzr().zzx().zza("App measurement setting deferred collection", Boolean.valueOf(z));
        SharedPreferences.Editor editorEdit = zzg().edit();
        editorEdit.putBoolean("deferred_analytics_collection", z);
        editorEdit.apply();
    }

    final boolean zzx() {
        return this.zzy.contains("deferred_analytics_collection");
    }

    final boolean zza(long j) {
        return j - this.zzl.zza() > this.zzq.zza();
    }
}