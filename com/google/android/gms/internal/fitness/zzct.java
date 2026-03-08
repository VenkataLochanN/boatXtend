package com.google.android.gms.internal.fitness;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.BleApi;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.StartBleScanRequest;
import com.google.android.gms.fitness.result.BleDevicesResult;

/* JADX INFO: loaded from: classes.dex */
public final class zzct implements BleApi {
    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return googleApiClient.execute(new zzcx(this, googleApiClient, bleDevice));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> claimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzcw(this, googleApiClient, str));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<BleDevicesResult> listClaimedBleDevices(GoogleApiClient googleApiClient) {
        return googleApiClient.enqueue(new zzcz(this, googleApiClient));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> startBleScan(GoogleApiClient googleApiClient, StartBleScanRequest startBleScanRequest) {
        return googleApiClient.enqueue(new zzcu(this, googleApiClient, startBleScanRequest));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> stopBleScan(GoogleApiClient googleApiClient, BleScanCallback bleScanCallback) {
        return googleApiClient.enqueue(new zzcv(this, googleApiClient, bleScanCallback));
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, BleDevice bleDevice) {
        return unclaimBleDevice(googleApiClient, bleDevice.getAddress());
    }

    @Override // com.google.android.gms.fitness.BleApi
    public final PendingResult<Status> unclaimBleDevice(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.execute(new zzcy(this, googleApiClient, str));
    }
}