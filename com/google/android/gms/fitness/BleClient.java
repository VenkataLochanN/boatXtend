package com.google.android.gms.fitness;

import android.app.Activity;
import android.content.Context;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.internal.fitness.zzct;
import com.google.android.gms.internal.fitness.zzeq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BleClient extends GoogleApi<FitnessOptions> {
    private static final BleApi zze;

    static {
        zze = PlatformVersion.isAtLeastJellyBeanMR2() ? new zzct() : new zzeq();
    }

    BleClient(Activity activity, FitnessOptions fitnessOptions) {
        super(activity, com.google.android.gms.internal.fitness.zzp.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    BleClient(Context context, FitnessOptions fitnessOptions) {
        super(context, com.google.android.gms.internal.fitness.zzp.zzew, fitnessOptions, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public Task<Void> claimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zze.claimBleDevice(asGoogleApiClient(), bleDevice));
    }

    public Task<Void> claimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zze.claimBleDevice(asGoogleApiClient(), str));
    }

    public Task<List<BleDevice>> listClaimedBleDevices() {
        return PendingResultUtil.toTask(zze.listClaimedBleDevices(asGoogleApiClient()), zza.zzf);
    }

    public Task<Void> startBleScan(List<DataType> list, int i, BleScanCallback bleScanCallback) {
        if (!PlatformVersion.isAtLeastJellyBeanMR2()) {
            return Tasks.forException(new ApiException(zzeq.zzgd));
        }
        ListenerHolder<L> listenerHolderRegisterListener = registerListener(bleScanCallback, BleScanCallback.class.getSimpleName());
        return doRegisterEventListener(new zzb(this, listenerHolderRegisterListener, listenerHolderRegisterListener, list, i), new zzc(this, listenerHolderRegisterListener.getListenerKey(), listenerHolderRegisterListener));
    }

    public Task<Boolean> stopBleScan(BleScanCallback bleScanCallback) {
        return !PlatformVersion.isAtLeastJellyBeanMR2() ? Tasks.forException(new ApiException(zzeq.zzgd)) : doUnregisterEventListener(ListenerHolders.createListenerKey(bleScanCallback, BleScanCallback.class.getSimpleName()));
    }

    public Task<Void> unclaimBleDevice(BleDevice bleDevice) {
        return PendingResultUtil.toVoidTask(zze.unclaimBleDevice(asGoogleApiClient(), bleDevice));
    }

    public Task<Void> unclaimBleDevice(String str) {
        return PendingResultUtil.toVoidTask(zze.unclaimBleDevice(asGoogleApiClient(), str));
    }
}