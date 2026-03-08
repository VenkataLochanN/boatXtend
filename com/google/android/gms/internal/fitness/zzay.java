package com.google.android.gms.internal.fitness;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.fitness.FitnessOptions;

/* JADX INFO: loaded from: classes.dex */
public final class zzay extends zzn<zzcf> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzay> CLIENT_KEY = new Api.ClientKey<>();
    public static final Api<FitnessOptions> zzew;

    static {
        zzaz zzazVar = null;
        API = new Api<>("Fitness.SESSIONS_API", new zzba(), CLIENT_KEY);
        zzew = new Api<>("Fitness.SESSIONS_CLIENT", new zzbc(), CLIENT_KEY);
    }

    private zzay(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 58, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitSessionsApi");
        return iInterfaceQueryLocalInterface instanceof zzcf ? (zzcf) iInterfaceQueryLocalInterface : new zzcg(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitSessionsApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.SessionsApi";
    }
}