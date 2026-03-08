package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothDevice;

/* JADX INFO: loaded from: classes3.dex */
public abstract class IChannelCallback {
    public void onConnectionStateChanged(BluetoothDevice bluetoothDevice, boolean z, int i) {
    }

    public void onDataReceive(byte[] bArr) {
    }
}