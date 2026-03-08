package com.realsil.sdk.core.bluetooth.profile;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.String;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes3.dex */
public abstract class HideProfileManager<CN extends String> {
    public BluetoothManager A;
    public BluetoothAdapter B;
    public Class Ha;
    public BluetoothProfile Ia;
    public CN className;
    public List<ProfileManagerCallback> mCallbacks;
    public Context mContext;

    public HideProfileManager(Context context) {
        this.mContext = context.getApplicationContext();
        initialize();
    }

    public void addProfileManagerCallback(ProfileManagerCallback profileManagerCallback) {
        if (this.mCallbacks == null) {
            this.mCallbacks = new CopyOnWriteArrayList();
        }
        if (this.mCallbacks.contains(profileManagerCallback)) {
            return;
        }
        this.mCallbacks.add(profileManagerCallback);
    }

    public CN getClassName() {
        return this.className;
    }

    public int getConnectionState(BluetoothDevice bluetoothDevice) {
        String str;
        BluetoothAdapter bluetoothAdapter = this.B;
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            str = "BT not enabled";
        } else {
            Class cls = this.Ha;
            if (cls != null) {
                try {
                    Method method = cls.getMethod("getConnectionState", BluetoothDevice.class);
                    if (method != null) {
                        Integer num = (Integer) method.invoke(this.Ia, bluetoothDevice);
                        StringBuilder sb = new StringBuilder();
                        sb.append("result: ");
                        sb.append(num);
                        ZLogger.d(sb.toString());
                        return num.intValue();
                    }
                } catch (Exception e2) {
                    ZLogger.e("An exception occured,  " + e2.toString());
                }
                return -1;
            }
            str = "not supported > " + this.Ha.getName();
        }
        ZLogger.w(str);
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean initialize() {
        /*
            r3 = this;
            android.bluetooth.BluetoothManager r0 = r3.A
            r1 = 0
            if (r0 != 0) goto L1b
            android.content.Context r0 = r3.mContext
            java.lang.String r2 = "bluetooth"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.bluetooth.BluetoothManager r0 = (android.bluetooth.BluetoothManager) r0
            r3.A = r0
            android.bluetooth.BluetoothManager r0 = r3.A
            if (r0 != 0) goto L1b
            java.lang.String r0 = "Unable to initialize BluetoothManager."
        L17:
            com.realsil.sdk.core.logger.ZLogger.w(r0)
            return r1
        L1b:
            android.bluetooth.BluetoothAdapter r0 = r3.B
            if (r0 != 0) goto L2e
            android.bluetooth.BluetoothManager r0 = r3.A
            android.bluetooth.BluetoothAdapter r0 = r0.getAdapter()
            r3.B = r0
            android.bluetooth.BluetoothAdapter r0 = r3.B
            if (r0 != 0) goto L2e
            java.lang.String r0 = "Unable to obtain a BluetoothAdapter."
            goto L17
        L2e:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.realsil.sdk.core.bluetooth.profile.HideProfileManager.initialize():boolean");
    }

    public boolean isProfileSupported() {
        return this.Ha != null;
    }

    public void removeProfileManagerCallback(ProfileManagerCallback profileManagerCallback) {
        List<ProfileManagerCallback> list = this.mCallbacks;
        if (list != null) {
            list.remove(profileManagerCallback);
        }
    }

    public void setClassName(CN cn) {
        this.className = cn;
    }
}