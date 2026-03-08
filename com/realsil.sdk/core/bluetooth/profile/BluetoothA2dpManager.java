package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.realsil.sdk.core.bluetooth.BluetoothHelper;
import com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.sdk.core.logger.ZLogger;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothA2dpManager extends PublicProfileManager<BluetoothA2dp> {
    public static BluetoothA2dpManager mInstance;
    public BtBroadcastReceiver Na;
    public BluetoothProfile.ServiceListener Q;

    public class BtBroadcastReceiver extends BroadcastReceiver {
        public BtBroadcastReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0031  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r7, android.content.Intent r8) {
            /*
                r6 = this;
                java.lang.String r7 = r8.getAction()
                java.lang.String r0 = "android.bluetooth.device.extra.DEVICE"
                android.os.Parcelable r0 = r8.getParcelableExtra(r0)
                android.bluetooth.BluetoothDevice r0 = (android.bluetooth.BluetoothDevice) r0
                int r1 = r7.hashCode()
                r2 = -1
                r3 = 1
                r4 = -855499628(0xffffffffcd021c94, float:-1.3643194E8)
                if (r1 == r4) goto L27
                r4 = 1244161670(0x4a286686, float:2759073.5)
                if (r1 == r4) goto L1d
                goto L31
            L1d:
                java.lang.String r1 = "android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED"
                boolean r7 = r7.equals(r1)
                if (r7 == 0) goto L31
                r7 = 0
                goto L32
            L27:
                java.lang.String r1 = "android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED"
                boolean r7 = r7.equals(r1)
                if (r7 == 0) goto L31
                r7 = r3
                goto L32
            L31:
                r7 = r2
            L32:
                java.lang.String r1 = " > "
                java.lang.String r4 = "android.bluetooth.profile.extra.PREVIOUS_STATE"
                java.lang.String r5 = "android.bluetooth.profile.extra.STATE"
                if (r7 == 0) goto L70
                if (r7 == r3) goto L3e
                goto Lbd
            L3e:
                int r7 = r8.getIntExtra(r5, r2)
                int r8 = r8.getIntExtra(r4, r2)
                r0 = 10
                if (r7 != r0) goto L51
                java.lang.String r7 = "A2DP_PLAYING_STATE: STATE_PLAYING"
            L4c:
                com.realsil.sdk.core.logger.ZLogger.d(r3, r7)
                goto Lbd
            L51:
                r0 = 11
                if (r7 != r0) goto L58
                java.lang.String r7 = "A2DP_PLAYING_STATE: STATE_NOT_PLAYING"
                goto L4c
            L58:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                java.lang.String r2 = "A2DP_PLAYING_STATE: "
                r0.append(r2)
                r0.append(r8)
                r0.append(r1)
                r0.append(r7)
                java.lang.String r7 = r0.toString()
                goto L4c
            L70:
                int r7 = r8.getIntExtra(r5, r2)
                int r8 = r8.getIntExtra(r4, r2)
                r2 = 2
                if (r7 != r2) goto L81
                java.lang.String r8 = "A2DP_CONNECTION_STATE: STATE_CONNECTED"
            L7d:
                com.realsil.sdk.core.logger.ZLogger.d(r3, r8)
                goto La3
            L81:
                if (r7 != 0) goto L86
                java.lang.String r8 = "A2DP_CONNECTION_STATE: STATE_DISCONNECTED"
                goto L7d
            L86:
                if (r7 != r3) goto L8b
                java.lang.String r8 = "A2DP_CONNECTION_STATE: STATE_CONNECTING"
                goto L7d
            L8b:
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r4 = "A2DP_CONNECTION_STATE: "
                r2.append(r4)
                r2.append(r8)
                r2.append(r1)
                r2.append(r7)
                java.lang.String r8 = r2.toString()
                goto L7d
            La3:
                com.realsil.sdk.core.bluetooth.profile.BluetoothA2dpManager r8 = com.realsil.sdk.core.bluetooth.profile.BluetoothA2dpManager.this
                java.util.List<com.realsil.sdk.core.bluetooth.profile.ProfileManagerCallback> r8 = r8.mCallbacks
                if (r8 == 0) goto Lbd
                java.util.Iterator r8 = r8.iterator()
            Lad:
                boolean r1 = r8.hasNext()
                if (r1 == 0) goto Lbd
                java.lang.Object r1 = r8.next()
                com.realsil.sdk.core.bluetooth.profile.ProfileManagerCallback r1 = (com.realsil.sdk.core.bluetooth.profile.ProfileManagerCallback) r1
                r1.onConnectionStateChanged(r0, r7)
                goto Lad
            Lbd:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.profile.BluetoothA2dpManager.BtBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public BluetoothA2dpManager(Context context) {
        super(context);
        this.Na = null;
        this.Q = new BluetoothProfile.ServiceListener() { // from class: com.realsil.sdk.core.bluetooth.profile.BluetoothA2dpManager.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                ZLogger.i(BluetoothHelper.parseProfile(i) + " profile");
                if (i == 2) {
                    BluetoothA2dpManager.this.setBluetoothProfile((BluetoothA2dp) bluetoothProfile);
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                ZLogger.i(BluetoothHelper.parseProfile(i) + " profile");
                if (i == 2) {
                    BluetoothA2dpManager.this.setBluetoothProfile(null);
                }
            }
        };
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(this.mContext, this.Q, 2);
        }
        this.Na = new BtBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        this.mContext.registerReceiver(this.Na, intentFilter);
    }

    public static BluetoothA2dpManager getInstance() {
        return mInstance;
    }

    public static void initial(Context context) {
        if (mInstance == null) {
            synchronized (BluetoothA2dpManager.class) {
                if (mInstance == null) {
                    mInstance = new BluetoothA2dpManager(context);
                }
            }
        }
    }

    public boolean a2dpSrcConnect(String str) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w(true, "BT not enabled");
            return false;
        }
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
        BP bp = this.Ia;
        if (bp == 0) {
            ZLogger.w("A2DP not initialized");
            return false;
        }
        if (((BluetoothA2dp) bp).getConnectionState(remoteDevice) == 2) {
            ZLogger.w("a2dp already connected");
            return true;
        }
        ZLogger.d(true, "a2dpSrcConnect: " + str);
        BluetoothProfileImpl.setPriority(this.Ia, remoteDevice, 100);
        return BluetoothProfileImpl.connectProfile(this.Ia, remoteDevice);
    }

    public boolean a2dpSrcConnect(byte[] bArr) {
        return a2dpSrcConnect(BluetoothHelper.convertMac(bArr));
    }

    public boolean a2dpSrcDisconnect(String str) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w(true, "BT not enabled");
            return false;
        }
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
        if (remoteDevice == null) {
            ZLogger.w("device is null");
            return false;
        }
        BP bp = this.Ia;
        if (bp == 0) {
            ZLogger.w("A2DP not initialized");
            return false;
        }
        if (((BluetoothA2dp) bp).getConnectionState(remoteDevice) != 2) {
            ZLogger.w("A2DP already disconnected");
            return false;
        }
        ZLogger.d(true, "a2dpSrcDisconnect" + str);
        BluetoothProfileImpl.setPriority(this.Ia, remoteDevice, 100);
        return BluetoothProfileImpl.disconnect(this.Ia, remoteDevice);
    }

    public boolean a2dpSrcDisconnect(byte[] bArr) {
        return a2dpSrcDisconnect(BluetoothHelper.convertMac(bArr));
    }

    public void close() {
        ZLogger.d(true, "close()");
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this.Na);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }
    }
}