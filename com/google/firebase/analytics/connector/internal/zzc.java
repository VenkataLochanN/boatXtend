package com.google.firebase.analytics.connector.internal;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnectorImpl;
import com.google.firebase.components.ComponentContainer;
import com.google.firebase.components.ComponentFactory;
import com.google.firebase.events.Subscriber;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-api@@17.2.2 */
/* JADX INFO: loaded from: classes2.dex */
final /* synthetic */ class zzc implements ComponentFactory {
    static final ComponentFactory zza = new zzc();

    private zzc() {
    }

    @Override // com.google.firebase.components.ComponentFactory
    public final Object create(ComponentContainer componentContainer) {
        return AnalyticsConnectorImpl.getInstance((FirebaseApp) componentContainer.get(FirebaseApp.class), (Context) componentContainer.get(Context.class), (Subscriber) componentContainer.get(Subscriber.class));
    }
}