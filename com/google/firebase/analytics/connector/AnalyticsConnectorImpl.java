package com.google.firebase.analytics.connector;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.DataCollectionDefaultChange;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.analytics.connector.internal.zze;
import com.google.firebase.analytics.connector.internal.zzg;
import com.google.firebase.events.Event;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-api@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
public class AnalyticsConnectorImpl implements AnalyticsConnector {
    private static volatile AnalyticsConnector zzb;
    final Map<String, com.google.firebase.analytics.connector.internal.zza> zza;
    private final AppMeasurement zzc;

    private AnalyticsConnectorImpl(AppMeasurement appMeasurement) {
        Preconditions.checkNotNull(appMeasurement);
        this.zzc = appMeasurement;
        this.zza = new ConcurrentHashMap();
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp, Context context, Subscriber subscriber) {
        Preconditions.checkNotNull(firebaseApp);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(subscriber);
        Preconditions.checkNotNull(context.getApplicationContext());
        if (zzb == null) {
            synchronized (AnalyticsConnectorImpl.class) {
                if (zzb == null) {
                    Bundle bundle = new Bundle(1);
                    if (firebaseApp.isDefaultApp()) {
                        subscriber.subscribe(DataCollectionDefaultChange.class, zza.zza, zzb.zza);
                        bundle.putBoolean("dataCollectionDefaultEnabled", firebaseApp.isDataCollectionDefaultEnabled());
                    }
                    zzb = new AnalyticsConnectorImpl(AppMeasurement.zza(context, bundle));
                }
            }
        }
        return zzb;
    }

    public static AnalyticsConnector getInstance() {
        return getInstance(FirebaseApp.getInstance());
    }

    public static AnalyticsConnector getInstance(FirebaseApp firebaseApp) {
        return (AnalyticsConnector) firebaseApp.get(AnalyticsConnector.class);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void logEvent(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (com.google.firebase.analytics.connector.internal.zzb.zza(str) && com.google.firebase.analytics.connector.internal.zzb.zza(str2, bundle) && com.google.firebase.analytics.connector.internal.zzb.zza(str, str2, bundle)) {
            this.zzc.logEventInternal(str, str2, bundle);
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void setUserProperty(String str, String str2, Object obj) {
        if (com.google.firebase.analytics.connector.internal.zzb.zza(str) && com.google.firebase.analytics.connector.internal.zzb.zza(str, str2)) {
            this.zzc.setUserPropertyInternal(str, str2, obj);
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public Map<String, Object> getUserProperties(boolean z) {
        return this.zzc.getUserProperties(z);
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public AnalyticsConnector.AnalyticsConnectorHandle registerAnalyticsConnectorListener(final String str, AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListener) {
        com.google.firebase.analytics.connector.internal.zza zzgVar;
        Preconditions.checkNotNull(analyticsConnectorListener);
        if (!com.google.firebase.analytics.connector.internal.zzb.zza(str) || zza(str)) {
            return null;
        }
        AppMeasurement appMeasurement = this.zzc;
        if ("fiam".equals(str)) {
            zzgVar = new zze(appMeasurement, analyticsConnectorListener);
        } else {
            zzgVar = "crash".equals(str) ? new zzg(appMeasurement, analyticsConnectorListener) : null;
        }
        if (zzgVar == null) {
            return null;
        }
        this.zza.put(str, zzgVar);
        return new AnalyticsConnector.AnalyticsConnectorHandle() { // from class: com.google.firebase.analytics.connector.AnalyticsConnectorImpl.1
            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            public void unregister() {
                if (AnalyticsConnectorImpl.this.zza(str)) {
                    AnalyticsConnector.AnalyticsConnectorListener analyticsConnectorListenerZza = AnalyticsConnectorImpl.this.zza.get(str).zza();
                    if (analyticsConnectorListenerZza != null) {
                        analyticsConnectorListenerZza.onMessageTriggered(0, null);
                    }
                    AnalyticsConnectorImpl.this.zza.remove(str);
                }
            }

            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            public void registerEventNames(Set<String> set) {
                if (!AnalyticsConnectorImpl.this.zza(str) || !str.equals("fiam") || set == null || set.isEmpty()) {
                    return;
                }
                AnalyticsConnectorImpl.this.zza.get(str).zza(set);
            }

            @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorHandle
            public void unregisterEventNames() {
                if (AnalyticsConnectorImpl.this.zza(str) && str.equals("fiam")) {
                    AnalyticsConnectorImpl.this.zza.get(str).zzb();
                }
            }
        };
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void setConditionalUserProperty(AnalyticsConnector.ConditionalUserProperty conditionalUserProperty) {
        if (com.google.firebase.analytics.connector.internal.zzb.zza(conditionalUserProperty)) {
            this.zzc.setConditionalUserProperty(com.google.firebase.analytics.connector.internal.zzb.zzb(conditionalUserProperty));
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public void clearConditionalUserProperty(String str, String str2, Bundle bundle) {
        if (str2 == null || com.google.firebase.analytics.connector.internal.zzb.zza(str2, bundle)) {
            this.zzc.clearConditionalUserProperty(str, str2, bundle);
        }
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public List<AnalyticsConnector.ConditionalUserProperty> getConditionalUserProperties(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        Iterator<AppMeasurement.ConditionalUserProperty> it = this.zzc.getConditionalUserProperties(str, str2).iterator();
        while (it.hasNext()) {
            arrayList.add(com.google.firebase.analytics.connector.internal.zzb.zza(it.next()));
        }
        return arrayList;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector
    public int getMaxUserProperties(String str) {
        return this.zzc.getMaxUserProperties(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(String str) {
        return (str.isEmpty() || !this.zza.containsKey(str) || this.zza.get(str) == null) ? false : true;
    }

    static final /* synthetic */ void zza(Event event) {
        boolean z = ((DataCollectionDefaultChange) event.getPayload()).enabled;
        synchronized (AnalyticsConnectorImpl.class) {
            ((AnalyticsConnectorImpl) zzb).zzc.zza(z);
        }
    }
}