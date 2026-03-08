package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import com.google.android.gms.common.stats.ConnectionTracker;
import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.gms.common.wrappers.Wrappers;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-impl@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zzfu {
    final zzgf zza;

    zzfu(zzgf zzgfVar) {
        this.zza = zzgfVar;
    }

    protected final void zza() {
        this.zza.zzae();
        zza(this.zza.zzn().getPackageName());
    }

    protected final void zza(String str) {
        if (str == null || str.isEmpty()) {
            this.zza.zzr().zzj().zza("Install Referrer Reporter was called with invalid app package name");
            return;
        }
        this.zza.zzq().zzd();
        if (!zzb()) {
            this.zza.zzr().zzv().zza("Install Referrer Reporter is not available");
            return;
        }
        zzft zzftVar = new zzft(this, str);
        this.zza.zzq().zzd();
        Intent intent = new Intent("com.google.android.finsky.BIND_GET_INSTALL_REFERRER_SERVICE");
        intent.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.externalreferrer.GetInstallReferrerService"));
        PackageManager packageManager = this.zza.zzn().getPackageManager();
        if (packageManager == null) {
            this.zza.zzr().zzj().zza("Failed to obtain Package Manager to verify binding conditions for Install Referrer");
            return;
        }
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (listQueryIntentServices != null && !listQueryIntentServices.isEmpty()) {
            ResolveInfo resolveInfo = listQueryIntentServices.get(0);
            if (resolveInfo.serviceInfo != null) {
                String str2 = resolveInfo.serviceInfo.packageName;
                if (resolveInfo.serviceInfo.name != null && "com.android.vending".equals(str2) && zzb()) {
                    try {
                        this.zza.zzr().zzx().zza("Install Referrer Service is", ConnectionTracker.getInstance().bindService(this.zza.zzn(), new Intent(intent), zzftVar, 1) ? "available" : "not available");
                        return;
                    } catch (Exception e2) {
                        this.zza.zzr().zzf().zza("Exception occurred while binding to Install Referrer Service", e2.getMessage());
                        return;
                    }
                }
                this.zza.zzr().zzi().zza("Play Store version 8.3.73 or higher required for Install Referrer");
                return;
            }
            return;
        }
        this.zza.zzr().zzv().zza("Play Service for fetching Install Referrer is unavailable on device");
    }

    private final boolean zzb() {
        try {
            PackageManagerWrapper packageManagerWrapperPackageManager = Wrappers.packageManager(this.zza.zzn());
            if (packageManagerWrapperPackageManager != null) {
                return packageManagerWrapperPackageManager.getPackageInfo("com.android.vending", 128).versionCode >= 80837300;
            }
            this.zza.zzr().zzx().zza("Failed to get PackageManager for Install Referrer Play Store compatibility check");
            return false;
        } catch (Exception e2) {
            this.zza.zzr().zzx().zza("Failed to retrieve Play Store version for Install Referrer", e2);
            return false;
        }
    }

    final Bundle zza(String str, com.google.android.gms.internal.measurement.zzd zzdVar) {
        this.zza.zzq().zzd();
        if (zzdVar == null) {
            this.zza.zzr().zzi().zza("Attempting to use Install Referrer Service while it is not initialized");
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package_name", str);
        try {
            Bundle bundleZza = zzdVar.zza(bundle);
            if (bundleZza != null) {
                return bundleZza;
            }
            this.zza.zzr().zzf().zza("Install Referrer Service returned a null response");
            return null;
        } catch (Exception e2) {
            this.zza.zzr().zzf().zza("Exception occurred while retrieving the Install Referrer", e2.getMessage());
            return null;
        }
    }
}