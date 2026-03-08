package com.realsil.sdk.core.bluetooth.channel;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Build;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public abstract class Channel {
    public static final int STATE_CONNECTED = 512;
    public static final int STATE_CONNECTING = 256;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_DISCONNECTING = 768;
    public static final int STATE_LISTEN = 257;
    public static final int STATE_NONE = 0;
    public BluetoothManager A;
    public BluetoothAdapter B;
    public int U = 0;
    public BluetoothDevice ia = null;
    public boolean initialized;
    public IChannelCallback mCallback;
    public Context mContext;

    public Channel(Context context, IChannelCallback iChannelCallback) {
        this.mContext = context;
        this.mCallback = iChannelCallback;
    }

    public Channel(IChannelCallback iChannelCallback) {
        this.mCallback = iChannelCallback;
    }

    public synchronized void a(int i) {
        ZLogger.v(String.format(Locale.US, ">> ConnectionState=0x%04X > 0x%04X", Integer.valueOf(this.U), Integer.valueOf(i)));
        this.U = i;
        IChannelCallback iChannelCallback = this.mCallback;
        if (iChannelCallback != null) {
            iChannelCallback.onConnectionStateChanged(this.ia, true, this.U);
        } else {
            ZLogger.d("no callback registered");
        }
    }

    public int getConnectionState() {
        return this.U;
    }

    public BluetoothDevice getDevice() {
        return this.ia;
    }

    public void initialize() {
        ZLogger.v("initialize...");
        Context context = this.mContext;
        if (context != null && Build.VERSION.SDK_INT >= 18) {
            this.A = (BluetoothManager) context.getSystemService("bluetooth");
        }
        this.B = BluetoothAdapter.getDefaultAdapter();
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null) {
            ZLogger.w("BluetoothAdapter not initialized ");
            this.initialized = false;
        } else if (bluetoothAdapter.isEnabled()) {
            this.initialized = true;
        } else {
            ZLogger.w("Bluetooth is disabled ");
            this.initialized = false;
        }
    }
}