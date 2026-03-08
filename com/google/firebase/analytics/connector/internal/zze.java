package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-api@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public final class zze implements zza {
    Set<String> zza;
    private AnalyticsConnector.AnalyticsConnectorListener zzb;
    private AppMeasurement zzc;
    private zzd zzd = new zzd(this);

    public zze(AppMeasurement appMeasurement, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzb = analyticsConnectorListener;
        this.zzc = appMeasurement;
        this.zzc.registerOnMeasurementEventListener(this.zzd);
        this.zza = new HashSet();
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final AnalyticsConnector.AnalyticsConnectorListener zza() {
        return this.zzb;
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zza(Set<String> set) {
        this.zza.clear();
        Set<String> set2 = this.zza;
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzb.zzd(str) && zzb.zzc(str)) {
                hashSet.add(zzb.zzf(str));
            }
        }
        set2.addAll(hashSet);
    }

    @Override // com.google.firebase.analytics.connector.internal.zza
    public final void zzb() {
        this.zza.clear();
    }
}