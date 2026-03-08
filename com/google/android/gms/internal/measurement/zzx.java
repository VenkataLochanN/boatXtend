package com.google.android.gms.internal.measurement;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.internal.GoogleServices;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.measurement.dynamite.ModuleDescriptor;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.realsil.sdk.dfu.model.DfuConfig;
import com.tencent.bugly.Bugly;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import no.nordicsemi.android.dfu.internal.scanner.BootloaderScanner;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
/* JADX INFO: loaded from: classes.dex */
public class zzx {
    private static volatile zzx zzb = null;
    private static Boolean zzh = null;
    private static Boolean zzi = null;
    private static boolean zzj = false;
    private static Boolean zzk = null;
    private static String zzl = "use_dynamite_api";
    private static String zzm = "allow_remote_dynamite";
    private static boolean zzn = false;
    private static boolean zzo = false;
    protected final Clock zza;
    private final String zzc;
    private final ExecutorService zzd;
    private final AppMeasurementSdk zze;
    private List<Pair<com.google.android.gms.measurement.internal.zzhi, zzb>> zzf;
    private int zzg;
    private boolean zzp;
    private String zzq;
    private zzm zzr;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
    class zzd implements Application.ActivityLifecycleCallbacks {
        zzd() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            zzx.this.zza(new zzbc(this, activity, bundle));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            zzx.this.zza(new zzbe(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
            zzx.this.zza(new zzbd(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            zzx.this.zza(new zzbg(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            zzx.this.zza(new zzbf(this, activity));
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            zzk zzkVar = new zzk();
            zzx.this.zza(new zzbi(this, activity, zzkVar));
            Bundle bundleZzb = zzkVar.zzb(50L);
            if (bundleZzb != null) {
                bundle.putAll(bundleZzb);
            }
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            zzx.this.zza(new zzbh(this, activity));
        }
    }

    public static zzx zza(Context context) {
        return zza(context, (String) null, (String) null, (String) null, (Bundle) null);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
    abstract class zza implements Runnable {
        final long zza;
        final long zzb;
        private final boolean zzc;

        zza(zzx zzxVar) {
            this(true);
        }

        abstract void zza() throws RemoteException;

        protected void zzb() {
        }

        zza(boolean z) {
            this.zza = zzx.this.zza.currentTimeMillis();
            this.zzb = zzx.this.zza.elapsedRealtime();
            this.zzc = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (zzx.this.zzp) {
                zzb();
                return;
            }
            try {
                zza();
            } catch (Exception e2) {
                zzx.this.zza(e2, false, this.zzc);
                zzb();
            }
        }
    }

    public static zzx zza(Context context, String str, String str2, String str3, Bundle bundle) {
        Preconditions.checkNotNull(context);
        if (zzb == null) {
            synchronized (zzx.class) {
                if (zzb == null) {
                    zzb = new zzx(context, str, str2, str3, bundle);
                }
            }
        }
        return zzb;
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
    static class zzb extends zzr {
        private final com.google.android.gms.measurement.internal.zzhi zza;

        zzb(com.google.android.gms.measurement.internal.zzhi zzhiVar) {
            this.zza = zzhiVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzs
        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.onEvent(str, str2, bundle, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzs
        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-sdk-api@@17.2.2 */
    static class zzc extends zzr {
        private final com.google.android.gms.measurement.internal.zzhf zza;

        zzc(com.google.android.gms.measurement.internal.zzhf zzhfVar) {
            this.zza = zzhfVar;
        }

        @Override // com.google.android.gms.internal.measurement.zzs
        public final void zza(String str, String str2, Bundle bundle, long j) {
            this.zza.interceptEvent(str, str2, bundle, j);
        }

        @Override // com.google.android.gms.internal.measurement.zzs
        public final int zza() {
            return System.identityHashCode(this.zza);
        }
    }

    public final AppMeasurementSdk zza() {
        return this.zze;
    }

    private zzx(Context context, String str, String str2, String str3, Bundle bundle) {
        if (str == null || !zzc(str2, str3)) {
            this.zzc = "FA";
        } else {
            this.zzc = str;
        }
        this.zza = DefaultClock.getInstance();
        this.zzd = new ThreadPoolExecutor(0, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.zze = new AppMeasurementSdk(this);
        if (!(!zzf(context) || zzk())) {
            this.zzq = null;
            this.zzp = true;
            Log.w(this.zzc, "Disabling data collection. Found google_app_id in strings.xml but Google Analytics for Firebase is missing. Remove this value or add Google Analytics for Firebase to resume data collection.");
            return;
        }
        if (!zzc(str2, str3)) {
            this.zzq = "fa";
            if (str2 == null || str3 == null) {
                if ((str2 == null) ^ (str3 == null)) {
                    Log.w(this.zzc, "Specified origin or custom app id is null. Both parameters will be ignored.");
                }
            } else {
                Log.v(this.zzc, "Deferring to Google Analytics for Firebase for event data collection. https://goo.gl/J1sWQy");
            }
        } else {
            this.zzq = str2;
        }
        zza(new zzaa(this, str2, str3, context, bundle));
        Application application = (Application) context.getApplicationContext();
        if (application == null) {
            Log.w(this.zzc, "Unable to register lifecycle notifications. Application null.");
        } else {
            application.registerActivityLifecycleCallbacks(new zzd());
        }
    }

    private static boolean zzf(Context context) {
        try {
            GoogleServices.initialize(context);
        } catch (IllegalStateException unused) {
        }
        return GoogleServices.getGoogleAppId() != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzc(String str, String str2) {
        return (str2 == null || str == null || zzk()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zza zzaVar) {
        this.zzd.execute(zzaVar);
    }

    protected final zzm zza(Context context, boolean z) {
        DynamiteModule.VersionPolicy versionPolicy;
        try {
            if (z) {
                versionPolicy = DynamiteModule.PREFER_HIGHEST_OR_REMOTE_VERSION;
            } else {
                versionPolicy = DynamiteModule.PREFER_LOCAL;
            }
            return zzl.asInterface(DynamiteModule.load(context, versionPolicy, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.measurement.internal.AppMeasurementDynamiteService"));
        } catch (DynamiteModule.LoadingException e2) {
            zza((Exception) e2, true, false);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzg(Context context) {
        return DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzh(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Exception exc, boolean z, boolean z2) {
        this.zzp |= z;
        if (z) {
            Log.w(this.zzc, "Data collection startup failed. No data will be collected.", exc);
            return;
        }
        if (z2) {
            zza(5, "Error with data collection. Data lost.", exc, (Object) null, (Object) null);
        }
        Log.w(this.zzc, "Error with data collection. Data lost.", exc);
    }

    private static boolean zzk() {
        try {
            Class.forName("com.google.firebase.analytics.FirebaseAnalytics");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public final void zza(com.google.android.gms.measurement.internal.zzhf zzhfVar) {
        zza(new zzaj(this, zzhfVar));
    }

    public final void zza(com.google.android.gms.measurement.internal.zzhi zzhiVar) {
        Preconditions.checkNotNull(zzhiVar);
        zza(new zzaw(this, zzhiVar));
    }

    public final void zzb(com.google.android.gms.measurement.internal.zzhi zzhiVar) {
        Preconditions.checkNotNull(zzhiVar);
        zza(new zzax(this, zzhiVar));
    }

    public final void zza(String str, Bundle bundle) {
        zza(null, str, bundle, false, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle) {
        zza(str, str2, bundle, true, true, null);
    }

    public final void zza(String str, String str2, Bundle bundle, long j) {
        zza(str, str2, bundle, true, false, Long.valueOf(j));
    }

    private final void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, Long l) {
        zza(new zzba(this, l, str, str2, bundle, z, z2));
    }

    public final void zza(String str, String str2) {
        zza((String) null, str, (Object) str2, false);
    }

    public final void zza(String str, String str2, Object obj) {
        zza(str, str2, obj, true);
    }

    private final void zza(String str, String str2, Object obj, boolean z) {
        zza(new zzaz(this, str, str2, obj, z));
    }

    public final void zza(Bundle bundle) {
        zza(new zzbb(this, bundle));
    }

    public final void zzb(String str, String str2, Bundle bundle) {
        zza(new zzz(this, str, str2, bundle));
    }

    public final List<Bundle> zzb(String str, String str2) {
        zzk zzkVar = new zzk();
        zza(new zzac(this, str, str2, zzkVar));
        List<Bundle> list = (List) zzk.zza(zzkVar.zzb(BootloaderScanner.TIMEOUT), List.class);
        return list == null ? Collections.emptyList() : list;
    }

    public final void zza(String str) {
        zza(new zzab(this, str));
    }

    public final void zza(Activity activity, String str, String str2) {
        zza(new zzae(this, activity, str, str2));
    }

    public final void zza(boolean z) {
        zza(new zzad(this, z));
    }

    public final void zzb() {
        zza(new zzag(this));
    }

    public final void zza(long j) {
        zza(new zzaf(this, j));
    }

    public final void zzb(long j) {
        zza(new zzai(this, j));
    }

    public final void zzb(String str) {
        zza(new zzah(this, str));
    }

    public final void zzc(String str) {
        zza(new zzak(this, str));
    }

    public final String zzc() {
        zzk zzkVar = new zzk();
        zza(new zzam(this, zzkVar));
        return zzkVar.zza(500L);
    }

    public final String zzd() {
        zzk zzkVar = new zzk();
        zza(new zzal(this, zzkVar));
        return zzkVar.zza(50L);
    }

    public final long zze() {
        zzk zzkVar = new zzk();
        zza(new zzao(this, zzkVar));
        Long l = (Long) zzk.zza(zzkVar.zzb(500L), Long.class);
        if (l == null) {
            long jNextLong = new Random(System.nanoTime() ^ this.zza.currentTimeMillis()).nextLong();
            int i = this.zzg + 1;
            this.zzg = i;
            return jNextLong + ((long) i);
        }
        return l.longValue();
    }

    public final String zzf() {
        zzk zzkVar = new zzk();
        zza(new zzan(this, zzkVar));
        return zzkVar.zza(500L);
    }

    public final String zzg() {
        zzk zzkVar = new zzk();
        zza(new zzaq(this, zzkVar));
        return zzkVar.zza(500L);
    }

    public final Map<String, Object> zza(String str, String str2, boolean z) {
        zzk zzkVar = new zzk();
        zza(new zzap(this, str, str2, z, zzkVar));
        Bundle bundleZzb = zzkVar.zzb(BootloaderScanner.TIMEOUT);
        if (bundleZzb == null || bundleZzb.size() == 0) {
            return Collections.emptyMap();
        }
        HashMap map = new HashMap(bundleZzb.size());
        for (String str3 : bundleZzb.keySet()) {
            Object obj = bundleZzb.get(str3);
            if ((obj instanceof Double) || (obj instanceof Long) || (obj instanceof String)) {
                map.put(str3, obj);
            }
        }
        return map;
    }

    public final void zza(int i, String str, Object obj, Object obj2, Object obj3) {
        zza(new zzas(this, false, 5, str, obj, null, null));
    }

    public final Bundle zza(Bundle bundle, boolean z) {
        zzk zzkVar = new zzk();
        zza(new zzar(this, bundle, zzkVar));
        if (z) {
            return zzkVar.zzb(BootloaderScanner.TIMEOUT);
        }
        return null;
    }

    public final int zzd(String str) {
        zzk zzkVar = new zzk();
        zza(new zzau(this, str, zzkVar));
        Integer num = (Integer) zzk.zza(zzkVar.zzb(DfuConfig.CONNECTION_PARAMETERS_UPDATE_TIMEOUT), Integer.class);
        if (num == null) {
            return 25;
        }
        return num.intValue();
    }

    public final String zzh() {
        zzk zzkVar = new zzk();
        zza(new zzat(this, zzkVar));
        return zzkVar.zza(120000L);
    }

    public final String zzi() {
        return this.zzq;
    }

    public final Object zza(int i) {
        zzk zzkVar = new zzk();
        zza(new zzav(this, zzkVar, i));
        return zzk.zza(zzkVar.zzb(15000L), Object.class);
    }

    public final void zzb(boolean z) {
        zza(new zzay(this, z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzi(Context context) {
        synchronized (zzx.class) {
            try {
            } catch (Exception e2) {
                Log.e("FA", "Exception reading flag from SharedPreferences.", e2);
                zzh = false;
                zzi = false;
            }
            if (zzh == null || zzi == null) {
                if (zza(context, "app_measurement_internal_disable_startup_flags")) {
                    zzh = false;
                    zzi = false;
                    return;
                }
                SharedPreferences sharedPreferences = context.getSharedPreferences("com.google.android.gms.measurement.prefs", 0);
                zzh = Boolean.valueOf(sharedPreferences.getBoolean(zzl, false));
                zzi = Boolean.valueOf(sharedPreferences.getBoolean(zzm, false));
                SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                editorEdit.remove(zzl);
                editorEdit.remove(zzm);
                editorEdit.apply();
            }
        }
    }

    public static boolean zzb(Context context) {
        zzi(context);
        synchronized (zzx.class) {
            if (!zzj) {
                try {
                    try {
                        String str = (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, "measurement.dynamite.enabled", "");
                        if ("true".equals(str)) {
                            zzk = true;
                        } else if (Bugly.SDK_IS_DEV.equals(str)) {
                            zzk = false;
                        } else {
                            zzk = null;
                        }
                        zzj = true;
                    } catch (Exception e2) {
                        Log.e("FA", "Unable to call SystemProperties.get()", e2);
                        zzk = null;
                    }
                } finally {
                    zzj = true;
                }
            }
        }
        Boolean bool = zzk;
        if (bool == null) {
            bool = zzh;
        }
        return bool.booleanValue();
    }

    private static boolean zza(Context context, String str) {
        Preconditions.checkNotEmpty(str);
        try {
            ApplicationInfo applicationInfo = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                return applicationInfo.metaData.getBoolean(str);
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }
}