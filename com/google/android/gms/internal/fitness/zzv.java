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
public final class zzv extends zzn<zzbv> {
    public static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzv> CLIENT_KEY = new Api.ClientKey<>();
    public static final Api<FitnessOptions> zzew;

    static {
        zzw zzwVar = null;
        API = new Api<>("Fitness.CONFIG_API", new zzx(), CLIENT_KEY);
        zzew = new Api<>("Fitness.CONFIG_CLIENT", new zzz(), CLIENT_KEY);
    }

    private zzv(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 60, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitConfigApi");
        return iInterfaceQueryLocalInterface instanceof zzbv ? (zzbv) iInterfaceQueryLocalInterface : new zzbw(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitConfigApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.ConfigApi";
    }
}