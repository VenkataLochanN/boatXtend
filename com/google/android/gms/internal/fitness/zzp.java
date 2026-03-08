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
public final class zzp extends zzn<zzbt> {
    private static final Api.ClientKey<zzp> CLIENT_KEY = new Api.ClientKey<>();
    public static final Api<Api.ApiOptions.NoOptions> API = new Api<>("Fitness.BLE_API", new zzr(), CLIENT_KEY);
    public static final Api<FitnessOptions> zzew = new Api<>("Fitness.BLE_CLIENT", new zzt(), CLIENT_KEY);

    private zzp(Context context, Looper looper, ClientSettings clientSettings, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 59, connectionCallbacks, onConnectionFailedListener, clientSettings);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.fitness.internal.IGoogleFitBleApi");
        return iInterfaceQueryLocalInterface instanceof zzbt ? (zzbt) iInterfaceQueryLocalInterface : new zzbu(iBinder);
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getServiceDescriptor() {
        return "com.google.android.gms.fitness.internal.IGoogleFitBleApi";
    }

    @Override // com.google.android.gms.internal.fitness.zzn, com.google.android.gms.common.internal.BaseGmsClient
    public final String getStartServiceAction() {
        return "com.google.android.gms.fitness.BleApi";
    }
}