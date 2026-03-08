package com.realsil.sdk.core.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothHealth;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.realsil.sdk.core.RtkCore;
import com.realsil.sdk.core.bluetooth.impl.BluetoothA2dpImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothA2dpSinkImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothAvrcpControllerImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHeadsetImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothHidHostImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothProfileManager {
    public static final int INDICATOR_A2DP = 1;
    public static final int INDICATOR_A2DP_SINK = 2;
    public static final int INDICATOR_AVRCP = 16;
    public static final int INDICATOR_FULL = 255;
    public static final int INDICATOR_HEADSET = 4;
    public static final int INDICATOR_HID = 8;
    public static BluetoothProfileManager mInstance;
    public BluetoothManager A;
    public BluetoothAdapter B;
    public BluetoothHealth F;
    public Context mContext;
    public boolean u;
    public List<BluetoothProfileCallback> v;
    public BluetoothHeadset C = null;
    public BluetoothA2dp D = null;
    public BluetoothProfile G = null;
    public BluetoothProfile H = null;
    public BluetoothProfile I = null;
    public ProfileBroadcastReceiver J = null;
    public int K = 255;
    public BluetoothProfile.ServiceListener Q = new BluetoothProfile.ServiceListener() { // from class: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.1
        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
            ZLogger.d(BluetoothHelper.parseProfile(i) + " profile connected");
            if (i == 11) {
                BluetoothProfileManager.this.H = bluetoothProfile;
                return;
            }
            if (i == 12) {
                BluetoothProfileManager.this.I = bluetoothProfile;
                return;
            }
            if (i == 1) {
                BluetoothProfileManager.this.C = (BluetoothHeadset) bluetoothProfile;
                return;
            }
            if (i == 2) {
                BluetoothProfileManager.this.D = (BluetoothA2dp) bluetoothProfile;
            } else if (i == 3) {
                BluetoothProfileManager.this.F = (BluetoothHealth) bluetoothProfile;
            } else {
                if (i != 4) {
                    return;
                }
                BluetoothProfileManager.this.G = bluetoothProfile;
            }
        }

        @Override // android.bluetooth.BluetoothProfile.ServiceListener
        public void onServiceDisconnected(int i) {
            ZLogger.d(BluetoothHelper.parseProfile(i) + " profile disconnected");
            if (i == 1) {
                BluetoothProfileManager.this.C = null;
                return;
            }
            if (i == 2) {
                BluetoothProfileManager.this.D = null;
                return;
            }
            if (i == 3) {
                BluetoothProfileManager.this.F = null;
                return;
            }
            if (i == 4) {
                BluetoothProfileManager.this.G = null;
            } else if (i == 11) {
                BluetoothProfileManager.this.H = null;
            } else if (i == 12) {
                BluetoothProfileManager.this.I = null;
            }
        }
    };

    public class ProfileBroadcastReceiver extends BroadcastReceiver {
        public ProfileBroadcastReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:106:0x02b9  */
        /* JADX WARN: Removed duplicated region for block: B:128:0x0366  */
        /* JADX WARN: Removed duplicated region for block: B:153:0x041a  */
        /* JADX WARN: Removed duplicated region for block: B:61:0x016e  */
        /* JADX WARN: Removed duplicated region for block: B:83:0x0210  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r18, android.content.Intent r19) {
            /*
                Method dump skipped, instruction units count: 1230
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.ProfileBroadcastReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public BluetoothProfileManager(Context context) {
        this.u = false;
        this.mContext = context.getApplicationContext();
        this.u = RtkCore.DEBUG;
        initialize();
    }

    public static BluetoothProfileManager getInstance() {
        return mInstance;
    }

    public static void initial(Context context) {
        if (mInstance == null) {
            synchronized (BluetoothProfileManager.class) {
                if (mInstance == null) {
                    mInstance = new BluetoothProfileManager(context);
                }
            }
        }
    }

    public final void a(Intent intent) {
        String str;
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        if (bluetoothDevice == null) {
            str = "onVendorSpecificHeadsetEvent() remote device is null";
        } else {
            String stringExtra = intent.getStringExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD");
            if (stringExtra == null) {
                str = "onVendorSpecificHeadsetEvent() command is null";
            } else {
                int intExtra = intent.getIntExtra("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_CMD_TYPE", -1);
                if (intExtra == 0 && intExtra == 1 && intExtra == 2 && intExtra == 3 && intExtra == 4) {
                    Object[] objArr = (Object[]) intent.getExtras().get("android.bluetooth.headset.extra.VENDOR_SPECIFIC_HEADSET_EVENT_ARGS");
                    if (stringExtra != null) {
                        List<BluetoothProfileCallback> list = this.v;
                        if (list != null) {
                            Iterator<BluetoothProfileCallback> it = list.iterator();
                            while (it.hasNext()) {
                                it.next().onVendorSpecificHeadsetEvent(bluetoothDevice, stringExtra, intExtra, objArr);
                            }
                            return;
                        }
                        return;
                    }
                    str = "onVendorSpecificHeadsetEvent() args is null";
                } else {
                    str = "onVendorSpecificHeadsetEvent() unknown command";
                }
            }
        }
        ZLogger.w(str);
    }

    public void addManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        if (this.v == null) {
            this.v = new CopyOnWriteArrayList();
        }
        if (this.v.contains(bluetoothProfileCallback)) {
            return;
        }
        this.v.add(bluetoothProfileCallback);
    }

    public final void b() {
        if (this.B == null) {
            ZLogger.w("mBluetoothAdapter == null");
            return;
        }
        this.J = new ProfileBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        if ((this.K & 1) == 1) {
            getProfileProxy(2);
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED");
        }
        if ((this.K & 4) == 4) {
            getProfileProxy(1);
            intentFilter.addAction("android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED");
            intentFilter.addAction("android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT");
        }
        if ((this.K & 8) == 8) {
            getProfileProxy(4);
            intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        }
        if ((this.K & 2) == 2) {
            getProfileProxy(11);
            intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_CONNECTION_STATE_CHANGED);
            intentFilter.addAction(BluetoothA2dpSinkImpl.ACTION_PLAYING_STATE_CHANGED);
        }
        if ((this.K & 16) == 16) {
            getProfileProxy(12);
            intentFilter.addAction(BluetoothAvrcpControllerImpl.ACTION_CONNECTION_STATE_CHANGED);
        }
        this.mContext.registerReceiver(this.J, intentFilter);
    }

    public void close() {
        ZLogger.v(this.u, "close()");
        Context context = this.mContext;
        if (context != null) {
            try {
                context.unregisterReceiver(this.J);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }
    }

    public void closeProfileProxy(int i) {
        BluetoothAdapter bluetoothAdapter;
        BluetoothProfile bluetoothProfile;
        BluetoothAdapter bluetoothAdapter2 = this.B;
        if (bluetoothAdapter2 == null || !bluetoothAdapter2.isEnabled()) {
            ZLogger.w("BT not enabled");
            return;
        }
        try {
            ZLogger.v(String.format(Locale.US, "profile=%d", Integer.valueOf(i)));
            if (i == 11) {
                bluetoothAdapter = this.B;
                bluetoothProfile = this.H;
            } else if (i == 12) {
                bluetoothAdapter = this.B;
                bluetoothProfile = this.I;
            } else if (i == 1) {
                bluetoothAdapter = this.B;
                bluetoothProfile = this.C;
            } else if (i == 2) {
                bluetoothAdapter = this.B;
                bluetoothProfile = this.D;
            } else if (i == 3) {
                bluetoothAdapter = this.B;
                bluetoothProfile = this.F;
            } else {
                if (i != 4) {
                    return;
                }
                bluetoothAdapter = this.B;
                bluetoothProfile = this.G;
            }
            bluetoothAdapter.closeProfileProxy(i, bluetoothProfile);
        } catch (Exception e2) {
            ZLogger.e(e2.toString());
        }
    }

    public boolean connectA2dpSink(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothProfile bluetoothProfile = this.H;
        if (bluetoothProfile == null) {
            ZLogger.w("A2DP Sink not initialized");
            return false;
        }
        if (bluetoothProfile.getConnectionState(bluetoothDevice) == 2) {
            ZLogger.w("A2DP Sink already connected");
            return true;
        }
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w(this.u, "BT not enabled");
            return false;
        }
        ZLogger.v(this.u, "connectA2dpSink: " + bluetoothDevice.getAddress());
        return BluetoothProfileImpl.connectProfile(this.H, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
    }

    public boolean connectA2dpSink(String str) {
        return connectA2dpSink(getRemoteDevice(str));
    }

    public boolean connectA2dpSink(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectA2dpSource(BluetoothDevice bluetoothDevice) {
        String str;
        if (bluetoothDevice == null) {
            str = "can't find remote device.";
        } else {
            BluetoothA2dp bluetoothA2dp = this.D;
            if (bluetoothA2dp == null) {
                str = "A2DP not initialized";
            } else {
                if (bluetoothA2dp.getConnectionState(bluetoothDevice) == 2) {
                    ZLogger.w("a2dp already connected");
                    return true;
                }
                BluetoothAdapter bluetoothAdapter = this.B;
                if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
                    ZLogger.v(this.u, "connectA2dpSouce: " + bluetoothDevice.getAddress());
                    BluetoothProfileImpl.setPriority(this.D, bluetoothDevice, 100);
                    return BluetoothProfileImpl.connectProfile(this.D, bluetoothDevice);
                }
                str = "BT not enabled";
            }
        }
        ZLogger.w(str);
        return false;
    }

    public boolean connectA2dpSource(String str) {
        return connectA2dpSource(getRemoteDevice(str));
    }

    public boolean connectA2dpSource(byte[] bArr) {
        return connectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHfpAg(String str) {
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.C;
        if (bluetoothHeadset == null) {
            ZLogger.w(this.u, "BluetoothHeadset service is not connected");
            return false;
        }
        if (bluetoothHeadset.getConnectionState(remoteDevice) == 2) {
            ZLogger.w(this.u, "BluetoothHeadset profile is already connected");
            return true;
        }
        ZLogger.v(this.u, "HfpAgConnect" + str);
        return BluetoothProfileImpl.connectProfile(this.C, remoteDevice);
    }

    public boolean connectHfpAg(byte[] bArr) {
        return connectHfpAg(BluetoothHelper.convertMac(bArr));
    }

    public boolean connectHid(BluetoothDevice bluetoothDevice) {
        BluetoothProfile bluetoothProfile;
        String str;
        if (Build.VERSION.SDK_INT >= 28) {
            bluetoothProfile = this.G;
            str = BluetoothHidHostImpl.CLASS_NAME;
        } else {
            bluetoothProfile = this.G;
            str = BluetoothInputDeviceImpl.CLASS_NAME;
        }
        return BluetoothProfileImpl.connectProfile(bluetoothProfile, str, bluetoothDevice);
    }

    public boolean disconnectA2dpSink(BluetoothDevice bluetoothDevice) {
        String str;
        if (bluetoothDevice == null) {
            str = "device is null";
        } else {
            BluetoothProfile bluetoothProfile = this.H;
            if (bluetoothProfile == null) {
                str = "A2DP Sink not initialized";
            } else {
                if (bluetoothProfile.getConnectionState(bluetoothDevice) == 2) {
                    ZLogger.d(this.u, "disconnectA2dpSink" + bluetoothDevice.getAddress());
                    return BluetoothProfileImpl.disconnect(this.H, BluetoothA2dpSinkImpl.CLASS_NAME, bluetoothDevice);
                }
                str = "A2DP Sink already disconnected";
            }
        }
        ZLogger.w(str);
        return false;
    }

    public boolean disconnectA2dpSource(BluetoothDevice bluetoothDevice) {
        String str;
        if (bluetoothDevice == null) {
            str = "device is null";
        } else {
            BluetoothA2dp bluetoothA2dp = this.D;
            if (bluetoothA2dp == null) {
                str = "A2DP not initialized";
            } else {
                if (bluetoothA2dp.getConnectionState(bluetoothDevice) == 2) {
                    ZLogger.d(this.u, "disconnectA2dpSource" + bluetoothDevice.getAddress());
                    BluetoothProfileImpl.setPriority(this.D, bluetoothDevice, 100);
                    return BluetoothProfileImpl.disconnect(this.D, bluetoothDevice);
                }
                str = "A2DP already disconnected";
            }
        }
        ZLogger.w(str);
        return false;
    }

    public boolean disconnectA2dpSource(String str) {
        return disconnectA2dpSource(getRemoteDevice(str));
    }

    public boolean disconnectA2dpSource(byte[] bArr) {
        return disconnectA2dpSource(BluetoothHelper.convertMac(bArr));
    }

    public boolean disconnectHfp(String str) {
        boolean z;
        String str2;
        BluetoothDevice remoteDevice = this.B.getRemoteDevice(str);
        if (remoteDevice == null) {
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.C;
        if (bluetoothHeadset == null) {
            z = this.u;
            str2 = "BluetoothHeadset service is not connected";
        } else {
            if (bluetoothHeadset.getConnectionState(remoteDevice) == 2) {
                ZLogger.d(this.u, "hfpDisconnect");
                return BluetoothProfileImpl.disconnect(this.C, remoteDevice);
            }
            z = this.u;
            str2 = "BluetoothHeadset profile is not connected";
        }
        ZLogger.w(z, str2);
        return false;
    }

    public boolean disconnectHfp(byte[] bArr) {
        return disconnectHfp(BluetoothHelper.convertMac(bArr));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0052  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int getConnectionState(int r2, android.bluetooth.BluetoothDevice r3) {
        /*
            r1 = this;
            android.bluetooth.BluetoothAdapter r0 = r1.B
            if (r0 == 0) goto L5b
            boolean r0 = r0.isEnabled()
            if (r0 != 0) goto Lb
            goto L5b
        Lb:
            r0 = 1
            if (r2 == r0) goto L31
            r0 = 2
            if (r2 == r0) goto L3a
            r0 = 4
            if (r2 == r0) goto L43
            r0 = 11
            if (r2 == r0) goto L28
            r0 = 12
            if (r2 == r0) goto L1d
            goto L26
        L1d:
            android.bluetooth.BluetoothProfile r2 = r1.I
            if (r2 == 0) goto L26
            int r2 = r2.getConnectionState(r3)
            return r2
        L26:
            r2 = 0
            return r2
        L28:
            android.bluetooth.BluetoothProfile r2 = r1.H
            java.lang.String r0 = "android.bluetooth.BluetoothA2dpSink"
            int r2 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r2, r0, r3)
            return r2
        L31:
            android.bluetooth.BluetoothHeadset r2 = r1.C
            if (r2 == 0) goto L3a
            int r2 = r2.getConnectionState(r3)
            return r2
        L3a:
            android.bluetooth.BluetoothA2dp r2 = r1.D
            if (r2 == 0) goto L43
            int r2 = r2.getConnectionState(r3)
            return r2
        L43:
            int r2 = android.os.Build.VERSION.SDK_INT
            r0 = 28
            if (r2 < r0) goto L52
            android.bluetooth.BluetoothProfile r2 = r1.G
            java.lang.String r0 = "android.bluetooth.BluetoothHidHost"
            int r2 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r2, r0, r3)
            return r2
        L52:
            android.bluetooth.BluetoothProfile r2 = r1.G
            java.lang.String r0 = "android.bluetooth.BluetoothInputDevice"
            int r2 = com.realsil.sdk.core.bluetooth.impl.BluetoothProfileImpl.getConnectionState(r2, r0, r3)
            return r2
        L5b:
            boolean r2 = r1.u
            java.lang.String r3 = "BT not enabled"
            com.realsil.sdk.core.logger.ZLogger.w(r2, r3)
            r2 = -1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.BluetoothProfileManager.getConnectionState(int, android.bluetooth.BluetoothDevice):int");
    }

    public BluetoothProfile getProfile(int i) {
        if (i == 1) {
            return this.C;
        }
        if (i == 2) {
            return this.D;
        }
        if (i == 4) {
            return this.G;
        }
        if (i == 11) {
            return this.H;
        }
        if (i != 12) {
            return null;
        }
        return this.I;
    }

    public boolean getProfileProxy(int i) {
        try {
            if (!this.B.getProfileProxy(this.mContext, this.Q, i)) {
                ZLogger.d(String.format(Locale.US, "getProfileProxy %d failed", Integer.valueOf(i)));
                return false;
            }
            ZLogger.d(this.u, String.format(Locale.US, "getProfileProxy %d success", Integer.valueOf(i)));
            return true;
        } catch (Exception e2) {
            ZLogger.e(String.format(Locale.US, "getProfileProxy %d exception: %s", Integer.valueOf(i), e2.toString()));
            return false;
        }
    }

    public int getProfileState(BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            ZLogger.w("BT not enabled");
            return -1;
        }
        if (bluetoothProfile != null) {
            return bluetoothProfile.getConnectionState(bluetoothDevice);
        }
        ZLogger.d("profile is not supported");
        return -1;
    }

    public final BluetoothDevice getRemoteDevice(String str) {
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter != null && bluetoothAdapter.isEnabled()) {
            return this.B.getRemoteDevice(str);
        }
        ZLogger.w("BT not enabled");
        return null;
    }

    public final boolean initialize() {
        BluetoothAdapter defaultAdapter;
        String str;
        Context context = this.mContext;
        if (context != null) {
            if (this.B == null) {
                if (Build.VERSION.SDK_INT >= 18) {
                    this.A = (BluetoothManager) context.getSystemService("bluetooth");
                    BluetoothManager bluetoothManager = this.A;
                    if (bluetoothManager == null) {
                        str = "Unable to initialize BluetoothManager.";
                    } else {
                        defaultAdapter = bluetoothManager.getAdapter();
                    }
                } else {
                    defaultAdapter = BluetoothAdapter.getDefaultAdapter();
                }
                this.B = defaultAdapter;
                if (this.B == null) {
                    str = "Unable to obtain a BluetoothAdapter.";
                }
            }
            b();
            return true;
        }
        str = "not intialized";
        ZLogger.w(str);
        return false;
    }

    public boolean isProfileSupported(int i) {
        return i != 1 ? i != 2 ? i != 4 ? i != 11 ? i == 12 && this.I != null : this.H != null : this.G != null : this.D != null : this.C != null;
    }

    public void removeManagerCallback(BluetoothProfileCallback bluetoothProfileCallback) {
        List<BluetoothProfileCallback> list = this.v;
        if (list != null) {
            list.remove(bluetoothProfileCallback);
        }
    }

    public void setAvrcpAbsoluteVolume(int i) {
        BluetoothA2dpImpl.setAvrcpAbsoluteVolume(this.D, i);
    }

    public boolean startScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.C;
        if (bluetoothHeadset != null) {
            return Build.VERSION.SDK_INT >= 28 ? BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset) : BluetoothHeadsetImpl.startScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
        ZLogger.w(this.u, "BluetoothHeadset service is not connected");
        return false;
    }

    public boolean stopScoUsingVirtualVoiceCall(BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null) {
            ZLogger.w("can't find remote device.");
            return false;
        }
        BluetoothHeadset bluetoothHeadset = this.C;
        if (bluetoothHeadset != null) {
            return Build.VERSION.SDK_INT >= 28 ? BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset) : BluetoothHeadsetImpl.stopScoUsingVirtualVoiceCall(bluetoothHeadset, bluetoothDevice);
        }
        ZLogger.w(this.u, "BluetoothHeadset service is not connected");
        return false;
    }
}