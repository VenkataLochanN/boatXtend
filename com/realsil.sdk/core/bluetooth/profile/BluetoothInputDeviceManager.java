package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothProfile;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.realsil.sdk.core.bluetooth.impl.BluetoothClassImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothInputDeviceImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes3.dex */
public class BluetoothInputDeviceManager extends HideProfileManager {
    public static BluetoothInputDeviceManager mInstance;
    public BluetoothDevice Ja;
    public boolean Ka;
    public RCUReconnectReceiver La;
    public BluetoothProfile.ServiceListener Ma;
    public HidConnectionCallback mCallback;

    public static abstract class HidConnectionCallback {
        public void onConnectionStateChange(boolean z) {
        }
    }

    private class RCUReconnectReceiver extends BroadcastReceiver {
        public RCUReconnectReceiver() {
        }

        /* JADX WARN: Removed duplicated region for block: B:36:0x00b2  */
        @Override // android.content.BroadcastReceiver
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void onReceive(android.content.Context r3, android.content.Intent r4) {
            /*
                Method dump skipped, instruction units count: 210
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.profile.BluetoothInputDeviceManager.RCUReconnectReceiver.onReceive(android.content.Context, android.content.Intent):void");
        }
    }

    public BluetoothInputDeviceManager(Context context) {
        super(context);
        this.Ma = new BluetoothProfile.ServiceListener() { // from class: com.realsil.sdk.core.bluetooth.profile.BluetoothInputDeviceManager.1
            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceConnected(int i, BluetoothProfile bluetoothProfile) {
                if (4 == i) {
                    try {
                        BluetoothInputDeviceManager.this.Ha = bluetoothProfile.getClass().asSubclass(Class.forName(BluetoothInputDeviceImpl.CLASS_NAME));
                    } catch (ClassNotFoundException e2) {
                        e2.printStackTrace();
                    }
                    BluetoothInputDeviceManager.this.Ia = bluetoothProfile;
                    ZLogger.i("get Bluetooth input device proxy");
                }
            }

            @Override // android.bluetooth.BluetoothProfile.ServiceListener
            public void onServiceDisconnected(int i) {
                if (4 == i) {
                    BluetoothInputDeviceManager bluetoothInputDeviceManager = BluetoothInputDeviceManager.this;
                    bluetoothInputDeviceManager.Ha = null;
                    bluetoothInputDeviceManager.Ia = null;
                    ZLogger.i("close Bluetooth input device proxy");
                }
            }
        };
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter != null) {
            bluetoothAdapter.getProfileProxy(context, this.Ma, 4);
        }
        this.La = new RCUReconnectReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED");
        intentFilter.addAction("android.bluetooth.device.action.BOND_STATE_CHANGED");
        this.mContext.registerReceiver(this.La, intentFilter);
    }

    public static BluetoothInputDeviceManager getInstance() {
        return mInstance;
    }

    public static void initial(Context context) {
        ZLogger.v("initial");
        mInstance = new BluetoothInputDeviceManager(context);
    }

    public static boolean isHidDevice(BluetoothDevice bluetoothDevice) {
        return BluetoothClassImpl.isHidDevice(bluetoothDevice.getBluetoothClass());
    }

    public final boolean a(BluetoothDevice bluetoothDevice) {
        try {
            Method method = this.Ha.getMethod("connect", BluetoothDevice.class);
            if (method != null) {
                boolean zBooleanValue = ((Boolean) method.invoke(this.Ia, bluetoothDevice)).booleanValue();
                StringBuilder sb = new StringBuilder();
                sb.append("connect(): connect result: ");
                sb.append(zBooleanValue);
                ZLogger.d(sb.toString());
                return zBooleanValue;
            }
        } catch (Exception e2) {
            ZLogger.e("connect(): An exception occured while connect device, e = " + e2);
        }
        return false;
    }

    public final boolean a(BluetoothDevice bluetoothDevice, HidConnectionCallback hidConnectionCallback) {
        ZLogger.d("connect()");
        this.mCallback = hidConnectionCallback;
        this.Ja = bluetoothDevice;
        this.Ka = false;
        if (!checkProfileConnect()) {
            return false;
        }
        if (bluetoothDevice.getBondState() != 12) {
            ZLogger.i("connect with not bond device, bond first, current state: " + bluetoothDevice.getBondState());
            bluetoothDevice.createBond();
            return false;
        }
        try {
            ZLogger.d("connect with bonded device, remove bond first.");
            Method method = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]);
            if (method != null) {
                boolean zBooleanValue = ((Boolean) method.invoke(bluetoothDevice, new Object[0])).booleanValue();
                if (!zBooleanValue) {
                    return a(bluetoothDevice);
                }
                this.Ka = zBooleanValue;
                StringBuilder sb = new StringBuilder();
                sb.append("removeBond(): result: ");
                sb.append(zBooleanValue);
                ZLogger.d(sb.toString());
                return zBooleanValue;
            }
        } catch (Exception e2) {
            ZLogger.e("removeBond(): An exception occured, e = " + e2);
        }
        return a(bluetoothDevice);
    }

    public boolean checkProfileConnect() {
        if (this.Ha != null) {
            return true;
        }
        ZLogger.d("checkProfileConnect(): profile not connect");
        return false;
    }

    public void close() {
        if (this.Ha != null) {
            this.B.closeProfileProxy(4, this.Ia);
        }
        if (this.mContext != null && this.La != null) {
            try {
                ZLogger.d("unregisterReceiver");
                this.mContext.unregisterReceiver(this.La);
            } catch (Exception e2) {
                ZLogger.e(e2.toString());
            }
        }
        this.mCallback = null;
    }

    public boolean connect(String str, HidConnectionCallback hidConnectionCallback) {
        return a(this.B.getRemoteDevice(str), hidConnectionCallback);
    }

    public int getConnectionState(String str) {
        return getConnectionState(this.B.getRemoteDevice(str));
    }

    public boolean isHidDevice(String str) {
        return isHidDevice(this.B.getRemoteDevice(str));
    }

    public boolean isHogpConnect(BluetoothDevice bluetoothDevice) {
        return getConnectionState(bluetoothDevice) == 2;
    }

    public boolean isHogpConnect(String str) {
        return getConnectionState(str) == 2;
    }
}